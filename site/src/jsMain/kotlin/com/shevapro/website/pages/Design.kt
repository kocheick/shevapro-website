package com.shevapro.website.pages

import androidx.compose.runtime.Composable
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HeroSection
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.utils.Constants
import com.shevapro.website.utils.getArticles
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun DesignPage() {
    Layout(title = Constants.Pages.Design.TITLE, description = Constants.Pages.Design.DESCRIPTION) {
        // Main container with gradient background
        Div(
            attrs = {
                classes(
                    "min-h-screen",
                    "bg-gradient-to-bl",
                    "from-red-200",
                    "via-purple-300",
                    "to-blue-500",
                    "bg-opacity-40"
                )
            }
        ) {
            // Hero section using the reusable component
            HeroSection(
                title = "Design",
                subtitle = "Elegant, modern and creative",
                description = "Admire a blend of various visual projects I created in no particular order for artists, events and businesses.",
                // Override default gradient for Design page
                gradientFrom = "from-purple-300",
                gradientTo = "to-pink-200"
            )

            // Design gallery section
            DesignGallerySection()
        }
    }
}

@Composable
private fun DesignGallerySection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gallery grid
            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .display(DisplayStyle.Flex)
                    .flexWrap(FlexWrap.Wrap)
                    .justifyContent(org.jetbrains.compose.web.css.JustifyContent.Center)
                    .gap(SiteTheme.Spacing.lg)
                    .toAttrs()
            ) {
                // Get design articles
                val designArticles = getArticles("design")
                
                // Display design articles
                designArticles.forEach { article ->
                    Div(
                        attrs = Modifier
                            .width(300.px)
                            .margin(SiteTheme.Spacing.md)
                            .toAttrs()
                    ) {
                        // Get category from first 1-2 tags
                        val category = when {
                            article.tags.size >= 2 -> "${article.tags[0]}, ${article.tags[1]}"
                            article.tags.isNotEmpty() -> article.tags[0]
                            else -> "Design" // Fallback if no tags
                        }
                        
                        DesignGalleryItem(
                            imageUrl = article.imageUrl,
                            title = article.title,
                            category = category,
                            href = "/design/${article.slug}"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DesignGalleryItem(imageUrl: String, title: String, category: String, href: String) {
    Div(
        attrs = {
            classes(
                "bg-white",
                "bg-opacity-85", // Slightly darker content area (was 90)
                "rounded-2xl",
                "overflow-hidden",
                "shadow-lg",
                "hover:shadow-xl",
                "transition-all",
                "cursor-pointer"
            )
        }
    ) {
        A(
            href = href,
            attrs = {
                classes("block", "text-decoration-none", "text-inherit")
            }
        ) {
            // Image
            Img(
                src = imageUrl,
                attrs = Modifier
                    .width(100.percent)
                    .height(200.px)
                    .objectFit(ObjectFit.Cover)
                    .toAttrs()
            )

            // Content
            Div(
                attrs = Modifier
                    .padding(SiteTheme.Spacing.md)
                    .toAttrs()
            ) {
                // Category
                Span(
                    attrs = {
                        classes("text-purple-600", "text-sm", "font-medium")
                    }
                ) {
                    Text(category)
                }

                // Title
                H3(
                    attrs = {
                        classes("text-gray-900", "text-lg", "font-bold", "mt-2", "mb-0")
                    }
                ) {
                    Text(title)
                }
            }
        }
    }
}