package com.shevapro.website.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.styles.MarkdownStyles
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Layout
@Composable
fun MarkdownLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val title = ctx.markdown!!.frontMatter["title"]?.single() ?: "Article"
    val description = ctx.markdown!!.frontMatter["description"]?.single() ?: "Article"
    val thumbnailUrl = ctx.markdown?.frontMatter?.get("thumbnailUrl")?.single()
    val coverUrl =
        if (thumbnailUrl != null && thumbnailUrl.length > 42) thumbnailUrl else "/assets/images/${thumbnailUrl ?: "blank-image.jpeg"}"

    Layout(title = "SHEVAPRO | $title", description =description ) {
        // Add CSS styles for markdown content
        Style {
            MarkdownStyles.markdownContentCSS
        }

        // Main container with gradient background
        Div(
            attrs = {
                classes("rounded", "min-h-full", "pt-8")
            }
        ) {
            // Cover image + title section
            Section(
                attrs = {
                    classes("bg-slate-100", "flex", "flex-col", "justify-around", "items-center", "p-2")
//                    style {
//                        backgroundColor(rgb(241, 245, 249))
//                        display(DisplayStyle.Flex)
//                        flexDirection(FlexDirection.Column)
//                        justifyContent(JustifyContent.SpaceAround)
//                        alignItems(AlignItems.Center)
//                        padding(8.px)
//                    }
                }
            ) {
                // Cover image
                Img(
                    src = coverUrl,
                    attrs = {
                        classes("rounded")
                        style {
                            width(500.px)
                        }
                    }
                )

                // Title
                H1(
                    attrs = {
                        classes(
                            "m-4",
                            "w-full",
                            "font-mono",
                            "text-center",
                            "underline",
                            "text-2xl",
                            "font-bold",
                            "md:text-4xl"
                        )

                    }
                ) {
                    Text(title)
                }
            }

            // Article content section
            Section(
                attrs = {
                    style {
                        height(100.percent)
                    }
                }
            ) {
                Article(
                    attrs = {
                        classes("markdown-content", "px-2", "min-h-full", "md:px-5", "font-sans")
                    }
                ) {
                    content()
                }
            }
        }
    }
}