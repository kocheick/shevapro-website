package com.shevapro.website.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val slug: String = id,
    val title: String,
    val content: String,
    val description: String,
    val author: String,
    val dateAdded: String,
    val tags: List<String> = emptyList(),
    val imageUrl: String = "/assets/images/blank-image.webp",
    val coverImage: String? = null,
    val readTime: Int = 0, // in minutes
) {
    companion object {
        val BLANK_ARTICLE = Article(
            id = "blank",
            title = "Article not found",
            content = "",
            description = "This article could not be found.",
            author = "",
            dateAdded = "",
            tags = emptyList(),
            imageUrl = "/assets/images/blank-image.webp"
        )
    }
}