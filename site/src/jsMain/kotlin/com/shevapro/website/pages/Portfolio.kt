package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HeroSection
import com.shevapro.website.components.widgets.FiltersBar
import com.shevapro.website.components.widgets.ProjectCard
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.utils.Constants
import com.shevapro.website.utils.getArticles
import com.varabyte.kobweb.compose.css.backgroundImage
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Page("/portfolio")
@Composable
fun PortfolioPage() {
    // Articles from markdown files
    val projectArticles = remember { getArticles("portfolio") }

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

    Layout(title = Constants.Pages.Portfolio.TITLE, description = Constants.Pages.Portfolio.DESCRIPTION) {
        // Page flex container (main background)
        Div(
            attrs = {
                classes(
                    "rounded-2xl", "min-h-screen", "flex", "flex-col", "items-center",
                    "bg-opacity-40", "bg-gradient-to-bl", "from-red-200", "via-purple-300", "to-blue-500", "pt-8"
                )

            }
        ) {
            // Hero section using the reusable component
            HeroSection(
                title = Constants.Pages.Portfolio.HEADLINE,
                description = Constants.Pages.Portfolio.SECONDARY_HEADLINE
                // Uses default gradient which matches the original
            )

            // Filter instruction note
            Section(
                attrs = {
                    classes("w-4/5", "max-w-4xl", "mb-4")
                }
            ) {
                P(attrs = {
                    classes(
                        "w-fit",
                        "mx-auto",
                        "p-3",
                        "bg-gray-50",
                        "bg-opacity-80",
                        "rounded-xl",
                        "text-sm",
                        "md:text-lg",
                        "text-gray-800",
                        "font-medium"
                    )
                }) {
                    Text("You can filter articles by using tags right below!")
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