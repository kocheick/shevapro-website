package com.shevapro.website.components.ui

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.shevapro.website.styles.SiteTheme
import org.jetbrains.compose.web.dom.*

@Composable
fun HeroSection(
    title: String,
    subtitle: String? = null,
    description: String? = null,
    gradientFrom: String = "from-yellow-300", // Consistent default
    gradientTo: String = "to-red-200",        // Consistent default  
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        Div(
            attrs = {
                classes(
                    "w-4/5",
                    "max-w-4xl",
                    "my-6",
                    "bg-gradient-to-bl",
                    gradientFrom,
                    gradientTo,
                    "rounded-2xl",
                    "p-6",
                    "text-center"
                )
            }
        ) {
            H1(
                attrs = {
                    classes("font-bold", "text-3xl", "md:text-5xl", "mb-4", "text-gray-900")
                }
            ) {
                Text(title)
            }

            subtitle?.let { subtitleText ->
                H2(
                    attrs = {
                        classes("text-xl", "md:text-2xl", "font-normal", "mb-4", "text-gray-800")
                    }
                ) {
                    Text(subtitleText)
                }
            }

            description?.let { descriptionText ->
                P(
                    attrs = {
                        classes("text-base", "md:text-lg", "text-gray-800", "leading-relaxed", "max-w-3xl")
                    }
                ) {
                    Text(descriptionText)
                }
            }
        }
    }
}