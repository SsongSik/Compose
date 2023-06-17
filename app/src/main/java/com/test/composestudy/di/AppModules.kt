package com.test.composestudy.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.composestudy.service.GithubService
import com.test.composestudy.service.PokeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

//    @Singleton
//    @Provides
//    @Named("API_URI")
//    fun provideWebAPI(): String = "https://api.github.com/"

    @Singleton
    @Provides
    @Named("API_URI")
    fun provideWebAPI(): String = "https://pokeapi.co/api/v2/"

    // JSON <-> Kotlin Object 변환을 위한 Gson 객체 생성
    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //html_url -> htmlUrl
            .create()

    @Singleton
    @Provides
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("API_URI") apiUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

//    @Singleton
//    @Provides
//    fun provideGithubService(
//        retrofit: Retrofit
//    ): GithubService = retrofit.create(GithubService::class.java)

    @Singleton
    @Provides
    fun provideGithubService(
        retrofit: Retrofit
    ): PokeAPI = retrofit.create(PokeAPI::class.java)
}