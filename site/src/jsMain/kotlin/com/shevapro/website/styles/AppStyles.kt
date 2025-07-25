package com.shevapro.website.styles

import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.*

object AppStyles {

    val globalAppCSS = """
        html, body {
            padding: 0;
            margin: 0;
            font-size: 16px;
        }

        body {
            background-color: #1F2233;
            transition-duration: 300ms;
            font-family: Roboto, serif;
            color: #9eacd5;
        }

        #_kobweb-root {
            display: flex;
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin-left: auto;
            margin-right: auto;
            background-image: linear-gradient(to top right, #7C3AED, #93C5FD);
            flex-direction: column;
        }

        main {
            flex: 1;
            overflow-y: auto;
        }

        @media (min-width: 1024px) {
            main {
                margin-left: auto;
                margin-right: auto;
                max-width: 64rem;
                box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
            }
        }

        /* Scrollbar styling for dark theme */
        ::-webkit-scrollbar {
            width: 8px;
        }

        ::-webkit-scrollbar-track {
            background: #1F2233;
        }

        ::-webkit-scrollbar-thumb {
            background: #7C3AED;
            border-radius: 4px;
        }

        ::-webkit-scrollbar-thumb:hover {
            background: #6D28D9;
        }

        /* Selection styling */
        ::selection {
            background-color: rgba(124, 58, 237, 0.3);
            color: #bb9af7;
        }

        ::-moz-selection {
            background-color: rgba(124, 58, 237, 0.3);
            color: #bb9af7;
        }
    """.trimIndent()
}