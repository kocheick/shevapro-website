package com.shevapro.website

import androidx.compose.runtime.*
import com.shevapro.website.pages.NotFound
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.shevapro.website.styles.AppStyles
import com.shevapro.website.styles.MarkdownStyles
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.init.InitKobweb
import com.varabyte.kobweb.core.init.InitKobwebContext
import com.varabyte.kobwebx.markdown.MarkdownContext
import org.jetbrains.compose.web.css.*

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    // Register global app styles
    ctx.stylesheet.registerStyleBase("*") {
        Modifier.styleModifier {
            property("box-sizing", "border-box")
        }
    }

    // Inject global CSS styles
    ctx.stylesheet.registerStyleBase("app") {
        Modifier.styleModifier {
//            property("font-size", "16px")
        }
    }

//    // Add our custom CSS to the document head
//    val style = kotlinx.browser.document.createElement("style")
//    style.textContent = "${AppStyles.globalAppCSS}\n\n${MarkdownStyles.markdownContentCSS}"
//    kotlinx.browser.document.head?.appendChild(style)
}

@InitKobweb
fun initKobweb(ctx: InitKobwebContext) {
//    ctx.router.setErrorPage {
//        NotFound()
//    }


}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    kotlinext.js.require("./public/app.css")
    kotlinext.js.require("./public/blog.css")
    SilkApp {
        Surface(SmoothColorStyle.toModifier().fillMaxHeight()) {
            content()
        }
    }
}
