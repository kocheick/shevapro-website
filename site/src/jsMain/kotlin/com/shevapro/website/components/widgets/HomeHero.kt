package com.shevapro.website.components.widgets

import androidx.compose.runtime.*
import com.shevapro.website.data.HomeContent
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
                        }) { Text(HomeContent.Hero.headline) }
                        H1({
                            classes("text-xl", "my-2", "text-center", "md:text-start", "lg:text-2xl")
                        }) {
                            Text(HomeContent.Hero.subheadline)
                        }
                        Div({
                            classes("text-lg", "lg:text-xl", "text-center", "md:text-start", "m-2", "md:m-0")
                        }) {
                            P {
                                Text(HomeContent.Hero.description)
                            }
                        }
                    }
                    Div({
                        classes("flex","flex-col","md:flex-row","justify-around","md","mt-auto")
                    }) {

                        A(href = HomeContent.Hero.primaryCtaHref, {
                            onClick { it.preventDefault()
                                router.navigateTo(HomeContent.Hero.primaryCtaHref)
                            }

                            classes(
                                "text-center",
                                "mx-auto",
                                "px-6",
                                "py-3","mt-2",
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
                        }) { Text(HomeContent.Hero.primaryCtaText) }
                        // Portfolio 
                        A(href = HomeContent.Hero.secondaryCtaHref, {

                            onClick { it.preventDefault()
                                router.navigateTo(HomeContent.Hero.secondaryCtaHref)
                            }
                            classes(
                                "text-center",
                                "border",
                                "font-semibold",
                                "mt-2",
                                "mx-auto",
                                "text-purple-800",
                                "py-3",
                                "px-6",
                                "rounded-lg",
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
                        }) { Text(HomeContent.Hero.secondaryCtaText) }

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