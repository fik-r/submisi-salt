package com.example.data.api.response

import com.example.model.Article
import com.example.model.Source

fun ResponseArticle.toModel(): Article {
    return Article(
        source = Source(this.source?.id.orEmpty(), this.source?.name.orEmpty()),
        author = this.author.orEmpty(),
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        url = this.url.orEmpty(),
        urlToImage = this.urlToImage.orEmpty(),
        publishedAt = this.publishedAt.orEmpty(),
        content = this.content.orEmpty()
    )
}

fun List<ResponseArticle>.toModel(): List<Article> {
    val list = mutableListOf<Article>()
    this.forEach {
        list.add(it.toModel())
    }
    return list
}