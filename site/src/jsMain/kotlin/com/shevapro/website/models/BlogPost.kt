package com.shevapro.website.models

import kotlinx.serialization.Serializable

/**
 * Represents a blog post.
 * 
 * @property id Unique identifier for the blog post
 * @property title Title of the blog post
 * @property slug URL-friendly version of the title for routing
 * @property summary Short summary of the blog post
 * @property content Full content of the blog post (can be markdown or HTML)
 * @property author Author of the blog post
 * @property publishDate Date when the blog post was published (ISO format: YYYY-MM-DD)
 * @property updateDate Date when the blog post was last updated (ISO format: YYYY-MM-DD)
 * @property thumbnailUrl URL to the thumbnail image
 * @property tags List of tags associated with the blog post
 * @property featured Whether this blog post should be featured on the homepage
 * @property readTimeMinutes Estimated reading time in minutes
 * @property category Category of the blog post (e.g., Technology, Design, Tutorial)
 */
@Serializable
data class BlogPost(
    val id: String,
    val title: String,
    val slug: String,
    val summary: String,
    val content: String,
    val author: String,
    val publishDate: String,
    val updateDate: String? = null,
    val thumbnailUrl: String? = null,
    val tags: List<String> = emptyList(),
    val featured: Boolean = false,
    val readTimeMinutes: Int? = null,
    val category: String
)