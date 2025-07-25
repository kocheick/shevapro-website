package com.shevapro.website.components.ui

import androidx.compose.runtime.*
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun AboutSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(SiteTheme.Colors.gray50)
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px)
                .margin(topBottom = 0.px, leftRight = 0.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section title
            H2(
                attrs = Modifier
                    .color(SiteTheme.Colors.text)
                    .fontSize(SiteTheme.Typography.h2)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = SiteTheme.Spacing.lg)
                    .textAlign(TextAlign.Center)
                    .toAttrs()
            ) {
                Text("About Me")
            }
            
            // Section content
            Div(
                attrs = Modifier
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
                    .gap(SiteTheme.Spacing.xl)
                    .toAttrs()
            ) {
                // About text
                P(
                    attrs = Modifier
                        .color(SiteTheme.Colors.textSecondary)
                        .fontSize(SiteTheme.Typography.body)
                        .lineHeight(1.6)
                        .maxWidth(800.px)
                        .margin(topBottom = 0.px, leftRight = 0.px)
                        .textAlign(TextAlign.Center)
                        .toAttrs()
                ) {
                    Text("I'm a passionate developer with over 5 years of experience in creating web and mobile applications. My journey in software development started with a curiosity about how things work on the internet, and it has evolved into a career focused on building user-friendly, accessible, and performant digital experiences.")
                }
                
                // Skills section
                Div(
                    attrs = Modifier
                        .margin(top = SiteTheme.Spacing.lg)
                        .toAttrs()
                ) {
                    H3(
                        attrs = Modifier
                            .color(SiteTheme.Colors.text)
                            .fontSize(SiteTheme.Typography.h4)
                            .fontWeight(FontWeight.SemiBold)
                            .margin(bottom = SiteTheme.Spacing.md)
                            .textAlign(TextAlign.Center)
                            .toAttrs()
                    ) {
                        Text("My Skills")
                    }
                    
                    // Skills grid
                    Div(
                        attrs = Modifier
                            .display(DisplayStyle.Flex)
                            .flexWrap(FlexWrap.Wrap)
                            .gap(SiteTheme.Spacing.md)
                            .toAttrs()
                    ) {
                        // Skill tags
                        listOf(
                            "Kotlin", "JavaScript", "TypeScript", "React", 
                            "Compose", "Android", "iOS", "Node.js", 
                            "GraphQL", "REST API", "UI/UX Design", "Figma"
                        ).forEach { skill ->
                            Span(
                                attrs = Modifier
                                    .padding(SiteTheme.Spacing.sm, SiteTheme.Spacing.md)
                                    .backgroundColor(SiteTheme.Colors.surface)
                                    .color(SiteTheme.Colors.text)
                                    .borderRadius(SiteTheme.BorderRadius.md)
                                    .fontSize(SiteTheme.Typography.small)
                                    .toAttrs()
                            ) {
                                Text(skill)
                            }
                        }
                    }
                }
                
                // CTA
                A(
                    href = "/about",
                    attrs = Modifier
                        .margin(top = SiteTheme.Spacing.lg)
                        .padding(SiteTheme.Spacing.md, SiteTheme.Spacing.lg)
                        .backgroundColor(SiteTheme.Colors.primary)
                        .color(rgb(255, 255, 255))
                        .borderRadius(SiteTheme.BorderRadius.md)
                        .textDecorationLine(TextDecorationLine.None)
                        .fontWeight(FontWeight.Medium)
                        .toAttrs()
                ) {
                    Text("Learn More About Me")
                }
            }
        }
    }
}