package com.example.datamastictask

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsDetailScreen(viewModel: NewsViewModel, article: Article, onBack: () -> Unit) {
    val articleId = article.url.replace("https://", "").replace("/", "-")
    var likes by remember { mutableStateOf(0) }
    var comments by remember { mutableStateOf(0) }

    LaunchedEffect(articleId) {
        likes = viewModel.fetchLikes(articleId)
        comments = viewModel.fetchComments(articleId)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = onBack) {
            Text("Back")
        }
        Scaffold {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = article.title, style = MaterialTheme.typography.titleMedium)
                article.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                article.author?.let {
                    Text(
                        text = "By $it",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                article.urlToImage?.let {
                    Image(
                        painter = rememberImagePainter(it),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(250.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Likes: $likes", style = MaterialTheme.typography.bodySmall)
                Text(text = "Comments: $comments", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}