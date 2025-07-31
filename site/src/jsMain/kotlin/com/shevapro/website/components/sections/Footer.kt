package com.shevapro.website.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.borderColor
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun Footer() {
    Footer(
        attrs = {
            classes(
                "rounded-t","bg-gradient-to-tr", "from-green-200", "to-yellow-300", "shrink-0",
                "border-orange-700", "w-full", "h-100", "bg-yellow-300", "py-2",
                "flex", "items-center", "justify-center"
            )
            style {
                background("linear-gradient(to top right, rgb(187, 247, 208), rgb(254, 240, 138))")
                property("flex-shrink", "0")
                borderColor(rgb(194, 120, 3))
                width(100.percent)
//                height(100.px)
                backgroundColor(rgb(254, 240, 138))
                paddingTop(8.px)
                paddingBottom(8.px)
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
            }
        }
    ) {
        Div(
            attrs = {
                classes("text-center", "font-medium", "text-base", "sm:text-xl")
                style {
                    textAlign("center")
                    fontWeight("500")
//                    fontSize(16.px)
                }
            }
        ) {
            P(
                attrs = {
                    style {
                        margin(0.px)
                        lineHeight(1.5.cssRem)
                    }
                }
            ) {
                Text("Made with")
                Span(
                    attrs = {
                        style {
                            color(rgb(226, 85, 85)) // #e25555
                            property("font-size", "inherit")
                        }
                    }
                ) {
                    Text(" ❤️ ")
                }
                Text(" by SHEVAPRO © ${getCurrentYear()}")
            }
        }
    }
}

// Helper function to get current year
private fun getCurrentYear(): Int {
    return js("new Date().getFullYear()").toString().toInt()
}