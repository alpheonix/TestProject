package com.example.sillappsproject.data.api

import android.util.Log
import androidx.lifecycle.Transformations.map
import com.example.sillapps.data.api.SillappsService
import com.example.sillapps.data.model.Album
import com.example.sillappsproject.data.dto.AlbumsResponseDTO
import com.example.sillappsproject.data.dto.PhotosResponseDTO
import com.example.sillappsproject.data.dto.UserResponseDTO
import com.example.sillappsproject.data.model.Photo
import com.example.sillappsproject.data.model.Users
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val USER_ID = "48933662@N05"
private const val PARAM_API_KEY = "api_key"
private const val PARAM_FORMAT = "format"
private const val PARAM_NOJSON_CALLBACK = "nojsoncallback"

object SillappsProvider {
    private var service: SillappsService

    init {
        service = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SillappsService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor {
                val request = it.request()
                val url = request.url
                val builder = url.newBuilder()
                val newUrl = builder.build()
                val newRequest = request.newBuilder().url(newUrl).build()

                it.proceed(newRequest)
            }.build()
    }

    fun getAlbum(listener: Listener2<List<Album>>) {
        Log.d("1","1")
        service.getAlbum().enqueue(object : Callback<List<AlbumsResponseDTO>> {

            override fun onFailure(call: Call<List<AlbumsResponseDTO>>, t: Throwable) {
                Log.d("erroralbum",t.message)
            }

            override fun onResponse(
                call: Call<List<AlbumsResponseDTO>>,
                response: Response<List<AlbumsResponseDTO>>
            ) {
                response.body()?.let { albumsResponseDTO ->
                    val album = AlbumsResponseMapper().map(albumsResponseDTO)
                    listener.onSuccess(album)
                }
            }
        })
    }

    fun getUser( listener: Listener<List<Users>>) {
        service.getUser().enqueue(object  : Callback<List<UserResponseDTO>> {
            override fun onFailure(call: Call<List<UserResponseDTO>>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(
                call: Call<List<UserResponseDTO>>,
                response: Response<List<UserResponseDTO>>
            ) {
                response.body()?.let { userResponseDTO ->
                    val user = UserResponseMapper().map(userResponseDTO)

                    listener.onSuccess(user)
                }
            }

        })}

    fun getPhotos(albumId:Int,listener: Listener<List<Photo>>){
        service.getPhotos(albumId).enqueue(object : Callback<List<PhotosResponseDTO>>{
            override fun onFailure(call: Call<List<PhotosResponseDTO>>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(
                call: Call<List<PhotosResponseDTO>>,
                response: Response<List<PhotosResponseDTO>>
            ) {
                response.body()?.let { photoResponseDTO ->
                    val photos = PhotoResponseMapper().map(photoResponseDTO)
                    listener.onSuccess(photos)


                }
            }

        })
    }


interface Listener<T> {
    fun onError(t: Throwable)
    fun onSuccess(track: T)
}

    interface Listener2<T> {
        fun onError(t: Throwable)
        fun onSuccess(track: List<Album>)
    }
    class AlbumsResponseMapper {

        fun map(albumsResponse: List<AlbumsResponseDTO>): List<Album> {
            val albumListDTO = albumsResponse

            return albumListDTO.map { albumDto ->

                Album(albumDto.userId, albumDto.id, albumDto.title)
            }
        }
    }
class UserResponseMapper {

    fun map(userResponse: List<UserResponseDTO>): List<Users> {
        val userListDTO = userResponse


        return userListDTO.map { userDto ->
            Users(userDto.id,userDto.name)
        }
    }
}

    class PhotoResponseMapper {
        fun map(photoResponse: List<PhotosResponseDTO>): List<Photo> {
            val photoListDTO = photoResponse


            return photoListDTO.map { photoDto ->
                Photo(photoDto.albumId,photoDto.id,photoDto.title,photoDto.thumbnailUrl)
            }
        }
    }
}