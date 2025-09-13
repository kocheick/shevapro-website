package com.shevapro.website.components.widgets

import androidx.compose.runtime.Composable
import com.shevapro.website.models.Article
import org.jetbrains.compose.web.dom.*

/**
 * Blog post summary card.
 */
@Composable
fun BlogPostCard(
    article: Article,
    onClick: (() -> Unit)? = null
) {
    Div(attrs = {
        classes("w-full", "p-6", "bg-white", "rounded-lg", "shadow-md", "transition-all", "hover:shadow-xl")
    }) {
        A(href = "/blog/${'$'}{article.slug}", attrs = { classes("no-underline", "w-full", "inline-block") }) {
            H3(attrs = { classes("text-gray-900", "text-2xl", "font-bold", "mb-2") }) {
                Text(article.title)
            }
        }
        P(attrs = { classes("text-gray-600", "text-xs", "mb-4") }) { Text(article.dateAdded) }
        P(attrs = { classes("text-gray-700", "text-base", "mb-4", "leading-relaxed") }) {
            Text(article.description)
        }
        A(
            href = "/blog/${'$'}{article.slug}",
            attrs = {
                onClick {
                    it.preventDefault()
                    onClick?.invoke()
                }
                classes(
                    "text-blue-500",
                    "text-base",
                    "font-semibold",
                    "mb-4",
                    "no-underline",
                    "inline-block",
                    "hover:text-blue-700"
                )
            }
        ) { Text("Read more →") }
        Div(attrs = { classes("flex", "flex-wrap", "gap-2") }) {
            article.tags.forEach { tag ->
                Span(attrs = { classes("bg-gray-200", "text-gray-800", "px-2", "py-1", "rounded", "text-xs") }) {
                    Text(tag)
                }
            }
        }
    }
}
