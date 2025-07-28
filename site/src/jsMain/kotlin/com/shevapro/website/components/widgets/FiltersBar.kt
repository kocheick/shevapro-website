package com.shevapro.website.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.shevapro.website.styles.SiteTheme
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun FiltersBar(
    tags: List<String>,
    activeTags: List<String>,
    onTagClick: (String) -> Unit,
    modifier: Modifier = Modifier // Accept for extension but no longer used directly
) {
    // Outer container div for width/flex
    Div(attrs = {
        classes("w-full", "md:w-4/5", "mx-auto")
    }) {
        // Tag bar as unordered list flex row
        Ul(attrs = {
            classes(
                "flex", "my-3", "list-none", "flex-wrap", "justify-around", "w-full", "p-2"
            )
        }) {
            tags.forEach { tag ->
                FilterTag(
                    tag = tag,
                    isActive = activeTags.contains(tag),
                    onClick = { onTagClick(tag) }
                )
            }
        }
    }
}

@Composable
private fun FilterTag(
    tag: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier // Accept for extension
) {
    val activeTagClasses = if (isActive)
        arrayOf("bg-gray-200", "scale-110", "font-bold")
    else
        arrayOf("md:hover:bg-gray-200", "md:hover:bg-opacity-60")

    Li(
        attrs = {
            onClick { onClick() }
            classes(
                *activeTagClasses,
                "text-zinc-800",
                "border",
                "cursor-pointer",
                "rounded-xl",
                "p-2",
                "m-1",
                "font-medium",
                "text-sm",
                "md:text-base",
                "transition-all",
                "duration-300"
            )
        }
    ) {
        Text(tag)
    }
}