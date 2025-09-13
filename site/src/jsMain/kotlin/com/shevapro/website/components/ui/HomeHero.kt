package com.shevapro.website.components.ui

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.dom.*

@Composable
fun HomeHero() {
    val router = rememberPageContext().router
            Div({
                classes("flex", "flex-col", "items-center", "md:flex-row")
            }) {
                // Left content (text, CTA)
                Div({
                    classes("flex-1", "m-1")
                }) {
                    Div({
                        classes("flex", "flex-col", "justify-between", "flex-1", "m-1")
                    }) {
                        Div({
                            classes(
                                "text-center",
                                "md:text-start",
                                "text-3xl",
                                "md:text-4xl",
                                "lg:text-5xl",
                                "font-semibold"
                            )
                        }) {
                            Text("Hi there, I am Cheick!")
                        }
                        H1({
                            classes("text-xl", "mb-2", "text-center", "md:text-start", "lg:text-2xl")
                        }) {
                            Text("Software Engineer / Mobile app developer experienced in Android.")
                        }
                        Div({
                            classes("text-lg", "lg:text-xl", "text-center", "md:text-start", "m-2", "md:m-0")
                        }) {
                            P {
                                Text("I am a creative soul who loves making art but I really get a thrill from building custom solutions that help users achieve their goals.")
                            }
                        }
                    }
                    Div({
                        classes("flex","flex-col","md:flex-row","justify-around","md","mt-auto")
                    }) {

                        A(href = "/contact", {
                            onClick { it.preventDefault()
                                router.navigateTo("/contact") }

                            classes(
                                "text-center",
                                "mx-auto",
                                "px-6",
                                "py-3",
                                "bg-purple-600",
                                "hover:bg-purple-700",
                                "text-white",
                                "font-semibold",
                                "rounded-lg",
                                "shadow-md",
                                "hover:shadow-lg",
                                "transition-all",
                                "duration-300",
                                "transform",
                                "hover:scale-105"
                            )
                        }) {
                            Text("Contact Me")
                        }
                        // Portfolio 
                        A(href = "/portfolio", {

                            onClick { it.preventDefault()
                                router.navigateTo("/portfolio") }
                            classes(
                                "text-center",
                                "border",
                                "font-semibold",
                                "mx-auto",
                                "text-purple-800",
                                "m-3",
                                "px-6",
                                "rounded",
                                "hover:from-red-200",
                                "ease-out",
                                "shadow",
                                "hover:scale-105",
                                "duration-300",
                                "hover:to-yellow-200",
                                "transition",
                                "bg-gradient-to-tl",
                                "from-orange-200",
                                "via-cyan-100"
                            )
                        }) {
                            Text("See my work")
                        }

                    }
                }
                // Right image (hidden for mobile)
                Div({
                    classes("hidden", "md:block", "flex-1", "m-1")
                }) {
                    Picture(attrs = {
                        classes("mx-auto")
                    }) {
                        // Mobile Image
                        Source(
                            attrs = {
                                attr("media", "(max-width: 770px)")

                                attr("srcset", "/assets/images/photos/cover-m.webp")
                            }
                        )

//                         Desktop Image (commented out for later)
                         Source(
                             attrs = {
                                 attr("media", "(min-width: 771px)")
                                 attr("srcset", "/assets/images/photos/cover.webp")
                             }
                         )

                        // Fallback Image
                        Img(
                            src = "/assets/images/blank-image.webp",
                            attrs = {
                                attr("aria-hidden", "true")
                                attr("loading", "lazy")
                                attr("decoding", "async")
                                attr("alt", "main profile image")
                            }
                        )
                    }
                }
            }

}