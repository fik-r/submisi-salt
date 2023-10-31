package com.example.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseArticles(
    @SerializedName("articles")
    val results: List<ResponseArticle>
)