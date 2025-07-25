package com.shevapro.website.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.shevapro.website.components.sections.Footer
import com.shevapro.website.components.sections.Header
import com.shevapro.website.styles.ThemeMode
import com.shevapro.website.styles.ThemeProvider
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.overflowY
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Main
import org.w3c.dom.Document

@Composable
fun Layout(
    title: String = "Shevapro",
    description: String = "Shevapro's website",
    themeMode: ThemeMode = ThemeMode.System,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    LaunchedEffect(title){

        document.setPageMetadata(title,description)
    }


    ThemeProvider(mode = themeMode) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(100.vh),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header - will be sticky due to CSS in Header component
            Header()

            // Main content - scrollable area that fills remaining space
            Main(
                attrs = {
                    classes(
                        "min-h-full",
                        "bg-gradient-to-tr",
                        "from-purple-600",
                        "to-blue-300",
                        "p-2",
                        "border-red-700",
                        "items-center",
                        "justify-center"
                    )
                    style {
                        property("flex", "1")
//                        property("overflow-y", "auto")
                        property("overflow-x", "hidden")
                        overflowY(Overflow.Scroll)
                        width(100.percent)
                        display(DisplayStyle.Flex)
                        justifyContent(JustifyContent.Center)
                        alignItems(AlignItems.FlexStart)
                        padding(16.px)
                    }
                }
            ) {
                    content()

            }

            // Footer - stays at bottom of screen
            Footer()
        }
    }
}

private fun Document.setPageMetadata(title: String, description: String) {
    this.title = title
    val head = this.head!!
    (head.querySelector("meta[name='description']")
        ?: this.createElement("meta").apply {
            setAttribute("name", description)
            head.appendChild(this)
        }
    ).setAttribute("content", description)
}

