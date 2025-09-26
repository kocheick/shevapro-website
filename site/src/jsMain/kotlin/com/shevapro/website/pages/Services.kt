package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HeroSection
import com.shevapro.website.components.widgets.ServiceCard
import com.shevapro.website.data.ServicesCatalog
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Page("/services")
@Composable
fun ServicesPage() {
    Layout(title = "Services - ShevaPro | Software Development & Consulting") {
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
                title = "Services",
                description = "I offer development and consulting services to help you build, improve, and maintain software that makes a real difference for your users and business."
                // Using default gradient - can override with gradientFrom/gradientTo if needed
            )

            // Services section
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
                    horizontalAlignment = Alignment.Start
                ) {
                    ServicesCatalog.forEach { service ->
                        ServiceCard(service)
                    }
                }
            }

            // Call to action section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .backgroundColor(rgba(124, 58, 237, 0.1))
                    .padding(topBottom = SiteTheme.Spacing.xxl),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(90.percent)
                        .maxWidth(800.px)
                        .gap(SiteTheme.Spacing.lg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    H2(
                        attrs = {
                            style {
                                fontSize(32.px)
                                fontWeight("bold")
                                color(SiteTheme.Colors.textSecondary)
                                marginBottom(SiteTheme.Spacing.md)
                                textAlign("center")
                            }
                        }
                    ) {
                        Text("Ready to Start Working Together?")
                    }

                    P(
                        attrs = {
                            style {
                                fontSize(18.px)
                                color(SiteTheme.Colors.textSecondary)
                                lineHeight("1.6")
                                marginBottom(SiteTheme.Spacing.lg)
                                textAlign("center")
                            }
                        }
                    ) {
                        Text("I'd love to discuss how I can help you achieve your goals. Let's have a conversation about your project and see how we can work together.")
                    }

                    Link(
                        path = "/contact",
                        modifier = Modifier
                            .classNames(
                                "inline-block",
                                "bg-purple-600",
                                "text-white",
                                "text-lg",
                                "font-medium",
                                "px-8",
                                "py-4",
                                "rounded-lg",
                                "no-underline",
                                "cursor-pointer",
                                "transition-all",
                                "hover:bg-purple-500"
                            )
                    ) {
                        Text("Get In Touch")
                    }
                }
            }
        }
    }
}

@Composable
private fun ServiceCard(
    title: String,
    description: String,
    features: List<String>
) {
    Div(
        attrs = {
            classes(
                "w-full",
                "p-6",
                "bg-white",
                "bg-opacity-90",
                "rounded-2xl",
                "shadow-lg",
                "hover:shadow-xl",
                "transition-all"
            )
        }
    ) {
        H3(
            attrs = {
                classes("text-2xl", "md:text-3xl", "font-bold", "text-gray-900", "mb-4")
            }
        ) {
            Text(title)
        }

        P(
            attrs = {
                classes("text-base", "md:text-lg", "text-gray-700", "leading-relaxed", "mb-6")
            }
        ) {
            Text(description)
        }

        H4(
            attrs = {
                classes("text-lg", "font-semibold", "text-gray-900", "mb-4")
            }
        ) {
            Text("Key Features:")
        }

        Ul(
            attrs = {
                classes("list-disc", "pl-6", "space-y-2")
            }
        ) {
            features.forEach { feature ->
                Li(
                    attrs = {
                        classes("text-base", "text-gray-700", "leading-relaxed")
                    }
                ) {
                    Text(feature)
                }
            }
        }
    }
}