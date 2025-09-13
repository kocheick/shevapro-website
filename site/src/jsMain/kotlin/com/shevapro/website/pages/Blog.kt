package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.widgets.BlogPostCard
import com.shevapro.website.models.Article
import com.shevapro.website.utils.getArticles
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.*

@Page("/blog")
@Composable
fun BlogPage() {
    val blogArticles = remember { getArticles("blog") }
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
                        it.description.contains(searchQuery, ignoreCase = true) ||
                        it.tags.contains(searchQuery)
            }
        }
    }
    val router = rememberPageContext().router

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

                BlogListSection(filteredArticles,
                    onClick = { slug -> router.navigateTo("/blog/$slug") })
            }
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
    articles: List<Article>,
    onClick: (String) -> Unit = {}
) {
        if (articles.isEmpty()) {
            P(attrs = { attr("class", "text-center text-white text-lg my-8") }) {
                Text("No blog articles found.")
            }
        } else {
            Div(attrs = { attr("class", "grid grid-cols-1 gap-8") }) {
                articles.forEach { article ->
                    BlogPostCard(
                        article = article,
                        onClick = { onClick(article.slug) }
                    )
                }
            }
        }

}