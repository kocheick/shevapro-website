package com.shevapro.website.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.styles.SiteTheme
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page("/about")
@Composable
fun AboutPage() {
    Layout(title = "About - ShevaPro | Software Engineer | Artist") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .maxWidth(800.px)
                .padding(SiteTheme.Spacing.xxl),
            horizontalAlignment = Alignment.Start
        ) {
            H1(
                attrs = {
                    style {
                        fontSize(36.px)
                        fontWeight("bold")
                        color(SiteTheme.Colors.text)
                        marginBottom(SiteTheme.Spacing.xl)
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
                        color(SiteTheme.Colors.text)
                        marginBottom(SiteTheme.Spacing.lg)
                        lineHeight("1.4")
                    }
                }
            ) {
                Text("I'm a dedicated Software Engineer with a strong background in Kotlin, Android, and Web development.")
            }

            P(
                attrs = {
                    style {
                        fontSize(16.px)
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.textSecondary)
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
                        color(SiteTheme.Colors.text)
                        lineHeight("1.6")
                        fontWeight("500")
                    }
                }
            ) {
                Text("Looking forward to connecting with you!")
            }
        }
    }
}