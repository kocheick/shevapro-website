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

@Page("/about")
@Composable
fun AboutPage() {
    Layout(title = "About - ShevaPro | Software Engineer | Artist") {
        // Main container with background
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Background div with gradient
            Div(
                attrs = {
                    style {
                        position(Position.Absolute)
                        top(0.px)
                        left(0.px)
                        right(0.px)
                        bottom(0.px)
                        property("background-image", "linear-gradient(135deg, rgba(124, 58, 237, 0.8), rgba(147, 197, 253, 0.8))")
                        property("background-size", "cover")
                        property("background-position", "center")
                        property("z-index", "-1")
                    }
                }
            )
            
            // Content container with semi-transparent background
            Column(
                modifier = Modifier
                    .fillMaxWidth(90.percent)
                    .maxWidth(800.px)
                    .margin(topBottom = SiteTheme.Spacing.xxl)
                    .padding(SiteTheme.Spacing.xxl)
                    .backgroundColor(rgba(255, 255, 255, 0.9))
                    .borderRadius(SiteTheme.BorderRadius.lg)
                    .boxShadow(offsetX = 0.px, offsetY = 4.px, blurRadius = 12.px, color = rgba(0, 0, 0, 0.1)),
                horizontalAlignment = Alignment.Start
            ) {
                H1(
                    attrs = {
                        style {
                            fontSize(36.px)
                            fontWeight("bold")
                            color(SiteTheme.Colors.textHeading)
                            marginBottom(SiteTheme.Spacing.xl)
                            property("text-shadow", "1px 1px 2px rgba(0, 0, 0, 0.2)")
                        }
                    }
                ) {
                    Text("About Me")
                }

                H3(
                    attrs = {
                        style {
                            fontSize(24.px)
                            fontWeight("600")
                            color(SiteTheme.Colors.textHeading)
                            marginBottom(SiteTheme.Spacing.lg)
                            lineHeight("1.4")
                            property("text-shadow", "0.5px 0.5px 1px rgba(0, 0, 0, 0.15)")
                        }
                    }
                ) {
                    Text("I'm a dedicated Software Engineer with a strong background in Kotlin, Android, and Web development.")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50)) // Darker text for better readability on light background
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.lg)
                        }
                    }
                ) {
                    Text("My journey into coding began with a deep-rooted passion for technology, and I've always dreamt of creating my own innovative solutions. While self-learning was no easy feat, my unwavering curiosity fueled my progress, leading me from one discovery to another until I grasped the bigger picture of coding fundamentals.")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.lg)
                        }
                    }
                ) {
                    Text("My foray into app development started when I built a website for a local artist to showcase his music. The experience ignited my excitement to delve into mobile app development. I chose Android as my platform of choice due to its accessibility and widespread popularity. And let me tell you, learning Kotlin was an absolute delight! <3")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.lg)
                        }
                    }
                ) {
                    Text("What truly drives me is the sheer joy I find in crafting apps from conception to completion. The process of transforming an idea into a fully functional app is exhilarating, and I thrive on collaborating with individuals from diverse backgrounds. Each project is a valuable learning experience that expands my skill set and broadens my horizons.")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.lg)
                        }
                    }
                ) {
                    Text("My ultimate goal as a Software Engineer is to contribute to products that not only delight users but also provide tangible value. I strive to create seamless and engaging experiences that leave a lasting impact. I firmly believe that technology should enrich our lives, and I'm passionate about bringing that vision to reality.")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.lg)
                        }
                    }
                ) {
                    Text("When I'm not immersed in development, you might find me lacing up my running shoes, engrossed in a captivating book, or exploring nature through invigorating hikes. I believe in maintaining a well-rounded lifestyle that fuels creativity and keeps me inspired.")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.lg)
                        }
                    }
                ) {
                    Text("I invite you to reach out to me about any exciting challenges or opportunities you may have. Let's connect and discuss how I can contribute my skills and expertise to help you achieve your goals.")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.md)
                        }
                    }
                ) {
                    Text("GitHub: https://github.com/shevapro")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(rgb(50, 50, 50))
                            lineHeight("1.6")
                            marginBottom(SiteTheme.Spacing.md)
                        }
                    }
                ) {
                    Text("Specialties: Object-Oriented Programming (OOP) | Mobile Development | Kotlin | Android Development | Software Developer | JavaScript | HTML | CSS | Web Development")
                }

                P(
                    attrs = {
                        style {
                            fontSize(16.px)
                            color(SiteTheme.Colors.textHeading)
                            lineHeight("1.6")
                            fontWeight("600")
                        }
                    }
                ) {
                    Text("Looking forward to connecting with you!")
                }
            }
        }
    }
}