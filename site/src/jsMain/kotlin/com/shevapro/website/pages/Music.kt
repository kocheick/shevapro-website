package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun MusicPage() {
    Layout {
        // Music page content
        MusicHeroSection()
        MusicFiltersSection()
        MusicPlayerSection()
    }
}

@Composable
private fun MusicHeroSection() {
    // TODO: Implement Music hero section
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
                Text("Music")
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
                Text("Listen to my latest tracks and compositions")
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
                Text("Explore my musical creations spanning various genres and styles. Each track represents a unique expression of creativity and emotion.")
            }
        }
    }
}

@Composable
private fun MusicFiltersSection() {
    // TODO: Implement music filters section
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
            // Filter buttons placeholder
            listOf("All", "Electronic", "Ambient", "Rock", "Classical").forEach { category ->
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
        }
    }
}

@Composable
private fun MusicPlayerSection() {
    // TODO: Implement music player section
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px)
                .gap(SiteTheme.Spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder for music player
            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .padding(SiteTheme.Spacing.lg)
                    .backgroundColor(SiteTheme.Colors.surface)
                    .borderRadius(SiteTheme.BorderRadius.lg)
                    .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 8.px, color = rgba(0, 0, 0, 0.05))
                    .toAttrs()
            ) {
                // Music player placeholder
                Div(
                    attrs = Modifier
                        .fillMaxWidth()
                        .height(80.px)
                        .display(DisplayStyle.Flex)
                        .alignItems(AlignItems.Center)
                        .justifyContent(org.jetbrains.compose.web.css.JustifyContent.SpaceBetween)
                        .padding(SiteTheme.Spacing.md)
                        .toAttrs()
                ) {
                    // Track info
                    Div(
                        attrs = Modifier
                            .display(DisplayStyle.Flex)
                            .flexDirection(FlexDirection.Column)
                            .gap(SiteTheme.Spacing.xs)
                            .toAttrs()
                    ) {
                        // Track title
                        H3(
                            attrs = Modifier
                                .color(SiteTheme.Colors.text)
                                .fontSize(SiteTheme.Typography.body)
                                .fontWeight(FontWeight.Bold)
                                .margin(0.px)
                                .toAttrs()
                        ) {
                            Text("Sample Track Title")
                        }
                        
                        // Track genre
                        P(
                            attrs = Modifier
                                .color(SiteTheme.Colors.textSecondary)
                                .fontSize(SiteTheme.Typography.small)
                                .margin(0.px)
                                .toAttrs()
                        ) {
                            Text("Electronic")
                        }
                    }
                    
                    // Player controls placeholder
                    Div(
                        attrs = Modifier
                            .display(DisplayStyle.Flex)
                            .alignItems(AlignItems.Center)
                            .gap(SiteTheme.Spacing.md)
                            .toAttrs()
                    ) {
                        // Play button placeholder
                        Button(
                            attrs = Modifier
                                .backgroundColor(SiteTheme.Colors.primary)
                                .color(rgb(255, 255, 255))
                                .borderRadius(50.percent)
                                .width(40.px)
                                .height(40.px)
                                .border(0.px)
                                .cursor(Cursor.Pointer)
                                .toAttrs()
                        ) {
                            Text("▶")
                        }
                    }
                }
            }
            
            // Playlist placeholder
            repeat(3) { index ->
                MusicTrackItem(
                    title = "Track ${index + 1}",
                    genre = when (index % 3) {
                        0 -> "Electronic"
                        1 -> "Ambient"
                        else -> "Classical"
                    },
                    duration = "${index + 3}:${(index * 15) + 10}"
                )
            }
        }
    }
}

@Composable
private fun MusicTrackItem(title: String, genre: String, duration: String) {
    Div(
        attrs = Modifier
            .fillMaxWidth()
            .padding(SiteTheme.Spacing.md)
            .backgroundColor(SiteTheme.Colors.surface)
            .borderRadius(SiteTheme.BorderRadius.md)
            .display(DisplayStyle.Flex)
            .justifyContent(org.jetbrains.compose.web.css.JustifyContent.SpaceBetween)
            .alignItems(AlignItems.Center)
            .cursor(Cursor.Pointer)
            .toAttrs()
    ) {
        // Track info
        Div(
            attrs = Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .gap(SiteTheme.Spacing.xs)
                .padding(SiteTheme.Spacing.md)
                .toAttrs()
        ) {
            // Track title
            H3(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(SiteTheme.Typography.body)
                    .fontWeight(FontWeight.Bold)
                    .margin(0.px)
                    .toAttrs()
            ) {
                Text(title)
            }
            
            // Track genre
            P(
                attrs = Modifier
                    .color(SiteTheme.Colors.textSecondary)
                    .fontSize(SiteTheme.Typography.small)
                    .margin(0.px)
                    .toAttrs()
            ) {
                Text(genre)
            }
        }
        
        // Track duration
        P(
            attrs = Modifier
                .color(SiteTheme.Colors.textSecondary)
                .fontSize(SiteTheme.Typography.small)
                .margin(0.px)
                .padding(SiteTheme.Spacing.md)
                .toAttrs()
        ) {
            Text(duration)
        }
    }
}