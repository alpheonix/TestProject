package com.example.sillappsproject.data.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String)
