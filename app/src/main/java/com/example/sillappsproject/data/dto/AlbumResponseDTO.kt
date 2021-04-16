package com.example.sillappsproject.data.dto


import com.google.gson.annotations.SerializedName

data class AlbumsResponseDTO(@SerializedName("userId") val userId: Int,
                             @SerializedName("id") val id: Int,
                             @SerializedName("title") val title: String
)

