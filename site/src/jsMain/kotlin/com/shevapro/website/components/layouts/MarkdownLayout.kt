package com.shevapro.website.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.styles.MarkdownStyles
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobwebx.markdown.markdown
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

// Import our external declarations for unified processing
import com.shevapro.website.external.unified
import com.shevapro.website.external.remarkParse
import com.shevapro.website.external.remarkGfm
import com.shevapro.website.external.remarkRehype
import com.shevapro.website.external.rehypeRaw
import com.shevapro.website.external.rehypeStringify

@Layout
@Composable
fun MarkdownLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val title = ctx.markdown!!.frontMatter["title"]?.single() ?: "Article"
    val description = ctx.markdown!!.frontMatter["description"]?.single() ?: "Article"
    val thumbnailUrl = ctx.markdown?.frontMatter?.get("thumbnailUrl")?.single()
    val coverUrl =
        if (thumbnailUrl != null && thumbnailUrl.length > 42) thumbnailUrl else "/assets/images/${thumbnailUrl ?: "blank-image.jpeg"}"
    
    // Check if the post is published
    val isPosted = ctx.markdown!!.frontMatter["posted"]?.single()?.toBoolean() ?: true
    
    // If the post is not published, redirect to the home page
    LaunchedEffect(isPosted) {
        if (!isPosted) {
            console.log("🚫 Attempted to access unpublished post: ${ctx.route.path}")
            // Redirect to the home page
            ctx.router.navigateTo("/")
        }
    }

    // State for unified processing
    var originalContentHtml by remember { mutableStateOf<String?>(null) }
    var processedHtml by remember { mutableStateOf<String?>(null) }
    var isProcessing by remember { mutableStateOf(false) }
    var processingError by remember { mutableStateOf<String?>(null) }

    // Capture the original rendered content
    LaunchedEffect(Unit) {
        // Get the original content HTML by temporarily rendering it
        val tempDiv = kotlinx.browser.document.createElement("div")
        // We'll process this after we get the HTML, for now we'll enhance what we have
        isProcessing = false
    }

    Layout(title = "SHEVAPRO | $title", description = description) {
        // Add CSS styles for markdown content + enhanced GFM styles
        Style {
            MarkdownStyles.markdownContentCSS

            // Enhanced styles for GFM features from unified processing
            """
            /* Enhanced GFM Features */
            .enhanced-markdown del {
                text-decoration: line-through;
                color: #6c757d;
                opacity: 0.8;
            }
            
            .enhanced-markdown input[type="checkbox"] {
                margin-right: 0.5em;
                margin-left: 0;
                accent-color: #bb9af7;
            }
            
            .enhanced-markdown li input[type="checkbox"] {
                margin-right: 0.5em;
                position: relative;
                top: 2px;
            }
            
            .enhanced-markdown mark {
                background-color: #fff3cd;
                color: #856404;
                padding: 2px 4px;
                border-radius: 3px;
            }
            
            .enhanced-markdown u {
                text-decoration: underline;
                text-decoration-color: #bb9af7;
            }
            
            /* Enhanced table styling */
            .enhanced-markdown table {
                border-collapse: collapse;
                border-spacing: 0;
                margin: 1.5rem 0;
                font-size: 0.9rem;
            }
            
            .enhanced-markdown table th,
            .enhanced-markdown table td {
                border: 1px solid rgba(139, 0, 0, 0.3);
                padding: 8px 12px;
                text-align: left;
            }
            
            .enhanced-markdown table th {
                background-color: rgba(139, 0, 0, 0.4);
                font-weight: 600;
                color: #bb9af7;
            }
            
            .enhanced-markdown table tr:nth-child(even) {
                background-color: rgba(139, 0, 0, 0.05);
            }
            
            /* Enhanced code block styling */
            .enhanced-markdown pre {
                position: relative;
                overflow-x: auto;
            }
            
            .enhanced-markdown pre code {
                display: block;
                padding: 1rem;
                line-height: 1.5;
            }
            
            /* Enhanced autolink styling */
            .enhanced-markdown a[href^="http"]:after {
                content: " ↗";
                font-size: 0.8em;
                opacity: 0.6;
            }
            
            /* Enhanced task list styling */
            .enhanced-markdown ul li {
                list-style: none;
                position: relative;
            }
            
            .enhanced-markdown ul li:has(input[type="checkbox"]) {
                padding-left: 0;
            }
            
            .enhanced-markdown ul li input[type="checkbox"]:checked + * {
                text-decoration: line-through;
                opacity: 0.7;
            }
            
            /* Processing state styles */
            .processing-indicator {
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 2rem;
                color: #9eacd5;
                font-style: italic;
            }
            
            .error-message {
                background-color: rgba(220, 53, 69, 0.1);
                border-left: 4px solid #dc3545;
                padding: 1rem;
                margin: 1rem 0;
                color: #dc3545;
                border-radius: 4px;
            }
            """.trimIndent()
        }

        // Main container with gradient background
        Div(
            attrs = {
                classes("rounded", "min-h-full", "pt-8")
            }
        ) {
            // Cover image + title section
            Section(
                attrs = {
                    classes("bg-slate-100", "flex", "flex-col", "justify-around", "items-center", "p-2")
                }
            ) {
                // Cover image
                Img(
                    src = coverUrl,
                    attrs = {
                        classes("rounded")
                        style {
                            width(500.px)
                        }
                    }
                )

                // Title
                H1(
                    attrs = {
                        classes(
                            "m-4",
                            "w-full",
                            "font-mono",
                            "text-center",
                            "underline",
                            "text-2xl",
                            "font-bold",
                            "md:text-4xl"
                        )

                    }
                ) {
                    Text(title)
                }
            }

            // Article content section with enhanced processing
            Section(
                attrs = {
                    style {
                        height(100.percent)
                    }
                }
            ) {
                Article(
                    attrs = {
                        classes("markdown-content", "enhanced-markdown", "px-2", "min-h-full", "md:px-5", "font-sans")
                    }
                ) {
                    when {
                        isProcessing -> {
                            Div(attrs = { classes("processing-indicator") }) {
                                Text("🔄 Processing markdown with unified pipeline...")
                            }
                        }

                        processingError != null -> {
                            Div(attrs = { classes("error-message") }) {
                                Text("❌ $processingError")
                            }
                            // Fallback to original content
                            content()
                        }

                        processedHtml != null -> {
                            Div(attrs = {
                                ref { element ->
                                    // Set the processed HTML content
                                    element.innerHTML = processedHtml!!
                                    onDispose { }
                                }
                            })
                        }

                        else -> {
                            // Default: use the original content with enhanced styling
                            content()
                        }
                    }
                }
            }
        }
    }
}