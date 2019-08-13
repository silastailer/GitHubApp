package br.com.fiap.githubapp.model

import com.google.gson.annotations.SerializedName

data class Username (
    @SerializedName("name") val name: String,
    @SerializedName("avatar_url") val avatarUrl: String
)