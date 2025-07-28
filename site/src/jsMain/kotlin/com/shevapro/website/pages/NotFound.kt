package com.shevapro.website.pages

import androidx.compose.runtime.Composable
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.widgets.Button
import com.shevapro.website.components.widgets.ButtonVariant
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import kotlinx.browser.window

//@Page(routeOverride = "{...}")
@Page
@Composable
fun NotFound() {
    Layout(title = "Page Not Found | Shevapro", description = "The page you're looking for doesn't exist.") {
        // Outer container - replaces Box with contentAlignment = Alignment.Center
        Div(
            attrs = {
                classes(
                    "w-full", 
                    "h-full", 
                    "flex", 
                    "items-center", 
                    "justify-center"
                )
                style {
                    padding(SiteTheme.Spacing.xxl)
                }
            }
        ) {
            // Inner container - replaces Column with horizontalAlignment = Alignment.CenterHorizontally
            Div(
                attrs = {
                    classes(
                        "w-full", 
                        "flex", 
                        "flex-col", 
                        "items-center"
                    )
                    style {
                        maxWidth(800.px)
                        padding(SiteTheme.Spacing.xl)
                        backgroundColor(rgba(26, 27, 38, 0.9)) // Using rgba for surface color with opacity
                        borderRadius(SiteTheme.BorderRadius.xl)
                    }
                }
            ) {
                // 404 Error Code
                H1(
                    attrs = {
                        style {
                            fontSize(120.px)
                            fontWeight("bold")
                            color(SiteTheme.Colors.primary)
                            margin(SiteTheme.Spacing.md, 0.px)
                            textAlign("center")
                        }
                    }
                ) {
                    Text("404")
                }
                
                // Error Message
                H1(
                    attrs = {
                        style {
                            fontSize(SiteTheme.Typography.h3)
                            fontWeight("bold")
                            color(SiteTheme.Colors.textHeading)
                            marginBottom(SiteTheme.Spacing.md)
                            textAlign("center")
                        }
                    }
                ) {
                    Text("Page Not Found")
                }
                
                // Description
                P(
                    attrs = {
                        style {
                            fontSize(SiteTheme.Typography.body)
                            color(SiteTheme.Colors.textSecondary)
                            marginBottom(SiteTheme.Spacing.xl)
                            textAlign("center")
                            maxWidth(600.px)
                            lineHeight("1.5")
                        }
                    }
                ) {
                    Text("The page you're looking for doesn't exist or has been moved. Please check the URL or navigate back to the home page.")
                }
                
                // Back to Home Button
//                Button(attrs = {
//                    classes(
//                        "bg-blue-600",
//                        "hover:bg-blue-700",
//                        "text-white",
//                        "font-bold",
//                        "py-3",
//                        "px-8",
//                        "rounded-lg",
//                        "text-lg",
//                        "transition",
//                        "duration-200",
//                        "shadow"
//                    )
//                    onClick {
//                        if (window.history.length > 1) {
//                            window.history.back()
//                        } else {
//                            window.location.href = "/"
//                        }
//                    }
//                }) {
//                    Text("Take Me Back")
//                }

                // Back to Home Button
                Button(
                    text = "Back to Home",
//                    path = "/",
                    onClick = {
                        if (window.history.length > 1) {
                            window.history.back()
                        } else {
                            window.location.href = "/"
                        }
                    },
                    variant = ButtonVariant.Primary,
                    modifier = Modifier
                        .margin(SiteTheme.Spacing.md)
                )
            }
        }
    }
}