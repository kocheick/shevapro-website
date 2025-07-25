package com.shevapro.website.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: String,
    val title: String,
    val description: String,
    val longDescription: String,
    val technologies: List<String>,
    val imageUrl: String? = null,
    val githubUrl: String? = null,
    val demoUrl: String? = null,
    val featured: Boolean = false,
    val category: ProjectCategory,
    val dateCompleted: String
)

@Serializable
enum class ProjectCategory {
    WEB_DEVELOPMENT,
    MOBILE_DEVELOPMENT,
    DESKTOP_APPLICATION,
    DESIGN,
    OTHER
}