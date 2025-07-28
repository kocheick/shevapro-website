package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.widgets.FiltersBar
import com.shevapro.website.components.widgets.ProjectCard
import com.shevapro.website.models.Article
import com.shevapro.website.styles.SiteTheme
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Page("/portfolio")
@Composable
fun PortfolioPage() {
    // Articles that correspond to the markdown files
    val projectArticles = remember {
        listOf(
            Article(
                id = "file-sorter-app",
                slug = "file-sorter-app",
                title = "File Sorter",
                content = "",
                description = "Your ultimate solution for keeping your digital life neat and tidy! With File Sorter, you can easily organize your files on your Android device by moving them from one folder to another based on their extensions.",
                author = "Shevapro",
                dateAdded = "September 8, 2023",
                tags = listOf("android", "app", "hobby", "productivity"),
                imageUrl = "/assets/images/file-sorter-logo.png",
                isPortfolioArticle = true,
                posted = true
            ),
            Article(
                id = "example",
                slug = "example",
                title = "Example Project",
                content = "",
                description = "This is an example project showcasing various markdown features and formatting options.",
                author = "Shevapro",
                dateAdded = "January 1, 2024",
                tags = listOf("example", "demo", "markdown"),
                imageUrl = "/assets/images/blank-image.jpeg",
                isPortfolioArticle = true,
                posted = true
            ),
            Article(
                id = "markdown-example",
                slug = "markdown-example",
                title = "Markdown Example",
                content = "",
                description = "A comprehensive example showcasing various markdown formatting features and capabilities.",
                author = "Shevapro",
                dateAdded = "December 1, 2023",
                tags = listOf("markdown", "example", "formatting", "demo"),
//                imageUrl = "/assets/images/file-sorter-logo.png",
                isPortfolioArticle = true,
                posted = true
            ),
            // Keep some sample articles as well
            Article(
                id = "android-music-player",
                slug = "android-music-player",
                title = "Android Music Player App",
                content = "A fully-featured music player app built with Kotlin and modern Android architecture components.",
                description = "A fully-featured music player app built with Kotlin and modern Android architecture components including MVVM, Room Database, and ExoPlayer.",
                author = "Shevapro",
                dateAdded = "March 15, 2024",
                tags = listOf("Android", "Kotlin", "ExoPlayer", "MVVM", "Room"),
//                imageUrl = "/favicon.ico",
                isPortfolioArticle = true,
                posted = true
            ),
            Article(
                id = "ecommerce-platform",
                slug = "ecommerce-platform",
                title = "E-commerce Web Platform",
                content = "Full-stack e-commerce platform with user authentication, payment processing, and admin dashboard.",
                description = "Full-stack e-commerce platform with user authentication, payment processing, and admin dashboard built with Ktor and React.",
                author = "Shevapro",
                dateAdded = "February 20, 2024",
                tags = listOf("Ktor", "React", "PostgreSQL", "Stripe", "JavaScript"),
//                imageUrl = "/favicon.ico",
                isPortfolioArticle = true,
                posted = true
            )
        )
    }

    // Filter state
    val allTags = remember(projectArticles) {
        projectArticles.flatMap { it.tags }.distinct().sorted()
    }
    var activeTags by remember { mutableStateOf<List<String>>(emptyList()) }

    // Filtered projects
    val filteredProjects = remember(projectArticles, activeTags) {
        if (activeTags.isEmpty()) {
            projectArticles
        } else {
            projectArticles.filter { project ->
                project.tags.any { tag -> activeTags.contains(tag) }
            }
        }
    }

    Layout(title = "Portfolio - Freelance Android Developer | Custom Apps and projects for Clients Across Industries.") {
        // Page flex container (main background)
        Div(
            attrs = {
                classes(
                    "rounded-2xl", "min-h-screen", "flex", "flex-col", "items-center",
                    "bg-opacity-40", "bg-gradient-to-bl", "from-red-200", "via-purple-300", "to-blue-500", "pt-8"
                )
            }
        ) {
            // Header section (banner)
            Section(
                attrs = {
                    classes("w-3/4", "my-3", "bg-gradient-to-bl", "from-yellow-300", "to-red-200", "rounded-2xl", "p-4")
                }
            ) {
                Div(attrs = { classes("text-lg", "rounded-2xl") }) {
                    H1(attrs = { classes("font-bold", "m-1", "text-2xl", "md:text-4xl") }) {
                        Text("Projects, Concepts exploration and Playground..")
                    }
                    Br {}
                    P(attrs = { classes("md:m-1", "text-sm", "md:text-lg") }) {
                        Text("Showcasing a few projects I have worked on (from creation to finish), challenges on the way and lessons learned.")
                    }
                    P(attrs = {
                        classes(
                            "w-fit",
                            "p-2",
                            "bg-gray-50",
                            "bg-opacity-60",
                            "rounded-xl",
                            "text-sm",
                            "md:text-lg"
                        )
                    }) {
                        Text("You can filter articles by using tags right below!")
                    }
                }
            }
            // Filters (Tags) bar
            Section(attrs = { classes("mt-5", "w-full") }) {
                FiltersBar(
                    tags = allTags,
                    activeTags = activeTags,
                    onTagClick = { clickedTag ->
                        activeTags = if (activeTags.contains(clickedTag)) {
                            activeTags - clickedTag
                        } else {
                            activeTags + clickedTag
                        }
                    },
                )
            }
            // Project cards grid
            Section(attrs = { classes("flex", "justify-around", "flex-wrap", "w-full", "p-1") }) {
                if (filteredProjects.isEmpty()) {
                    P(attrs = { classes("text-lg", "text-gray-700", "text-center", "mt-10") }) {
                        Text(if (activeTags.isEmpty()) "No projects available." else "No projects found with the selected tags.")
                    }
                } else {
                    filteredProjects.forEach { project ->
                        ProjectCard(
                            project = project,
                            onTagClick = { clickedTag ->
                                activeTags = if (activeTags.contains(clickedTag)) {
                                    activeTags - clickedTag
                                } else {
                                    activeTags + clickedTag
                                }
                            })
                    }
                }
            }
        }
    }
}