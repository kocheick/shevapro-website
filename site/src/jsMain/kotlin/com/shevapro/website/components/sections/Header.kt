package com.shevapro.website.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

const val MEDIUM_SCREEN_SIZE = 768
const val DARK = "md:border-0 dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700"

val navMenuItems = listOf("portfolio", "design", "blog", "services",
//    "music", "contact",
    "about")
val size = navMenuItems.size
// val menuHeight = "h-${12*navMenuItems.size}"
 const val menuHeight = "h-${12*5}"

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    var isNavBarOpen by remember { mutableStateOf(false) }
    val ctx = rememberPageContext()
    val currentPath = ctx.route.path.removePrefix("/")

    // Close navbar when route changes (similar to useEffect in original)
    LaunchedEffect(currentPath) {
        if (isNavBarOpen) {
            isNavBarOpen = false
        }
    }

    Nav(
        attrs = modifier
            .fillMaxWidth()
            .toAttrs {
                classes("shadow", "rounded-b", "w-full", "bg-gray-50", "py-1")
//                style {
//                    backgroundColor(rgb(249, 250, 251))
//                    property("box-shadow", "0 1px 3px 0 rgba(0, 0, 0, 0.1)")
//                    borderRadius(0.px, 0.px, 8.px, 8.px)
//                    width(100.percent)
//                    paddingTop(4.px)
//                    paddingBottom(4.px)
//                }
            }
    ) {
        Div(
            attrs = {
                classes(
                    "md:p-4",
                    "flex",
                    "flex-wrap",
                    "justify-between",
                    "2xl:justify-around",
                    "items-center",
                    "mx-2",
                    "md:mx-3"
                )
//                style {
//                    display(DisplayStyle.Flex)
//                    property("flex-wrap", "wrap")
//                    justifyContent(JustifyContent.SpaceBetween)
//                    alignItems(AlignItems.Center)
//                    marginLeft(8.px)
//                    marginRight(8.px)
//                    padding(16.px)
//                }
            }
        ) {
            // WEBSITE LOGO
            Link(
                path = "/",
                modifier = Modifier
                    .attrsModifier {
                        classes("flex", "items-center", "shrink-0")
                    }
            ) {
                Img(
                    src = "/assets/images/logo.png",
                    alt = "Post image",
                    attrs = {
                        classes("ml-3", "h-12")
                    }
                )
            }

            // MOBILE MENU BUTTON
            Button(
                onClick = { isNavBarOpen = !isNavBarOpen },
                modifier = Modifier
                    .attrsModifier {
                        classes(
                            "inline-flex",
                            "items-center",
                            "p-2",
                            "ml-3",
                            "text-sm",
                            "text-gray-500",
                            "rounded-lg",
                            "md:hidden",
                            "hover:bg-gray-100",
                            "focus:outline-none",
                            "focus:ring-2",
                            "focus:ring-gray-200",
                            "dark:text-gray-400",
                            "dark:hover:bg-gray-700",
                            "dark:focus:ring-gray-600"
                        )

//                                         attr("type", "button")
                        attr("aria-controls", "navbar-default")
                        attr("aria-expanded", "false")
                        attr("aria-label", "Toggle Navigation")
//                        style {
//                            property("display", "inline-flex")
//                            alignItems(AlignItems.Center)
//                            padding(8.px)
//                            marginLeft(12.px)
//                            fontSize(14.px)
//                            color(rgb(107, 114, 128))
//                            borderRadius(8.px)
//                            backgroundColor(Color.transparent)
//                            border(0.px)
//                        }
                    }
            ) {
                // Hamburger icon - simplified
                Svg(attrs = {
                    attr("xmlns", "http://www.w3.org/2000/svg")
                    classes("w-6", "h-6")
                    attr("aria-hidden", "true")
                    attr("fill", "currentColor")
                    attr("viewBox", "0 0 20 20")

                }) {
                    Path(
                        attrs = {
                            attr("fillRule", "evenodd")
                            attr("d", "M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z")
                            attr("clipRule", "evenodd")
                        }
                    )
                }
            }

            // PRIMARY MENU ITEMS
            Div(
                attrs = {
                    id("menu")
                    classes(
                        if (isNavBarOpen) menuHeight else "invisible",
                        if (isNavBarOpen) "visible" else "h-0",
                        "font-normal", "md:visible", "md:h-auto", "ease-in-out", "transition-all",
                        "duration-300", "md:transition-none", "md:block", "w-full", "md:w-auto"
                    )

                }
            ) {
                Ul(
                    attrs = {
                        id("ulMenu")
                        classes(
                            "md:h-auto", "flex", "flex-col", "md:flex-row", "items-end", "rounded-lg",
                            "md:space-x-8", "md:mt-0", "md:text-sm", "md:font-medium"
                        )
                    }
                ) {
                    navMenuItems.forEach { item ->
                        val isActive = currentPath.contains(item)

                        Li(
                            attrs = {
                                classes("w-full")
                            }
                        ) {
                            Link(
                                path = "/$item",
                                text = item.replaceFirstChar { it.uppercase() },
                                modifier = Modifier
                                    .attrsModifier {
                                        classes(
                                            if (isActive) "text-red-700" else ",",
                                            "block", "transition-all", "flex", "justify-end", "text-2xl",
                                            "py-2", "pr-2", "pl-3", "text-gray-700", "rounded", "md:rounded-none",
                                            "hover:bg-gray-100", "md:hover:bg-transparent", "md:hover:border-b",
                                            "border-red-700", "md:p-0"
                                        )
                                        onClick { isNavBarOpen = false }

                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}