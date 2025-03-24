package com.example.datamastictask


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(viewModel: NewsViewModel, apiKey: String, onArticleClick: (Article) -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.fetchNews(apiKey)
    }

    Scaffold {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(viewModel.articles) { article ->
                NewsItem(article, onArticleClick)
            }
        }
    }
}

@Composable
fun NewsItem(article: Article, onArticleClick: (Article) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onArticleClick(article) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title, style = MaterialTheme.typography.titleMedium)
            article.description?.let { Text(text = it, style = MaterialTheme.typography.bodySmall) }
            article.author?.let { Text(text = "By $it", style = MaterialTheme.typography.bodySmall) }
            article.urlToImage?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
            }
        }
    }
}