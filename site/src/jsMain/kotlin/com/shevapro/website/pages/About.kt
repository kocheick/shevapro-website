package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import com.shevapro.website.components.sections.StoryTree
import com.shevapro.website.data.AboutStoryService

@Page("/about")
@Composable
fun AboutPage() {
    Layout(title = "About - ShevaPro | Software Engineer | Artist") {
        // Main container with background
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Background div with softer gradient
            Div(
                attrs = {
                    style {
                        position(Position.Absolute)
                        top(0.px)
                        left(0.px)
                        right(0.px)
                        bottom(0.px)
                        property(
                            "background-image",
                            "linear-gradient(135deg, rgba(124, 58, 237, 0.15), rgba(147, 197, 253, 0.2))"
                        )
//                        property("background-color", "rgb(248, 250, 252)")
                        property("background-size", "cover")
                        property("background-position", "center")
                        property("z-index", "-1")
                    }
                }
            )

            // Content container with softer semi-transparent background
            Column(
                modifier = Modifier
//                    .fillMaxWidth(90.percent)
                    .maxWidth(800.px)
                    .margin(topBottom = SiteTheme.Spacing.xxl)
                    .padding(SiteTheme.Spacing.md)
                    .backgroundColor(rgba(255, 255, 255, 0.7))
                    .borderRadius(SiteTheme.BorderRadius.lg)
                    .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 12.px, color = rgba(0, 0, 0, 0.08)),
                horizontalAlignment = Alignment.Start
            ) {
                H1(
                    attrs = {
                        style {
                            fontSize(36.px)
                            fontWeight("bold")
                            color(rgb(55, 65, 81)) // Softer dark gray instead of pure black
                            marginBottom(SiteTheme.Spacing.xl)
                            property("text-shadow", "1px 1px 2px rgba(0, 0, 0, 0.1)")
                        }
                    }
                ) {
                    Text("About Me")
                }

                // Intro teaser
                P(
                    attrs = {
                        style {
                            fontSize(20.px)
                            fontWeight("600")
                            color(rgb(75, 85, 99)) // Softer gray
                            marginBottom(SiteTheme.Spacing.lg)
                            lineHeight("1.5")
                        }
                    }
                ) {
                    Text("I'm a Software Engineer who loves crafting experiences across Android and the Web. Here's my journey in a few snapshots.")
                }

                // Story timeline cards - now using centralized service
                StoryTree(nodes = AboutStoryService.getStoryNodes())

                // Contact / specialties note
                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(107, 114, 128)) // Softer gray to match the theme
                            lineHeight("1.6")
                            marginTop(SiteTheme.Spacing.xl)
                        }
                    }
                ) {
                    Text("Specialties: Kotlin, Android, Web, OOP. Find me on GitHub: https://github.com/shevapro")
                }
            }
        }
    }
}