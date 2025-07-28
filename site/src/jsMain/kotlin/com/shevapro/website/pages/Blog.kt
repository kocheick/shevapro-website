package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.models.Article
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.navigation.BasePath.Companion.value
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Page("/blog")
@Composable
fun BlogPage() {
    val blogArticles = remember {
        listOf(
            Article(
                id = "welcome-to-my-blog",
                slug = "welcome-to-my-blog",
                title = "Welcome to My Blog",
                content = "This is my first blog post where I introduce myself and share my thoughts on technology.",
                description = "An introduction to my blog and what you can expect to find here. I'll be sharing insights about software development, technology trends, and my personal journey.",
                author = "Shevapro",
                dateAdded = "January 15, 2024",
                tags = listOf("personal", "introduction", "blog"),
                imageUrl = "/favicon.ico",
                isPortfolioArticle = false,
                posted = true
            ),
            Article(
                id = "kotlin-multiplatform-guide",
                slug = "kotlin-multiplatform-guide",
                title = "Getting Started with Kotlin Multiplatform",
                content = "A comprehensive guide to building cross-platform applications with Kotlin Multiplatform.",
                description = "Learn how to set up and build your first Kotlin Multiplatform project. This guide covers the basics and best practices for sharing code between Android, iOS, and web platforms.",
                author = "Shevapro",
                dateAdded = "February 2, 2024",
                tags = listOf("kotlin", "multiplatform", "mobile", "tutorial"),
                imageUrl = "/favicon.ico",
                isPortfolioArticle = false,
                posted = true
            ),
            Article(
                id = "compose-html-tips",
                slug = "compose-html-tips",
                title = "Building Web UIs with Compose HTML",
                content = "Tips and tricks for building modern web applications using Jetpack Compose for Web.",
                description = "Explore the power of Compose HTML for web development. Learn about best practices, performance optimization, and how to create responsive web applications.",
                author = "Shevapro",
                dateAdded = "March 10, 2024",
                tags = listOf("compose", "web", "ui", "kotlin"),
                imageUrl = "/favicon.ico",
                isPortfolioArticle = false,
                posted = true
            )
        )
    }
    var searchQuery by remember { mutableStateOf("") }
    val allTags = remember(blogArticles) {
        blogArticles.flatMap { it.tags }.distinct().sorted()
    }
    var activeTags by remember { mutableStateOf<List<String>>(emptyList()) }

    val filteredArticles = remember(blogArticles, activeTags, searchQuery) {
        val articlesWithTags = if (activeTags.isEmpty()) {
            blogArticles
        } else {
            blogArticles.filter { article ->
                article.tags.any { tag -> activeTags.contains(tag) }
            }
        }
        if (searchQuery.isBlank()) {
            articlesWithTags
        } else {
            articlesWithTags.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
                        it.tags.contains(searchQuery)
            }
        }
    }

    Layout(title = "Blog - Shevapro | Thoughts, stories, and ideas") {
        Div(attrs = {
            attr(
                "class",
                "min-h-screen w-full bg-gradient-to-tr from-purple-600 to-blue-300 p-2"
            )
        }) { // Blog page content

            Section(attrs = { attr("class", "w-full p-2 md:p-4") }) {
                SearchBar(onQueryChange = { query -> searchQuery = query })
                BlogFiltersSection(allTags, activeTags) { clickedTag ->
                    activeTags = if (activeTags.contains(clickedTag)) {
                        activeTags - clickedTag
                    } else {
                        activeTags + clickedTag
                    }
                }
            }

            Section(attrs = { attr("class", "w-full max-w-4xl mx-auto p-2 md:p-4") }) {

            BlogListSection(filteredArticles) }
        }
    }
}

@Composable
private fun SearchBar(onQueryChange: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    Div(attrs = { attr("class", "relative w-full max-w-md mx-auto my-4") }) {
        Input(
            type = InputType.Text,
            attrs = {
                attr(
                    "class",
                    "w-full p-3 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500"
                )
                attr("placeholder", "Search articles...")
                value(query)
                onInput { event ->
                    query = event.value
                    onQueryChange(event.value)
                }
            }
        )
    }
}

@Composable
private fun BlogFiltersSection(
    tags: List<String>,
    activeTags: List<String>,
    onTagClick: (String) -> Unit
) {
    Div(attrs = { classes("w-full", "md:w-4/5", "mx-auto") }) {
        Ul(attrs = { classes("flex", "my-3", "list-none", "flex-wrap", "justify-center", "p-0") }) {
            tags.forEach { tag ->
                val isActive = activeTags.contains(tag)
                val tagClass = if (isActive)
                    "bg-sky-700 text-white scale-110"
                else
                    "bg-sky-100 text-sky-800 hover:bg-sky-200"
                Li(attrs = {
                     classes(
                        "m-1",
                        "cursor-pointer",
                        "p-2",
                        "rounded-lg",
                        "text-sm",
                        "font-medium",
                        "transition-all",
                        *tagClass.split(" ").toTypedArray()
                    )
                    onClick { onTagClick(tag) }
                }) {
                    Text(tag)
                }
            }
        }
    }
}

@Composable
private fun BlogListSection(
    articles: List<Article>
) {
        if (articles.isEmpty()) {
            P(attrs = { attr("class", "text-center text-white text-lg my-8") }) {
                Text("No blog articles found.")
            }
        } else {
            Div(attrs = { attr("class", "grid grid-cols-1 gap-8") }) {
                articles.forEach { article ->
                    BlogPostCard(
                        title = article.title,
                        summary = article.description,
                        date = article.dateAdded,
                        tags = article.tags,
                        slug = article.slug
                    )
                }
            }
        }

}

@Composable
private fun BlogPostCard(
    title: String,
    summary: String,
    date: String,
    tags: List<String>,
    slug: String
) {
    Div(attrs = {
        attr("class", "w-full p-6 bg-white rounded-lg shadow-md transition-all hover:shadow-xl")
    }) {
        Link(path = "/blog/$slug", modifier = Modifier.classNames(*"no-underline w-full".split(" ").toTypedArray())) {
            H3(attrs = {
                attr("class", "text-gray-900 text-2xl font-bold mb-2")
            }) {
                Text(title)
            }
        }
        P(attrs = { attr("class", "text-gray-600 text-xs mb-4") }) {
            Text(date)
        }
        P(attrs = { attr("class", "text-gray-700 text-base mb-4 leading-relaxed") }) {
            Text(summary)
        }
        Link(
            path = "/blog/$slug",
            modifier = Modifier.classNames(
                "text-blue-500",
                "text-base",
                "font-semibold",
                "mb-4",
                "no-underline",
                "inline-block",
                "hover:text-blue-700"
            )
        ) {
            Text("Read more →")
        }
        Div(attrs = { attr("class", "flex flex-wrap gap-2") }) {
            tags.forEach { tag ->
                Span(attrs = {
                    attr("class", "bg-gray-200 text-gray-800 px-2 py-1 rounded text-xs")
                }) {
                    Text(tag)
                }
            }
        }
    }
}