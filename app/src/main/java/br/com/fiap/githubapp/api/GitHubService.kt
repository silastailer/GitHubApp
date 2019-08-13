package br.com.fiap.githubapp.api

import br.com.fiap.githubapp.model.Username
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("/users/{username}")
    fun search(@Path("username")username: String) : Call<Username>
}