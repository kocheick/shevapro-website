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
import com.shevapro.website.styles.SiteTheme
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    Layout(title = "Cheick's brand | Software Engineer | Artist") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .maxWidth(1200.px)
                .padding(SiteTheme.Spacing.xxl),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Section
            HeroSection()

            // Migration status section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .maxWidth(1200.px)
                    .padding(SiteTheme.Spacing.xl)
                    .backgroundColor(SiteTheme.Colors.gray50)
                    .borderRadius(12.px),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(SiteTheme.Spacing.xl),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    H1(
                        attrs = {
                            style {
                                fontSize(24.px)
                                fontWeight("bold")
                                color(SiteTheme.Colors.primary)
                                marginBottom(SiteTheme.Spacing.md)
                            }
                        }
                    ) {
                        Text("🚧 Under Construction")
                    }

                    P(
                        attrs = {
                            style {
                                fontSize(16.px)
                                color(SiteTheme.Colors.textSecondary)
                                textAlign("center")
                                lineHeight("1.5")
                            }
                        }
                    ) {
                        Text(
                            "This portfolio is currently being migrated from React to Kobweb. " +
                                    "The original hero content has been preserved above. Check back soon for the complete experience!"
                        )
                    }
                }
            }
        }
    }
}
