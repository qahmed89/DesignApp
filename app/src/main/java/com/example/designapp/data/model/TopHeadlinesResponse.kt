package com.example.designapp.data.model

data class TopHeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)