package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.sections.HeroSection
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.utils.Constants
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
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
                // Gallery items placeholder
                repeat(9) { index ->
                    Div(
                        attrs = Modifier
                            .width(300.px)
                            .margin(SiteTheme.Spacing.md)
                            .toAttrs()
                    ) {
                        DesignGalleryItem(
                            imageUrl = "https://via.placeholder.com/300x300?text=Design+${index + 1}",
                            title = "Design Project ${index + 1}",
                            category = when (index % 3) {
                                0 -> "Branding"
                                1 -> "UI/UX"
                                else -> "Illustration"
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DesignGalleryItem(imageUrl: String, title: String, category: String) {
    Div(
        attrs = {
            classes(
                "bg-white",
                "bg-opacity-90",
                "rounded-2xl",
                "overflow-hidden",
                "shadow-lg",
                "hover:shadow-xl",
                "transition-all",
                "cursor-pointer"
            )
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