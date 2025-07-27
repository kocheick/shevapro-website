package com.shevapro.website.components.ui

import androidx.compose.runtime.*
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun HeroSection() {
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
                            classes("text-lg", "lg:text-xl", "text-center", "md:text-start", "m-2", "mb-2", "md:m-0")
                        }) {
                            P {
                                Text("I am a creative soul who loves making art but I really get a thrill from building custom solutions that help users achieve their goals.")
                            }
                        }
                    }
                    Div({
                        classes("flex","flex-col","md:flex-row","justify-around","md","mt-auto")
                    }) {
                        // Portfolio 
                        A(href = "/portfolio", {
                            classes(
                                "text-center",
                                "border",
                                "mx-auto",
                                "my-2",
                                "rounded",
                                "w-1/2",
                                "md:w-1/3",
                                "hover:from-red-200",
                                "ease-out",
                                "hover:shadow",
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
                        A(href = "/contact", {
                            classes(
                                "text-center",
                                "border",
                                "mx-auto",
                                "my-2",
                                "rounded",
                                "w-1/2",
                                "md:w-1/3",
                                "hover:from-red-200",
                                "ease-out",
                                "hover:shadow",
                                "hover:scale-105",
                                "duration-300",
                                "hover:to-yellow-200",
                                "transition",
                                "bg-gradient-to-tl",
                                "from-orange-200",
                                "via-cyan-100"
                            )
                        }) {
                            Text("Contact Me")
                        }
                    }
                }
                // Right image (hidden for mobile)
                Div({
                    classes("hidden", "md:block", "flex-1", "m-1")
                }) {
                    A(href = "#", {
                        classes("rounded-xl", "mx-auto")
                    }) {
                        Img(src = "/assets/images/blank-image.jpeg", alt = "main profile image")
                    }
                }
            }

}