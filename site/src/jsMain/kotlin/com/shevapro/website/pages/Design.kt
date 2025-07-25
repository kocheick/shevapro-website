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
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun DesignPage() {
    Layout {
        // Design page content
        DesignHeroSection()
        DesignGallerySection()
    }
}

@Composable
private fun DesignHeroSection() {
    // TODO: Implement Design hero section
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
                Text("Design")
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
                Text("Elegant, modern and creative")
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
                Text("Admire a blend of various visual projects I created in no particular order for artists, events and businesses.")
            }
        }
    }
}

@Composable
private fun DesignGallerySection() {
    // TODO: Implement design gallery section
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
        attrs = Modifier
            .backgroundColor(SiteTheme.Colors.surface)
            .borderRadius(SiteTheme.BorderRadius.lg)
            .overflow(Overflow.Hidden)
            .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 8.px, color = rgba(0, 0, 0, 0.05))
            .cursor(Cursor.Pointer)
            .toAttrs()
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
                attrs = Modifier
                    .color(SiteTheme.Colors.primary)
                    .fontSize(SiteTheme.Typography.small)
                    .fontWeight(FontWeight.Medium)
                    .toAttrs()
            ) {
                Text(category)
            }
            
            // Title
            H3(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(SiteTheme.Typography.body)
                    .fontWeight(FontWeight.Bold)
                    .margin(top = SiteTheme.Spacing.xs, bottom = 0.px)
                    .toAttrs()
            ) {
                Text(title)
            }
        }
    }
}