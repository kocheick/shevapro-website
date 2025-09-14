package com.shevapro.website.components.sections

import androidx.compose.runtime.Composable
import com.shevapro.website.models.Article
import org.jetbrains.compose.web.dom.*

/**
 * Section to display a titled list of articles with consistent layout and a "View All" button.
 */
@Composable
fun ArticleListSection(
    title: String,
    articles: List<Article>,
    emptyText: String,
    viewAllPath: String,
    renderCard: @Composable (Article) -> Unit
) {
    Section(attrs = { classes("mb-8") }) {
        H2(attrs = {
            classes(
                "text-3xl",
                "md:text-4xl",
                "text-gray-900",
                "p-2",
                "bg-white",
                "bg-opacity-70",
                "font-semibold",
                "rounded-lg",
                "shadow",
                "m-3",
                "mt-2"
            )
        }) { Text(title) }

        if (articles.isNotEmpty()) {
            Div(attrs = {
                classes(
                    "flex",
                    "flex-wrap",
                    "justify-center",
                    "gap-4",
                    "mt-6",
                    "bg-white",
                    "bg-opacity-60",
                    "backdrop-blur-sm",
                    "rounded-xl",
                    "p-4"
                )
            }) {
                articles.forEach { article ->
                    renderCard(article)
                }
            }
        } else {
            Div(attrs = {
                classes(
                    "bg-white",
                    "rounded-lg",
                    "shadow",
                    "p-6",
                    "text-gray-700",
                    "text-center",
                    "mt-6"
                )
            }) {
                Text(emptyText)
            }
        }

        Div(attrs = { classes("text-center", "mt-8") }) {
            A(
                href = viewAllPath,
                attrs = {
                    classes(
                        "inline-block",
                        "px-6",
                        "py-3",
                        "bg-purple-600",
                        "hover:bg-purple-700",
                        "text-white",
                        "font-semibold",
                        "rounded-lg",
                        "shadow-md",
                        "hover:shadow-lg",
                        "transition-all",
                        "duration-300",
                        "transform",
                        "hover:scale-105"
                    )
                }
            ) { Text("View All →") }
        }
    }
}
