package com.example.datamastictask

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("likes/{articleId}")
    suspend fun getLikes(@Path("articleId") articleId: String): LikesResponse

    @GET("comments/{articleId}")
    suspend fun getComments(@Path("articleId") articleId: String): CommentsResponse
}