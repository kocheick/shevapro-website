package com.shevapro.website.components.sections

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

data class StoryNode(
    val id: String,
    val title: String,
    val period: String? = null,
    val summary: String,
    val details: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val accentHex: String = "#8b5cf6",
    val emoji: String? = null
)

@Composable
fun StoryTree(nodes: List<StoryNode>) {
    Section(attrs = { classes("relative", "w-full") }) {
        // Vertical line
        Div(attrs = {
            classes("absolute", "left-4", "top-0", "bottom-0", "w-0.5", "bg-purple-300/60", "md:left-1/2")
        }) {}

        Ul(attrs = {
            classes("list-none", "m-0", "p-0", "space-y-8")
        }) {
            nodes.forEachIndexed { index, node ->
                Li(attrs = {
                    classes(
                        "relative",
                        "pl-12",
                        "md:grid",
                        "md:grid-cols-2",
                        "md:gap-8"
                    )
                }) {
                    // Timeline dot
                    Div(attrs = {
                        classes(
                            "absolute",
                            "left-3.5",
                            "top-3",
                            "h-3",
                            "w-3",
                            "rounded-full",
                            "bg-purple-600",
                            "md:left-[calc(50%-6px)]"
                        )
                    }) {}

                    // Card container: alternate sides on md+
                    val placeLeft = index % 2 == 0
                    val leftClasses = if (placeLeft) listOf("md:col-start-1") else listOf("md:col-start-2")

                    Article(attrs = {
                        classes(
                            "bg-gray-100/80",
                            "backdrop-blur-sm",
                            "rounded-xl",
                            "shadow",
                            "ring-1",
                            "ring-black/5",
                            "p-5",
                            "transition-colors",
                            "duration-300",
                            "hover:shadow-xl",
                            "hover:bg-white/90"
                        )
                        classes(*leftClasses.toTypedArray())
                    }) {
                        var expanded by remember(node.id) { mutableStateOf(false) }
                        // Title row
                        Div(attrs = { classes("flex", "items-center", "gap-2", "mb-1") }) {
                            if (node.emoji != null) {
                                Span(attrs = { classes("text-xl") }) { Text(node.emoji) }
                            }
                            H3(attrs = { classes("text-lg", "md:text-xl", "font-semibold", "text-gray-900") }) {
                                Text(node.title)
                            }
                        }

                        if (!node.period.isNullOrBlank()) {
                            P(attrs = { classes("text-sm", "text-gray-600", "mb-2") }) { Text(node.period!!) }
                        }

                        P(attrs = { classes("text-gray-800", "leading-relaxed") }) { Text(node.summary) }

                        val detailsDynamic = if (expanded) listOf(
                            "opacity-100",
                            "max-h-[1000px]",
                            "translate-y-0",
                            "scale-100"
                        ) else listOf("opacity-0", "max-h-0", "-translate-y-1", "scale-95")
                        Div(attrs = {
                            classes(
                                "mt-3",
                                "space-y-3",
                                "overflow-hidden",
                                "transition-all",
                                "duration-300",
                                "ease-in-out",
                                "transform",
                                "origin-top"
                            )
                            classes(*detailsDynamic.toTypedArray())
                        }) {
                            if (node.details.isNotEmpty()) {
                                node.details.forEach { paragraph ->
                                    P(attrs = { classes("text-gray-800", "leading-relaxed") }) { Text(paragraph) }
                                }
                            }
                        }

                        // Read more / less control
                        if (node.details.isNotEmpty()) {
                            Div(attrs = { classes("mt-4") }) {
                                Button(attrs = {
                                    classes(
                                        "text-sm",
                                        "font-medium",
                                        "text-purple-700",
                                        "hover:text-purple-900",
                                        "cursor-pointer"
                                    )
                                    onClick { expanded = !expanded }
                                }) {
                                    Text(if (expanded) "Show less" else "Read more")
                                }
                                // Toggle state when button clicked
                                // Note: Using onClick on the Button tag
                                // Compose HTML Button has its own onClick lambda via attrs DSL
                            }
                        }

                        if (node.tags.isNotEmpty()) {
                            Ul(attrs = { classes("flex", "flex-wrap", "gap-2", "mt-4", "p-0") }) {
                                node.tags.forEach { tag ->
                                    Li(attrs = {
                                        classes(
                                            "px-2.5",
                                            "py-1",
                                            "text-xs",
                                            "font-medium",
                                            "bg-purple-50",
                                            "text-purple-800",
                                            "border",
                                            "border-purple-200",
                                            "rounded-full",
                                            "shadow-sm"
                                        )
                                    }) { Text(tag) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
