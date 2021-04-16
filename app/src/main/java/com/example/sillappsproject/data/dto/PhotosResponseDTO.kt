package com.example.sillappsproject.data.dto

import com.google.gson.annotations.SerializedName

data class PhotosResponseDTO(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("id") val id: Int,
@SerializedName("title") val title: String,
@SerializedName("thumbnailUrl") val thumbnailUrl: String)