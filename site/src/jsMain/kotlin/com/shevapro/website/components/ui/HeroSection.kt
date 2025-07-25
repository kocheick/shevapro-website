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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minHeight(80.vh)
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        // Desktop layout - side by side
        Row(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px)
                .displayIfAtLeast(Breakpoint.MD)
                .gap(SiteTheme.Spacing.xl),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Content Column
            Column(
                modifier = Modifier
                    .fillMaxWidth(50.percent)
                    .textAlign(TextAlign.Start),
                horizontalAlignment = Alignment.Start
            ) {
                // Greeting
                Span(
                    attrs = Modifier
                        .color(SiteTheme.Colors.primary)
                        .fontSize(SiteTheme.Typography.body)
                        .fontWeight(FontWeight.Medium)
                        .margin(bottom = SiteTheme.Spacing.md)
                        .toAttrs()
                ) {
                    Text("Hi there, I am")
                }

                // Name
                H1(
                    attrs = Modifier
                        .color(SiteTheme.Colors.text)
                        .fontSize(3.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .margin(bottom = SiteTheme.Spacing.sm)
                        .toAttrs()
                ) {
                    Text("Shevapro!")
                }

                // Title
                H2(
                    attrs = Modifier
                        .color(SiteTheme.Colors.textSecondary)
                        .fontSize(1.5.cssRem)
                        .fontWeight(FontWeight.Normal)
                        .margin(bottom = SiteTheme.Spacing.lg)
                        .toAttrs()
                ) {
                    Text("Software Engineer / Mobile app developer experienced in Android.")
                }

                // Description
                P(
                    attrs = Modifier
                        .color(SiteTheme.Colors.textSecondary)
                        .fontSize(1.125.cssRem)
                        .maxWidth(600.px)
                        .margin(bottom = SiteTheme.Spacing.xl)
                        .lineHeight(1.6)
                        .toAttrs()
                ) {
                    Text("I am a creative soul who loves making art but I really get a thrill from building custom solutions that help users achieve their goals.")
                }

                // Call to action buttons
                Div(
                    attrs = Modifier
                        .display(DisplayStyle.Flex)
                        .gap(SiteTheme.Spacing.md)
                        .flexWrap(FlexWrap.Wrap)
                        .toAttrs()
                ) {
                    A(
                        href = "/portfolio",
                        attrs = Modifier
                            .padding(SiteTheme.Spacing.md, SiteTheme.Spacing.lg)
                            .backgroundColor(SiteTheme.Colors.primary)
                            .color(rgb(255, 255, 255))
                            .borderRadius(SiteTheme.BorderRadius.md)
                            .textDecorationLine(TextDecorationLine.None)
                            .fontWeight(FontWeight.Medium)
                            .toAttrs()
                    ) {
                        Text("See my work")
                    }

                    A(
                        href = "/contact",
                        attrs = Modifier
                            .padding(SiteTheme.Spacing.md, SiteTheme.Spacing.lg)
                            .backgroundColor(rgba(0, 0, 0, 0))
                            .color(SiteTheme.Colors.primary)
                            .border(1.px, LineStyle.Solid, SiteTheme.Colors.primary)
                            .borderRadius(SiteTheme.BorderRadius.md)
                            .textDecorationLine(TextDecorationLine.None)
                            .fontWeight(FontWeight.Medium)
                            .toAttrs()
                    ) {
                        Text("Contact Me")
                    }
                }
            }

            // Image Column
            Box(
                modifier = Modifier
                    .fillMaxWidth(45.percent)
                    .aspectRatio(1.0),
                contentAlignment = Alignment.Center
            ) {
                Img(
                    src = "/favicon.ico", // Placeholder until proper image is added
                    alt = "Shevapro profile image",
                    attrs = Modifier
                        .fillMaxSize()
                        .borderRadius(SiteTheme.BorderRadius.lg)
                        .objectFit(ObjectFit.Cover)
                        .boxShadow(offsetY = 10.px, blurRadius = 30.px, color = rgba(0, 0, 0, 0.1))
                        .toAttrs()
                )
            }
        }

        // Mobile layout - stacked
        Column(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(600.px)
                .displayUntil(Breakpoint.MD)
                .textAlign(TextAlign.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mobile Image
            Box(
                modifier = Modifier
                    .size(200.px)
                    .margin(bottom = SiteTheme.Spacing.xl),
                contentAlignment = Alignment.Center
            ) {
                Img(
                    src = "/favicon.ico", // Placeholder until proper image is added
                    alt = "Shevapro profile image",
                    attrs = Modifier
                        .fillMaxSize()
                        .borderRadius(50.percent)
                        .objectFit(ObjectFit.Cover)
                        .boxShadow(offsetY = 8.px, blurRadius = 24.px, color = rgba(0, 0, 0, 0.1))
                        .toAttrs()
                )
            }

            // Greeting
            Span(
                attrs = Modifier
                    .color(SiteTheme.Colors.primary)
                    .fontSize(SiteTheme.Typography.body)
                    .fontWeight(FontWeight.Medium)
                    .margin(bottom = SiteTheme.Spacing.md)
                    .toAttrs()
            ) {
                Text("Hi there, I am")
            }
            
            // Name
            H1(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(2.5.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = SiteTheme.Spacing.sm)
                    .toAttrs()
            ) {
                Text("Shevapro!")
            }
            
            // Title
            H2(
                attrs = Modifier
                    .color(SiteTheme.Colors.textSecondary)
                    .fontSize(1.25.cssRem)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = SiteTheme.Spacing.lg)
                    .toAttrs()
            ) {
                Text("Software Engineer / Mobile app developer experienced in Android.")
            }
            
            // Description
            P(
                attrs = Modifier
                    .color(SiteTheme.Colors.textSecondary)
                    .fontSize(1.125.cssRem)
                    .margin(bottom = SiteTheme.Spacing.xl)
                    .lineHeight(1.6)
                    .toAttrs()
            ) {
                Text("I am a creative soul who loves making art but I really get a thrill from building custom solutions that help users achieve their goals.")
            }
            
            // Call to action buttons
            Div(
                attrs = Modifier
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
                    .gap(SiteTheme.Spacing.md)
                    .width(100.percent)
                    .toAttrs()
            ) {
                A(
                    href = "/portfolio",
                    attrs = Modifier
                        .padding(SiteTheme.Spacing.md, SiteTheme.Spacing.lg)
                        .backgroundColor(SiteTheme.Colors.primary)
                        .color(rgb(255, 255, 255))
                        .borderRadius(SiteTheme.BorderRadius.md)
                        .textDecorationLine(TextDecorationLine.None)
                        .fontWeight(FontWeight.Medium)
                        .textAlign(TextAlign.Center)
                        .toAttrs()
                ) {
                    Text("See my work")
                }
                
                A(
                    href = "/contact",
                    attrs = Modifier
                        .padding(SiteTheme.Spacing.md, SiteTheme.Spacing.lg)
                        .backgroundColor(rgba(0, 0, 0, 0))
                        .color(SiteTheme.Colors.primary)
                        .border(1.px, LineStyle.Solid, SiteTheme.Colors.primary)
                        .borderRadius(SiteTheme.BorderRadius.md)
                        .textDecorationLine(TextDecorationLine.None)
                        .fontWeight(FontWeight.Medium)
                        .textAlign(TextAlign.Center)
                        .toAttrs()
                ) {
                    Text("Contact Me")
                }
            }
        }
    }
}