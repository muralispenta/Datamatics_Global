package com.example.datamastictask


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AppNavigation(apiKey: String) {
    var selectedArticle by remember { mutableStateOf<Article?>(null) }
    val viewModel = remember { NewsViewModel(NewsRepository()) }

    if (selectedArticle == null) {
        NewsListScreen(viewModel, apiKey) { article ->
            selectedArticle = article
        }
    } else {
        NewsDetailScreen(viewModel, selectedArticle!!) {
            selectedArticle = null
        }
    }
}
