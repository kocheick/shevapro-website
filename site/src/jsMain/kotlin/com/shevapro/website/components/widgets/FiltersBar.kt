package com.shevapro.website.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.shevapro.website.styles.SiteTheme
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun FiltersBar(
    tags: List<String>,
    activeTags: List<String>,
    onTagClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .flexWrap(FlexWrap.Wrap)
            .justifyContent(JustifyContent.Center)
            .gap(SiteTheme.Spacing.sm)
            .padding(SiteTheme.Spacing.lg),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tags.forEach { tag ->
            FilterTag(
                tag = tag,
                isActive = activeTags.contains(tag),
                onClick = { onTagClick(tag) }
            )
        }
    }
}

@Composable
private fun FilterTag(
    tag: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Div(
        attrs = {
            onClick { onClick() }
            style {
                padding(8.px, 16.px)
                borderRadius(12.px)
                border(1.px, LineStyle.Solid, SiteTheme.Colors.textSecondary)
                backgroundColor(
                    if (isActive) SiteTheme.Colors.gray100 else rgba(0, 0, 0, 0.0)
                )
                color(SiteTheme.Colors.text)
                cursor("pointer")
                fontWeight(if (isActive) "bold" else "500")
                fontSize(14.px)
                margin(4.px)
            }
        }
    ) {
        Text(tag)
    }
}