package com.shevapro.website.styles

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.Window
import org.w3c.dom.get

enum class ThemeMode {
    Light, Dark, System
}

val LocalThemeMode = compositionLocalOf { ThemeMode.System }

object SiteTheme {
    object Colors {
        // Primary app colors based on original app.css
        val primary = rgb(0x7C, 0x3A, 0xED) // Purple from gradient
        val secondary = rgb(0x93, 0xC5, 0xFD) // Blue from gradient
        val accent = rgb(0xF5, 0x9E, 0x0B)

        // Main backgrounds
        val background = rgb(0x1F, 0x22, 0x33) // #1F2233 from original
        val surface = rgb(0x1a, 0x1b, 0x26) // Article background from blog.css

        // Text colors from blog.css
        val text = rgb(0x9e, 0xac, 0xd5) // #9eacd5 main text
        val textSecondary = rgb(0xd5, 0xd6, 0xe0) // #d5d6e0 secondary text
        val textHeading = rgb(0xbb, 0x9a, 0xf7) // #bb9af7 headings

        // Link color from blog.css
        val link = rgb(0xCD, 0x5C, 0x5C) // indianred

        // Light theme colors (fallback)
        object Light {
            val background = rgb(0xFA, 0xFA, 0xFA)
            val surface = rgb(0xFF, 0xFF, 0xFF)
            val text = rgb(0x1F, 0x29, 0x37)
            val textSecondary = rgb(0x6B, 0x72, 0x80)
        }

        // Dark theme colors (primary theme)
        object Dark {
            val background = rgb(0x1F, 0x22, 0x33) // Main background
            val surface = rgb(0x1a, 0x1b, 0x26) // Surface/article background
            val text = rgb(0x9e, 0xac, 0xd5) // Main text
            val textSecondary = rgb(0xd5, 0xd6, 0xe0) // Secondary text
            val textHeading = rgb(0xbb, 0x9a, 0xf7) // Headings
        }

        // Code colors from blog.css
        val codeBackground = rgba(139, 0, 0, 0.5) // Pre/blockquote background
        val codeText = rgb(0xd5, 0xd6, 0xe0)
        val inlineCodeBg = rgb(0xd2, 0xc2, 0xae)
        val inlineCodeText = rgb(0x99, 0x00, 0x00)

        // Extended color palette
        val blue50 = rgb(0xEF, 0xF6, 0xFF)
        val blue500 = rgb(0x93, 0xC5, 0xFD) // From gradient
        val blue900 = rgb(0x1E, 0x3A, 0x8A)

        val purple500 = rgb(0x7C, 0x3A, 0xED) // From gradient
        val purple700 = rgb(0x6D, 0x28, 0xD9)

        val emerald50 = rgb(0xEC, 0xFD, 0xF5)
        val emerald500 = rgb(0x10, 0xB9, 0x81)
        val emerald900 = rgb(0x06, 0x4E, 0x3B)

        val gray50 = rgb(0xF9, 0xFA, 0xFB)
        val gray100 = rgb(0xF3, 0xF4, 0xF6)
        val gray500 = rgb(0x6B, 0x72, 0x80)
        val gray900 = rgb(0x1F, 0x22, 0x33) // Match main background
    }

    object Typography {
        val h1 = 2.5.cssRem
        val h2 = 2.25.cssRem
        val h3 = 1.75.cssRem
        val h4 = 1.5.cssRem
        val h5 = 1.25.cssRem
        val body = 1.cssRem
        val small = 0.875.cssRem
    }

    object Spacing {
        val xs = 4.px
        val sm = 8.px
        val md = 16.px
        val lg = 24.px
        val xl = 32.px
        val xxl = 48.px
    }

    object BorderRadius {
        val sm = 4.px
        val md = 6.px
        val lg = 8.px
        val xl = 12.px
    }

    object Shadows {
        val sm = "0 1px 2px 0 rgba(0, 0, 0, 0.05)"
        val md = "0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)"
        val lg = "0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05)"
        val xl = "0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)"
        val container = "0 25px 50px -12px rgba(0, 0, 0, 0.25)" // From original main container
    }

    object Gradients {
        val primary = "linear-gradient(to top right, #7C3AED, #93C5FD)" // Original gradient
        val primaryDark = "linear-gradient(to top right, #6D28D9, #7DD3FC)"
    }
}

@Composable
fun ThemeProvider(
    mode: ThemeMode = ThemeMode.System,
    content: @Composable () -> Unit
) {
    val isDark = when (mode) {
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
        ThemeMode.System -> js("window.matchMedia('(prefers-color-scheme: dark)').matches").unsafeCast<Boolean>()
    }
    
    CompositionLocalProvider(
        LocalThemeMode provides mode
    ) {
        Div(
            attrs = {
                classes(if (isDark) "dark" else "light")
            }
        ) {
            content()
        }
    }
}