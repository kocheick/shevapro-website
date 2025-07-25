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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .minHeight(100.vh)
                .backgroundColor(rgba(147, 197, 253, 0.2)) // Simple blue background instead of gradient
                .padding(SiteTheme.Spacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth(75.percent)
                    .backgroundColor(rgba(253, 224, 71, 0.8)) // Simple yellow background
                    .borderRadius(16.px)
                    .padding(SiteTheme.Spacing.lg)
                    .margin(topBottom = SiteTheme.Spacing.md)
            ) {
                Column {
                    H1(
                        attrs = {
                            style {
                                fontSize(32.px) // text-2xl md:text-4xl
                                fontWeight("bold")
                                color(SiteTheme.Colors.text)
                                margin(4.px)
                                marginBottom(SiteTheme.Spacing.md)
                            }
                        }
                    ) {
                        Text("Projects, Concepts exploration and Playground..")
                    }

                    Br()

                    P(
                        attrs = {
                            style {
                                fontSize(16.px) // md:text-lg
                                color(SiteTheme.Colors.text)
                                marginBottom(SiteTheme.Spacing.md)
                            }
                        }
                    ) {
                        Text("Showcasing a few projects I have worked on (from creation to finish), challenges on the way and lessons learned.")
                    }

                    P(
                        attrs = {
                            style {
                                fontSize(14.px) // text-sm md:text-lg
                                backgroundColor(rgba(249, 250, 251, 0.6)) // bg-gray-50 bg-opacity-60
                                padding(8.px)
                                borderRadius(12.px)
                                maxWidth("fit-content")
                            }
                        }
                    ) {
                        Text("You can filter articles by using tags right below!")
                    }
                }
            }

            // Filters Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = 20.px)
            ) {
                FiltersBar(
                    tags = allTags,
                    activeTags = activeTags,
                    onTagClick = { clickedTag ->
                        activeTags = if (activeTags.contains(clickedTag)) {
                            activeTags - clickedTag
                        } else {
                            activeTags + clickedTag
                        }
                    }
                )
            }

            // Projects Grid
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.px),
                contentAlignment = Alignment.Center
            ) {
                if (filteredProjects.isEmpty()) {
                    P(
                        attrs = {
                            style {
                                fontSize(16.px)
                                color(SiteTheme.Colors.textSecondary)
                                textAlign("center")
                                marginTop(SiteTheme.Spacing.xl)
                            }
                        }
                    ) {
                        Text(if (activeTags.isEmpty()) "No projects available." else "No projects found with the selected tags.")
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .flexWrap(FlexWrap.Wrap)
                            .justifyContent(JustifyContent.SpaceAround)
                            .gap(SiteTheme.Spacing.md)
                    ) {
                        filteredProjects.forEach { project ->
                            ProjectCard(
                                project = project,
                                onTagClick = { clickedTag ->
                                    activeTags = if (activeTags.contains(clickedTag)) {
                                        activeTags - clickedTag
                                    } else {
                                        activeTags + clickedTag
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}