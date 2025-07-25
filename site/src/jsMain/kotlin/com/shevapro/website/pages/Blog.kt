package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.models.Article
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.*

@Page("/blog")
@Composable
fun BlogPage() {
    // Sample blog articles (will be replaced with real markdown articles later)
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
    var isLoading by remember { mutableStateOf(false) }

    // Filter state
    val allTags = remember(blogArticles) {
        blogArticles.flatMap { it.tags }.distinct().sorted()
    }
    var activeTags by remember { mutableStateOf<List<String>>(emptyList()) }

    // Filtered blog articles
    val filteredArticles = remember(blogArticles, activeTags) {
        if (activeTags.isEmpty()) {
            blogArticles
        } else {
            blogArticles.filter { article ->
                article.tags.any { tag -> activeTags.contains(tag) }
            }
        }
    }

    Layout(title = "Blog - Shevapro | Thoughts, stories, and ideas") {
        Div(attrs = {
            classes("display-block")
//            style {
//                display(DisplayStyle.Block)
//            }
        }) { // Blog page content
            BlogHeroSection()
            BlogFiltersSection(allTags, activeTags) { clickedTag ->
                activeTags = if (clickedTag == "") {
                    // Clear all tags when "All" is clicked
                    emptyList()
                } else if (activeTags.contains(clickedTag)) {
                    activeTags - clickedTag
                } else {
                    activeTags + clickedTag
                }
            }
            BlogListSection(filteredArticles, isLoading)
        }
    }
}

@Composable
private fun BlogHeroSection() {
    // TODO: Implement Blog hero section
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minHeight(40.vh)
            .padding(topBottom = SiteTheme.Spacing.xxl)
            .backgroundColor(SiteTheme.Colors.gray50),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px)
                .margin(topBottom = 0.px, leftRight = 0.px)
                .textAlign(TextAlign.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page Title
            H1(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(SiteTheme.Typography.h1)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = SiteTheme.Spacing.md)
                    .toAttrs()
            ) {
                Text("Blog")
            }
            
            // Subtitle
            H2(
                attrs = Modifier
                    .color(SiteTheme.Colors.textSecondary)
                    .fontSize(SiteTheme.Typography.h4)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = SiteTheme.Spacing.lg)
                    .toAttrs()
            ) {
                Text("Thoughts, stories, and ideas")
            }
            
            // Brief introduction
            P(
                attrs = Modifier
                    .color(SiteTheme.Colors.textSecondary)
                    .fontSize(SiteTheme.Typography.body)
                    .maxWidth(800.px)
                    .margin(bottom = SiteTheme.Spacing.xl)
                    .lineHeight(1.6)
                    .toAttrs()
            ) {
                Text("Explore my latest articles on technology, design, and development. I share insights, tutorials, and personal experiences from my journey in the tech world.")
            }
        }
    }
}

@Composable
private fun BlogFiltersSection(
    tags: List<String>,
    activeTags: List<String>,
    onTagClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topBottom = SiteTheme.Spacing.lg),
        contentAlignment = Alignment.Center
    ) {
        Div(
            attrs = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px)
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .justifyContent(org.jetbrains.compose.web.css.JustifyContent.Center)
                .gap(SiteTheme.Spacing.md)
                .toAttrs()
        ) {
            // Search bar placeholder - will be implemented later
            Div(
                attrs = Modifier
                    .width(300.px)
                    .height(40.px)
                    .padding(leftRight = SiteTheme.Spacing.md)
                    .border(1.px, LineStyle.Solid, SiteTheme.Colors.gray100)
                    .borderRadius(SiteTheme.BorderRadius.md)
                    .backgroundColor(SiteTheme.Colors.surface)
                    .display(DisplayStyle.Flex)
                    .alignItems(AlignItems.Center)
                    .toAttrs()
            ) {
                Text("Search articles...")
            }
            
            // Tag filter buttons
            if (tags.isEmpty()) {
                // Show placeholder categories if no tags are available
                listOf("All", "Technology", "Design", "Development", "Personal").forEach { category ->
                    Button(
                        attrs = Modifier
                            .padding(SiteTheme.Spacing.sm)
                            .backgroundColor(if (category == "All") SiteTheme.Colors.primary else SiteTheme.Colors.surface)
                            .color(if (category == "All") rgb(255, 255, 255) else SiteTheme.Colors.text)
                            .borderRadius(SiteTheme.BorderRadius.md)
                            .padding(leftRight = SiteTheme.Spacing.md, topBottom = SiteTheme.Spacing.sm)
                            .border(1.px, LineStyle.Solid, if (category == "All") SiteTheme.Colors.primary else rgba(0, 0, 0, 0.1))
                            .cursor(Cursor.Pointer)
                            .toAttrs()
                    ) {
                        Text(category)
                    }
                }
            } else {
                // Show actual tags from articles
                Button(
                    attrs = Modifier
                        .padding(SiteTheme.Spacing.sm)
                        .backgroundColor(if (activeTags.isEmpty()) SiteTheme.Colors.primary else SiteTheme.Colors.surface)
                        .color(if (activeTags.isEmpty()) rgb(255, 255, 255) else SiteTheme.Colors.text)
                        .borderRadius(SiteTheme.BorderRadius.md)
                        .padding(leftRight = SiteTheme.Spacing.md, topBottom = SiteTheme.Spacing.sm)
                        .border(1.px, LineStyle.Solid, if (activeTags.isEmpty()) SiteTheme.Colors.primary else rgba(0, 0, 0, 0.1))
                        .cursor(Cursor.Pointer)
                        .onClick { /* Clear all active tags */ onTagClick("") }
                        .toAttrs()
                ) {
                    Text("All")
                }
                
                tags.forEach { tag ->
                    Button(
                        attrs = Modifier
                            .padding(SiteTheme.Spacing.sm)
                            .backgroundColor(if (activeTags.contains(tag)) SiteTheme.Colors.primary else SiteTheme.Colors.surface)
                            .color(if (activeTags.contains(tag)) rgb(255, 255, 255) else SiteTheme.Colors.text)
                            .borderRadius(SiteTheme.BorderRadius.md)
                            .padding(leftRight = SiteTheme.Spacing.md, topBottom = SiteTheme.Spacing.sm)
                            .border(1.px, LineStyle.Solid, if (activeTags.contains(tag)) SiteTheme.Colors.primary else rgba(0, 0, 0, 0.1))
                            .cursor(Cursor.Pointer)
                            .onClick { onTagClick(tag) }
                            .toAttrs()
                    ) {
                        Text(tag)
                    }
                }
            }
        }
    }
}

