package com.example.datamastictask


class NewsRepository {
    suspend fun getTopHeadlines(apiKey: String): List<Article> {

        return try {
            RetrofitInstance.api.getTopHeadlines(apiKey = apiKey).articles
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getLikes(articleId: String): Int {
        return try {
            RetrofitInstance.api.getLikes(articleId).likes
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getComments(articleId: String): Int {
        return try {
            RetrofitInstance.api.getComments(articleId).comments
        } catch (e: Exception) {
            0
        }
    }
}
