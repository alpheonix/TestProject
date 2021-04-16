package com.example.sillapps.data.api



import com.example.sillappsproject.data.api.SillappsProvider
import com.example.sillappsproject.data.dto.AlbumsResponseDTO
import com.example.sillappsproject.data.dto.PhotosResponseDTO
import com.example.sillappsproject.data.dto.UserResponseDTO
import com.example.sillappsproject.data.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SillappsService {
    @GET("albums")
    fun getAlbum(): Call<List<AlbumsResponseDTO>>
    @GET("users")
    fun getUser(): Call<List<UserResponseDTO>>
    @GET("photos")
    fun getPhotos(@Query("albumId") albumId:Int): Call<List<PhotosResponseDTO>>


}