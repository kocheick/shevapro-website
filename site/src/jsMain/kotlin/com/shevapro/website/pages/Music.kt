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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minHeight(50.vh)
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        // Background div with gradient overlay
        Div(
            attrs = {
                style {
                    position(Position.Absolute)
                    top(0.px)
                    left(0.px)
                    right(0.px)
                    bottom(0.px)
                    property("background-image", "linear-gradient(135deg, rgba(124, 58, 237, 0.8), rgba(59, 130, 246, 0.8)), url('/assets/images/blank-image.jpeg')")
                    property("background-size", "cover")
                    property("background-position", "center")
                    zIndex(-1)
                }
            }
        ) {}
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
                    .color(rgb(255, 255, 255))
                    .fontSize(SiteTheme.Typography.h1)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = SiteTheme.Spacing.md)
                    .textShadow(offsetX = 2.px, offsetY = 2.px, blurRadius = 3.px, color = rgba(0, 0, 0, 0.3))
                    .toAttrs()
            ) {
                Text("Music Studio")
            }
            
            // Subtitle
            H2(
                attrs = Modifier
                    .color(rgba(255, 255, 255, 0.9))
                    .fontSize(SiteTheme.Typography.h4)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = SiteTheme.Spacing.lg)
                    .textShadow(offsetX = 1.px, offsetY = 1.px, blurRadius = 2.px, color = rgba(0, 0, 0, 0.3))
                    .toAttrs()
            ) {
                Text("Listen to my latest tracks and compositions")
            }
            
            // Brief introduction
            P(
                attrs = Modifier
                    .color(rgba(255, 255, 255, 0.8))
                    .fontSize(SiteTheme.Typography.body)
                    .maxWidth(800.px)
                    .margin(bottom = SiteTheme.Spacing.xl)
                    .lineHeight(1.6)
                    .toAttrs()
            ) {
                Text("Explore my musical creations spanning various genres and styles. Each track represents a unique expression of creativity and emotion. From electronic beats to ambient soundscapes, there's something for every mood.")
            }
            
            // Call to action button
            Button(
                attrs = Modifier
                    .backgroundColor(rgba(255, 255, 255, 0.9))
                    .color(rgb(124, 58, 237))
                    .fontSize(18.px)
                    .fontWeight(700)
                    .padding(leftRight = 32.px, topBottom = 12.px)
                    .borderRadius(50.px)
                    .border(0.px)
                    .cursor(Cursor.Pointer)
                    .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 8.px, color = rgba(0, 0, 0, 0.2))
                    .toAttrs()
            ) {
                Text("Browse All Tracks")
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
            // Section title
            H2(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(28.px)
                    .fontWeight(700)
                    .margin(bottom = SiteTheme.Spacing.lg)
                    .textAlign(TextAlign.Center)
                    .toAttrs()
            ) {
                Text("Featured Track")
            }
            
            // Enhanced music player
            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .padding(SiteTheme.Spacing.lg)
                    .backgroundColor(SiteTheme.Colors.surface)
                    .borderRadius(SiteTheme.BorderRadius.lg)
                    .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 12.px, color = rgba(0, 0, 0, 0.08))
                    .toAttrs()
            ) {
                // Music player with album art
                Div(
                    attrs = Modifier
                        .fillMaxWidth()
                        .display(DisplayStyle.Flex)
                        .flexDirection(FlexDirection.Column)
                        .padding(SiteTheme.Spacing.lg)
                        .gap(SiteTheme.Spacing.md)
                        .toAttrs()
                ) {
                    // Album art and track info
                    Div(
                        attrs = Modifier
                            .display(DisplayStyle.Flex)
                            .gap(SiteTheme.Spacing.lg)
                            .alignItems(AlignItems.Center)
                            .toAttrs()
                    ) {
                        // Album art
                        Img(
                            src = "/assets/images/blank-image.jpeg",
                            alt = "Album cover",
                            attrs = Modifier
                                .width(120.px)
                                .height(120.px)
                                .borderRadius(8.px)
                                .boxShadow(offsetX = 0.px, offsetY = 2.px, blurRadius = 6.px, color = rgba(0, 0, 0, 0.1))
                                .toAttrs()
                        )
                        
                        // Track info
                        Div(
                            attrs = Modifier
                                .display(DisplayStyle.Flex)
                                .flexDirection(FlexDirection.Column)
                                .gap(SiteTheme.Spacing.sm)
                                .toAttrs()
                        ) {
                            // Track title
                            H3(
                                attrs = Modifier
                                    .color(SiteTheme.Colors.text)
                                    .fontSize(24.px)
                                    .fontWeight(700)
                                    .margin(0.px)
                                    .toAttrs()
                            ) {
                                Text("Midnight Echoes")
                            }
                            
                            // Artist
                            P(
                                attrs = Modifier
                                    .color(SiteTheme.Colors.textSecondary)
                                    .fontSize(18.px)
                                    .margin(0.px)
                                    .toAttrs()
                            ) {
                                Text("ShevaPro")
                            }
                            
                            // Genre and release info
                            Div(
                                attrs = Modifier
                                    .display(DisplayStyle.Flex)
                                    .gap(SiteTheme.Spacing.md)
                                    .margin(top = SiteTheme.Spacing.sm)
                                    .toAttrs()
                            ) {
                                // Genre pill
                                Span(
                                    attrs = Modifier
                                        .backgroundColor(rgba(124, 58, 237, 0.1))
                                        .color(rgb(124, 58, 237))
                                        .fontSize(14.px)
                                        .padding(leftRight = 12.px, topBottom = 4.px)
                                        .borderRadius(20.px)
                                        .toAttrs()
                                ) {
                                    Text("Electronic")
                                }
                                
                                // Release date
                                Span(
                                    attrs = Modifier
                                        .color(SiteTheme.Colors.textSecondary)
                                        .fontSize(14.px)
                                        .toAttrs()
                                ) {
                                    Text("Released: July 2025")
                                }
                            }
                        }
                    }
                    
                    // Progress bar
                    Div(
                        attrs = Modifier
                            .margin(topBottom = SiteTheme.Spacing.md)
                            .toAttrs()
                    ) {
                        // Progress bar background
                        Div(
                            attrs = Modifier
                                .fillMaxWidth()
                                .height(6.px)
                                .backgroundColor(rgba(0, 0, 0, 0.1))
                                .borderRadius(3.px)
                                .position(Position.Relative)
                                .toAttrs()
                        ) {
                            // Progress bar fill
                            Div(
                                attrs = {
                                    style {
                                        position(Position.Absolute)
                                        top(0.px)
                                        left(0.px)
                                        height(6.px)
                                        width(35.percent)
                                        backgroundColor(rgb(124, 58, 237))
                                        borderRadius(3.px)
                                    }
                                }
                            ) {}
                        }
                        
                        // Time indicators
                        Div(
                            attrs = Modifier
                                .display(DisplayStyle.Flex)
                                .justifyContent(org.jetbrains.compose.web.css.JustifyContent.SpaceBetween)
                                .margin(top = 8.px)
                                .toAttrs()
                        ) {
                            // Current time
                            Span(
                                attrs = Modifier
                                    .color(SiteTheme.Colors.textSecondary)
                                    .fontSize(14.px)
                                    .toAttrs()
                            ) {
                                Text("1:45")
                            }
                            
                            // Total time
                            Span(
                                attrs = Modifier
                                    .color(SiteTheme.Colors.textSecondary)
                                    .fontSize(14.px)
                                    .toAttrs()
                            ) {
                                Text("5:02")
                            }
                        }
                    }
                    
                    // Player controls
                    Div(
                        attrs = Modifier
                            .display(DisplayStyle.Flex)
                            .justifyContent(org.jetbrains.compose.web.css.JustifyContent.Center)
                            .alignItems(AlignItems.Center)
                            .gap(SiteTheme.Spacing.lg)
                            .margin(top = SiteTheme.Spacing.md)
                            .toAttrs()
                    ) {
                        // Previous button
                        Button(
                            attrs = Modifier
                                .backgroundColor(rgba(124, 58, 237, 0.1))
                                .color(rgb(124, 58, 237))
                                .borderRadius(50.percent)
                                .width(40.px)
                                .height(40.px)
                                .border(0.px)
                                .cursor(Cursor.Pointer)
                                .toAttrs()
                        ) {
                            Text("⏮")
                        }
                        
                        // Play button
                        Button(
                            attrs = Modifier
                                .backgroundColor(rgb(124, 58, 237))
                                .color(rgb(255, 255, 255))
                                .borderRadius(50.percent)
                                .width(56.px)
                                .height(56.px)
                                .border(0.px)
                                .cursor(Cursor.Pointer)
                                .boxShadow(offsetX = 0.px, offsetY = 2.px, blurRadius = 8.px, color = rgba(124, 58, 237, 0.4))
                                .toAttrs()
                        ) {
                            Text("▶")
                        }
                        
                        // Next button
                        Button(
                            attrs = Modifier
                                .backgroundColor(rgba(124, 58, 237, 0.1))
                                .color(rgb(124, 58, 237))
                                .borderRadius(50.percent)
                                .width(40.px)
                                .height(40.px)
                                .border(0.px)
                                .cursor(Cursor.Pointer)
                                .toAttrs()
                        ) {
                            Text("⏭")
                        }
                    }
                }
            }
            
            // Playlist section title
            H2(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(24.px)
                    .fontWeight(700)
                    .margin(top = SiteTheme.Spacing.xl, bottom = SiteTheme.Spacing.md)
                    .textAlign(TextAlign.Center)
                    .toAttrs()
            ) {
                Text("More Tracks")
            }
            
            // Enhanced playlist
            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .toAttrs()
            ) {
                // Track list
                listOf(
                    Triple("Cosmic Journey", "Ambient", "6:24"),
                    Triple("Digital Dreams", "Electronic", "4:18"),
                    Triple("Harmonic Waves", "Classical", "3:52"),
                    Triple("Urban Pulse", "Electronic", "5:10"),
                    Triple("Serene Reflections", "Ambient", "7:35")
                ).forEachIndexed { index, (title, genre, duration) ->
                    MusicTrackItem(
                        title = title,
                        genre = genre,
                        duration = duration,
                        isActive = index == 0
                    )
                }
            }
        }
    }
}

@Composable
private fun MusicTrackItem(title: String, genre: String, duration: String, isActive: Boolean = false) {
    Div(
        attrs = Modifier
            .fillMaxWidth()
            .padding(SiteTheme.Spacing.md)
            .backgroundColor(if (isActive) rgba(124, 58, 237, 0.05) else SiteTheme.Colors.surface)
            .borderRadius(SiteTheme.BorderRadius.md)
            .display(DisplayStyle.Flex)
            .justifyContent(org.jetbrains.compose.web.css.JustifyContent.SpaceBetween)
            .alignItems(AlignItems.Center)
            .cursor(Cursor.Pointer)
            .border(if (isActive) 1.px else 0.px, LineStyle.Solid, if (isActive) rgba(124, 58, 237, 0.3) else rgba(0, 0, 0, 0))
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