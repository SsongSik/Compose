package com.test.composestudy.part1.service

import com.test.composestudy.part1.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user}/repos")
    suspend fun listRepos(
        @Path("user") user: String
    ): List<Repo>
}