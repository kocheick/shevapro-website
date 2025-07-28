package com.shevapro.website.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.shevapro.website.styles.SiteTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.compose.ui.toStyles
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button as HtmlButton
import org.jetbrains.compose.web.dom.Text

enum class ButtonVariant {
    Primary, Secondary, Outline
}

@Composable
fun Button(
    text: String,
    onClick: (() -> Unit)? = null,
    path: String? = null,
    variant: ButtonVariant = ButtonVariant.Primary,
    modifier: Modifier = Modifier
) {
    val buttonModifier = modifier
        .padding(12.px, 24.px)
        .borderRadius(8.px)
        .fontWeight(FontWeight.Medium)
        .textAlign(TextAlign.Center)
        .cursor(Cursor.Pointer)
        .then(
            when (variant) {
                ButtonVariant.Primary -> Modifier
                    .backgroundColor(SiteTheme.Colors.primary)
                    .color(SiteTheme.Colors.surface)
                    .border(2.px, LineStyle.Solid, SiteTheme.Colors.primary)

                ButtonVariant.Secondary -> Modifier
                    .backgroundColor(SiteTheme.Colors.secondary)
                    .color(SiteTheme.Colors.surface)
                    .border(2.px, LineStyle.Solid, SiteTheme.Colors.secondary)

                ButtonVariant.Outline -> Modifier
                    .backgroundColor(rgba(0, 0, 0, 0.0))
                    .color(SiteTheme.Colors.primary)
                    .border(2.px, LineStyle.Solid, SiteTheme.Colors.primary)
            }
        )

    if (path != null) {
        Link(
            path = path,
            modifier = buttonModifier.textDecorationLine(TextDecorationLine.None),
            text = text
        )
    } else if (onClick != null) {
        Link(
            path ="",
            modifier = buttonModifier.onClick { onClick() }.textDecorationLine(TextDecorationLine.None),
            text = text
        )
    }
}