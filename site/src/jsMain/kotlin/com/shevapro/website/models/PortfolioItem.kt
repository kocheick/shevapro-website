package com.shevapro.website.models

import kotlinx.serialization.Serializable

/**
 * Represents a portfolio project item.
 * 
 * @property id Unique identifier for the portfolio item
 * @property title Title of the project
 * @property slug URL-friendly version of the title for routing
 * @property description Short description of the project
 * @property fullDescription Detailed description of the project
 * @property thumbnailUrl URL to the thumbnail image
 * @property imageUrls List of URLs to project images
 * @property technologies List of technologies used in the project
 * @property projectUrl URL to the live project (if available)
 * @property repositoryUrl URL to the project repository (if available)
 * @property featured Whether this project should be featured on the homepage
 * @property date Date when the project was completed (ISO format: YYYY-MM-DD)
 * @property category Category of the project (e.g., Web, Mobile, Design)
 */
@Serializable
data class PortfolioItem(
    val id: String,
    val title: String,
    val slug: String,
    val description: String,
    val fullDescription: String,
    val thumbnailUrl: String,
    val imageUrls: List<String> = emptyList(),
    val technologies: List<String> = emptyList(),
    val projectUrl: String? = null,
    val repositoryUrl: String? = null,
    val featured: Boolean = false,
    val date: String,
    val category: String
)