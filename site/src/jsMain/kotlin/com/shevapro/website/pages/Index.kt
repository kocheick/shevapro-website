package com.shevapro.website.pages

import androidx.compose.runtime.Composable
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HomeHero
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.utils.Constants
import com.varabyte.kobweb.compose.css.backgroundImage
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.margin
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun HomePage() {
    Layout(title = "Cheick's brand | Software Engineer | Artist", Constants.Site.DESCRIPTION) {
        Div(attrs = {
            classes(
                "min-h-full", "pt-16",
//                "bg-opacity-40",
//                "bg-gradient-to-tr",
//                "from-purple-600",
//                "to-blue-300",
                "rounded-2xl"
            )
            style {
//                backgroundImage(SiteTheme.Gradients.background)
                backgroundImage(linearGradient( to = Color("e3e1efb0"), from = Color("#0b03c859"),
                     dir = LinearGradient.Direction.ToTopRight
                ))

            }
        }) {
            // Hero Section
            Section(attrs = { classes("my-2") }) {
                HomeHero( )
            }
            Br {}

            // Latest Articles Section (stub)
            Section(attrs = {
                classes(
//
                    "my-2"

                )
                style {
                    margin(top = 50.percent)
                }
            }) {
                UnderConstructionSection()
            }

//            // Latest Designs Section (stub)
//            Section(attrs = { classes("mb-8") }) {
//                H2(attrs = { classes("text-3xl", "font-bold", "mb-4", "text-blue-800") }) { Text("Latest Designs") }
//                // TODO: Replace with ImageList equivalent
//                Div(attrs = { classes("bg-white", "rounded-lg", "shadow", "p-6", "text-gray-700") }) {
//                    Text("Design preview content coming soon...")
//                }
//            }

            // Music Section (stub)
//            Section(attrs = { }) {
//                H2(attrs = {
//                    classes(
//                        "text-3xl",
//                        "font-bold",
//                        "mb-4",
//                        "text-pink-800"
//                    )
//                }) { Text("Music COMING SOON..") }
//                // TODO: Future music section
//            }
        }
    }
}

@Composable
private fun UnderConstructionSection() {
    Div({
        classes(
//            "m-4",
//                    "md:m-8",
//                    "lg:m-12",
            "mt-12",
            "w-full",
            "max-w-md",
            "md:max-w-lg",
            "lg:max-w-2xl",
            "mx-auto",
            "mb-8",
            "p-6",
            "md:p-10",
            "rounded-xl",
            "bg-gradient-to-br",
            "from-yellow-200",
            "via-pink-200",
            "to-purple-200",
            "shadow-lg")
    }){
        H1(
            attrs = {
                classes(
                    "text-2xl",
                    "md:text-3xl",
                    "font-bold",
                    "mb-4",
                    "text-gray-900",
                    "text-center"
                )
            }
        ) {
            Text("🚧 Under Construction")
        }

        P(
            attrs = {
                classes(
                    "text-base",
                    "md:text-lg",
                    "text-gray-900",
                    "text-center",
                    "leading-relaxed"
                )
            }
        ) {
            Text(
                "This portfolio is currently being migrated from React to Kobweb. " +
                        "The original hero content has been preserved above. Check back soon for the complete experience!"
            )
        }
    }
}
