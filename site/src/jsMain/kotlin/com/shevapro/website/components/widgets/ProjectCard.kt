package com.shevapro.website.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.shevapro.website.models.Article
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.objectFit
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ProjectCard(
    project: Article,
    onTagClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Div(
        attrs = {
            style {
                width(288.px) // w-72
                backgroundColor(rgba(229, 231, 235, 0.6)) // bg-gray-200 bg-opacity-60
                borderRadius(16.px)
                margin(8.px)
                overflow("hidden")
            }
        }
    ) {
        // Project Image
        Link(
            path = "/portfolio/${project.slug}",
            modifier = Modifier.fillMaxWidth()
        ) {
            Img(
                src = project.imageUrl ?: "/favicon.ico",
                attrs = {
                        classes("md:hover:scale-105", "transition-all", "rounded", "w-full")
                   
                }
            )

            // Project Title
            H2(
                attrs = {
                    classes("text-lg", "text-center", "text-gray-900", "font-semibold", "font-medium")
                }
            ) {
                Text(project.title)
            }
        }

        // Project Details
        Div(
            attrs = {
                classes("p-1","text-center")

            }
        ) {
            // Date
            H3(
                attrs = {
                    classes("text-gray-700", "font-light", "text-base")
                    
                }
            ) {
                Text(project.dateAdded)
            }

            // Description
            H4(
                attrs = {
                    classes("text-gray-700", "text-sm", "lg:text-base", "my-4", "line-clamp-6")
                    
                }
            ) {
                Text(project.description)
            }

            // Tags
            Ul (
                attrs = {
                    classes("flex", "list-none", "flex-wrap", "justify-around")


                }
            ) {
                project.tags.forEach { tag ->
                    Li (
                        attrs = {
                            onClick { onTagClick(tag) }
                            classes(
                                "bg-gray-50",
                                "cursor-pointer",
                                "bg-opacity-40",
                                "font-medium",
                                "font-mono",
                                "md:text-xs",
                                "text-blue-900",
                                "inline",
                                "h-min",
                                "p-2",
                                "m-1",
                                "rounded-full"
                            )

                            style {

                                fontSize(12.px)
                                cursor("pointer")
                              
                            }
                        }
                    ) {
                        Text(tag)
                    }
                }
            }
        }
    }
}