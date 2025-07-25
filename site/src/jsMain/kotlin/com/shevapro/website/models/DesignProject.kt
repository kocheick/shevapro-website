package com.shevapro.website.models

import kotlinx.serialization.Serializable

/**
 * Represents a design project.
 * 
 * @property id Unique identifier for the design project
 * @property title Title of the design project
 * @property slug URL-friendly version of the title for routing
 * @property description Short description of the design project
 * @property fullDescription Detailed description of the design project
 * @property thumbnailUrl URL to the thumbnail image
 * @property imageUrls List of URLs to project images
 * @property tools List of design tools used in the project (e.g., Figma, Photoshop)
 * @property client Client information (if applicable)
 * @property projectUrl URL to the live project (if available)
 * @property behanceUrl URL to the Behance project (if available)
 * @property dribbbleUrl URL to the Dribbble project (if available)
 * @property featured Whether this project should be featured on the homepage
 * @property date Date when the project was completed (ISO format: YYYY-MM-DD)
 * @property category Category of the design project (e.g., UI/UX, Branding, Illustration)
 * @property colors List of color hex codes used in the design
 * @property typography Information about typography used in the design
 */
@Serializable
data class DesignProject(
    val id: String,
    val title: String,
    val slug: String,
    val description: String,
    val fullDescription: String,
    val thumbnailUrl: String,
    val imageUrls: List<String> = emptyList(),
    val tools: List<String> = emptyList(),
    val client: String? = null,
    val projectUrl: String? = null,
    val behanceUrl: String? = null,
    val dribbbleUrl: String? = null,
    val featured: Boolean = false,
    val date: String,
    val category: String,
    val colors: List<String> = emptyList(),
    val typography: String? = null
)