@Composable
private fun BlogListSection(
    articles: List<Article>,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topBottom = SiteTheme.Spacing.xl),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            // Loading state
            P(
                attrs = Modifier
                    .fontSize(18.px)
                    .color(SiteTheme.Colors.textSecondary)
                    .textAlign(TextAlign.Center)
                    .toAttrs()
            ) {
                Text("Loading blog articles...")
            }
        } else if (articles.isEmpty()) {
            // No articles state
            P(
                attrs = Modifier
                    .fontSize(18.px)
                    .color(SiteTheme.Colors.textSecondary)
                    .textAlign(TextAlign.Center)
                    .toAttrs()
            ) {
                Text("No blog articles found.")
            }
        } else {
            // Display articles
            Column(
                modifier = Modifier
                    .fillMaxWidth(90.percent)
                    .maxWidth(1200.px)
                    .gap(SiteTheme.Spacing.xl),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
}

@Composable
private fun BlogPostCard(
    title: String, 
    summary: String, 
    date: String, 
    tags: List<String>,
    slug: String
) {
    Div(
        attrs = Modifier
            .fillMaxWidth()
            .padding(SiteTheme.Spacing.lg)
            .backgroundColor(SiteTheme.Colors.background)
            .borderRadius(SiteTheme.Spacing.sm)
            .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 6.px, color = rgba(0, 0, 0, 0.1))
            .toAttrs()
    ) {
        // Title with link to article
        Link(
            path = "/blog/$slug",
            modifier = Modifier
                .textDecorationLine(TextDecorationLine.None)
                .fillMaxWidth()
        ) {
            H3(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(SiteTheme.Typography.h3)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = SiteTheme.Spacing.sm)
                    .toAttrs()
            ) {
                Text(title)
            }
        }
        
        P(
            attrs = Modifier
                .color(SiteTheme.Colors.textSecondary)
                .fontSize(SiteTheme.Typography.small)
                .margin(bottom = SiteTheme.Spacing.md)
                .toAttrs()
        ) {
            Text(date)
        }
        
        P(
            attrs = Modifier
                .color(SiteTheme.Colors.textSecondary)
                .fontSize(SiteTheme.Typography.body)
                .margin(bottom = SiteTheme.Spacing.md)
                .lineHeight(1.6)
                .toAttrs()
        ) {
            Text(summary)
        }
        
        // Read more link
        Link(
            path = "/blog/$slug",
            modifier = Modifier
                .color(SiteTheme.Colors.primary)
                .fontSize(SiteTheme.Typography.body)
                .fontWeight(FontWeight.SemiBold)
                .margin(bottom = SiteTheme.Spacing.md)
                .textDecorationLine(TextDecorationLine.None)
                .display(DisplayStyle.Block)
        ) {
            Text("Read more →")
        }
        
        Div(
            attrs = Modifier
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .gap(SiteTheme.Spacing.sm)
                .toAttrs()
        ) {
            tags.forEach { tag ->
                Span(
                    attrs = Modifier
                        .backgroundColor(SiteTheme.Colors.gray100)
                        .color(SiteTheme.Colors.textSecondary)
                        .padding(leftRight = SiteTheme.Spacing.sm, topBottom = SiteTheme.Spacing.xs)
                        .borderRadius(SiteTheme.Spacing.xs)
                        .fontSize(SiteTheme.Typography.small)
                        .toAttrs()
                ) {
                    Text(tag)
                }
            }
        }
    }
}