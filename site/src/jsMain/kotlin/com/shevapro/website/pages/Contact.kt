package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HeroSection
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Page("/contact")
@Composable
fun ContactPage() {
    Layout(title = "Contact - ShevaPro | Get in Touch") {
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
            // Hero section using the default gradient (no parameters needed)
            HeroSection(
                title = "Contact Me",
                description = "Have a question or want to work together? Fill out the form below or use the contact information to get in touch."
            )

            // Contact section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(topBottom = SiteTheme.Spacing.xxl),
                contentAlignment = Alignment.Center
            ) {
                Div(
                    attrs = Modifier
                        .fillMaxWidth(90.percent)
                        .maxWidth(1200.px)
                        .classNames("flex", "flex-col", "lg:flex-row", "gap-8", "lg:gap-16")
                        .toAttrs()
                ) {
                    // Contact form
                    Div(
                        attrs = {
                            classes(
                                "m-4",
                                "lg:w-3/5",
                                "bg-white",
                                "bg-opacity-90",
                                "rounded-2xl",
                                "p-6",
                                "shadow-lg"
                            )
                        }
                    ) {
                        H2(
                            attrs = {
                                classes("text-2xl", "md:text-3xl", "font-bold", "text-gray-900", "mb-6")
                            }
                        ) {
                            Text("Send a Message")
                        }

                        Form(
                            attrs = {
                                style {
                                    width(100.percent)
                                }
                            }
                        ) {
                            // Name field
                            Div(
                                attrs = {
                                    style {
                                        marginBottom(SiteTheme.Spacing.lg)
                                    }
                                }
                            ) {
                                Label(
                                    attrs = {
                                        attr("for", "name")
                                        style {
                                            display(DisplayStyle.Block)
                                            fontSize(16.px)
                                            fontWeight("500")
                                            color(SiteTheme.Colors.text)
                                            marginBottom(8.px)
                                        }
                                    }
                                ) {
                                    Text("Name")
                                }
                                Input(
                                    type = InputType.Text,
                                    attrs = {
                                        id("name")
                                        attr("placeholder", "Your name")
                                        attr("required", "true")
                                        style {
                                            width(100.percent)
                                            padding(12.px)
                                            fontSize(16.px)
                                            borderRadius(8.px)
                                            border(1.px, LineStyle.Solid, rgba(0, 0, 0, 0.1))
                                        }
                                    }
                                )
                            }

                            // Email field
                            Div(
                                attrs = {
                                    style {
                                        marginBottom(SiteTheme.Spacing.lg)
                                    }
                                }
                            ) {
                                Label(
                                    attrs = {
                                        attr("for", "email")
                                        style {
                                            display(DisplayStyle.Block)
                                            fontSize(16.px)
                                            fontWeight("500")
                                            color(SiteTheme.Colors.text)
                                            marginBottom(8.px)
                                        }
                                    }
                                ) {
                                    Text("Email")
                                }
                                Input(
                                    type = InputType.Email,
                                    attrs = {
                                        id("email")
                                        attr("placeholder", "Your email address")
                                        attr("required", "true")
                                        style {
                                            width(100.percent)
                                            padding(12.px)
                                            fontSize(16.px)
                                            borderRadius(8.px)
                                            border(1.px, LineStyle.Solid, rgba(0, 0, 0, 0.1))
                                        }
                                    }
                                )
                            }

                            // Subject field
                            Div(
                                attrs = {
                                    style {
                                        marginBottom(SiteTheme.Spacing.lg)
                                    }
                                }
                            ) {
                                Label(
                                    attrs = {
                                        attr("for", "subject")
                                        style {
                                            display(DisplayStyle.Block)
                                            fontSize(16.px)
                                            fontWeight("500")
                                            color(SiteTheme.Colors.text)
                                            marginBottom(8.px)
                                        }
                                    }
                                ) {
                                    Text("Subject")
                                }
                                Input(
                                    type = InputType.Text,
                                    attrs = {
                                        id("subject")
                                        attr("placeholder", "Subject of your message")
                                        attr("required", "true")
                                        style {
                                            width(100.percent)
                                            padding(12.px)
                                            fontSize(16.px)
                                            borderRadius(8.px)
                                            border(1.px, LineStyle.Solid, rgba(0, 0, 0, 0.1))
                                        }
                                    }
                                )
                            }

                            // Message field
                            Div(
                                attrs = {
                                    style {
                                        marginBottom(SiteTheme.Spacing.xl)
                                    }
                                }
                            ) {
                                Label(
                                    attrs = {
                                        attr("for", "message")
                                        style {
                                            display(DisplayStyle.Block)
                                            fontSize(16.px)
                                            fontWeight("500")
                                            color(SiteTheme.Colors.text)
                                            marginBottom(8.px)
                                        }
                                    }
                                ) {
                                    Text("Message")
                                }
                                TextArea(
                                    attrs = {
                                        id("message")
                                        attr("placeholder", "Your message")
                                        attr("required", "true")
                                        attr("rows", "6")
                                        style {
                                            width(100.percent)
                                            padding(12.px)
                                            fontSize(16.px)
                                            borderRadius(8.px)
                                            border(1.px, LineStyle.Solid, rgba(0, 0, 0, 0.1))
                                            property("resize", "vertical")
                                        }
                                    }
                                )
                            }

                            // Submit button
                            Button(
                                attrs = {
                                    attr("type", "submit")
                                    classes(
                                        "bg-purple-600",
                                        "text-white",
                                        "text-lg",
                                        "font-medium",
                                        "px-8",
                                        "py-3",
                                        "rounded-lg",
                                        "cursor-pointer",
                                        "transition-all",
                                        "hover:bg-purple-500",
                                        "w-full",
                                        "sm:w-auto"
                                    )
                                    style {
                                        border(0.px)
                                    }
                                }
                            ) {
                                Text("Send Message")
                            }
                        }
                    }

                    // Contact information
                    Div(
                        attrs = {
                            classes(
                                "w-full",
                                "lg:w-2/5",
                                "mt-8",
                                "lg:mt-0",
                                "bg-white",
                                "bg-opacity-90",
                                "rounded-2xl",
                                "p-6",
                                "shadow-lg"
                            )
                        }
                    ) {
                        H2(
                            attrs = {
                                classes("text-2xl", "md:text-3xl", "font-bold", "text-gray-900", "mb-6")
                            }
                        ) {
                            Text("Contact Information")
                        }

                        // Contact cards with responsive spacing
                        Div(
                            attrs = Modifier
                                .classNames("space-y-4")
                                .toAttrs()
                        ) {
                            ContactInfoCard(
                                title = "Email",
                                content = "contact@shevapro.com",
                                icon = "✉️"
                            )

                            ContactInfoCard(
                                title = "Location",
                                content = "New York, NYC",
                                icon = "📍"
                            )

                            ContactInfoCard(
                                title = "Social Media",
                                content = "GitHub: @kocheick",
                                icon = "🌐"
                            )
                        }

                        // Note about response time
                        Div(
                            attrs = {
                                classes(
                                    "mt-8",
                                    "p-4",
                                    "bg-gradient-to-r",
                                    "from-purple-100",
                                    "to-blue-100",
                                    "rounded-xl",
                                    "border",
                                    "border-purple-200"
                                )
                            }
                        ) {
                            P(
                                attrs = {
                                    classes("text-base", "text-gray-800", "leading-relaxed", "m-0")
                                }
                            ) {
                                Text("I typically respond to inquiries within 24-72 hours. Thank you for your patience!")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactInfoCard(
    title: String,
    content: String,
    icon: String
) {
    Div(
        attrs = {
            classes(
                "flex",
                "items-center",
                "p-4",
                "mb-4",
                "bg-gradient-to-r",
                "from-gray-50",
                "to-gray-100",
                "rounded-xl",
                "border",
                "border-gray-200",
                "hover:shadow-md",
                "transition-all"
            )
        }
    ) {
        // Icon
        Div(
            attrs = {
                classes("text-2xl", "mr-4", "flex-shrink-0")
            }
        ) {
            Text(icon)
        }
        
        // Content
        Div(
            attrs = {
                classes("flex-1")
            }
        ) {
            H3(
                attrs = {
                    classes("text-lg", "font-bold", "text-gray-900", "mb-1")
                }
            ) {
                Text(title)
            }
            
            P(
                attrs = {
                    classes("text-base", "text-gray-700", "m-0", "break-words")
                }
            ) {
                Text(content)
            }
        }
    }
}