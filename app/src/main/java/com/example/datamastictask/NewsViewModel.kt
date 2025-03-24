package com.example.datamastictask

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    var articles by mutableStateOf(emptyList<Article>())
    var errorMessage by mutableStateOf("")

    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            articles = repository.getTopHeadlines(apiKey)
        }
    }

    suspend fun fetchLikes(articleId: String): Int {
        return repository.getLikes(articleId)
    }

    suspend fun fetchComments(articleId: String): Int {
        return repository.getComments(articleId)
    }
}