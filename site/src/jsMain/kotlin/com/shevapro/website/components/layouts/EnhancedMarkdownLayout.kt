package com.shevapro.website.components.layouts

// Import our external declarations from the proper package
import androidx.compose.runtime.*
import com.shevapro.website.external.*
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobwebx.markdown.markdown
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.*

// Function to get test content as fallback
private fun getTestContent(): String {
    return ""
}

// Function to construct markdown file path from route
private fun getMarkdownFilePath(route: String): String {
    val normalizedRoute = route.removePrefix("/").replace("-", "_")
    return "/content/$normalizedRoute.md"
}

// Function to fetch markdown content
private suspend fun fetchMarkdownContent(filePath: String): String {
    try {
        val response = kotlinx.browser.window.fetch(filePath).await()

        if (!response.ok) {
            throw Exception("Failed to fetch: ${response.status} ${response.statusText}")
        }

        val text = response.text().await()

        // Check if we got HTML instead of markdown
        if (text.trim().startsWith("<!DOCTYPE html>") || text.contains("<html")) {
            console.warn("⚠️ Received HTML instead of markdown - file might not exist")
            throw Exception("File not found or server returned HTML page")
        }

        // Extract content after frontmatter
        val frontMatterEnd = text.indexOf("---", 3)
        return if (frontMatterEnd > 0) {
            console.log("Found frontmatter, extracting content...")
            text.substring(frontMatterEnd + 3).trim()
        } else {
            console.log("No frontmatter found, using full content...")
            text
        }
    } catch (e: Exception) {
        console.error("❌ Error fetching markdown:", e.message)
        throw e
    }
}

@Layout
@Composable
fun EnhancedMarkdownLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val title = ctx.markdown!!.frontMatter["title"]?.single() ?: "Article"
    val description = ctx.markdown!!.frontMatter["description"]?.single() ?: "Article"
    val thumbnailUrl = ctx.markdown?.frontMatter?.get("thumbnailUrl")?.single()
//    val isPosted = ctx.markdown?.frontMatter?.get("posted")?.single().toBoolean()
    val coverUrl = if (thumbnailUrl != null && thumbnailUrl.length > 42) thumbnailUrl
    else "/assets/images/${thumbnailUrl ?: "blank-image.webp"}"

    val currentRoute = ctx.route.path
    val markdownFilePath = remember(currentRoute) { getMarkdownFilePath(currentRoute) }

    var markdownContent by remember { mutableStateOf<String?>(null) }
    var isFetching by remember { mutableStateOf(true) }
    var fetchError by remember { mutableStateOf<String?>(null) }
    var processedHtml by remember { mutableStateOf<String?>(null) }
    var isProcessing by remember { mutableStateOf(true) }
    var processingError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(markdownFilePath) {
        try {
            isFetching = true
            fetchError = null

            markdownContent = fetchMarkdownContent(markdownFilePath)
        } catch (e: Exception) {
            console.error("❌ Error fetching markdown:", e)
            fetchError = "Could not load article content. Please check the file path and ensure the file exists."
            markdownContent = "# Error\n$fetchError"
        } finally {
            isFetching = false
        }
    }

//    LaunchedEffect(isPosted){
//        if (!isPosted) {
//                ctx.router.navigateTo("/")
//        }
//    }
    val processor = remember{
        unified()
            .use(remarkParse)
            .use(rehypeKatex)
            .use(remarkGfm, js("{ singleTilde: false }"))
            .use(remarkMath)
            .use(remarkRehype, js("{ allowDangerousHtml: true }"))
            .use(rehypeRaw)
            .use(rehypeStringify)
    }
    
    LaunchedEffect(markdownContent) {
        // print processor info as js object code
        console.log("Processor plugins:", processor.asDynamic().plugins)


        if (markdownContent != null) {
            try {
                isProcessing = true
                processingError = null



                val result = processor.process(markdownContent!!).await()
                processedHtml = result.value
                console.log("Processor data:", processor.asDynamic().data)
            } catch (e: Exception) {
                console.error("❌ Error processing markdown:", e)
                processingError = "Error processing markdown: ${e.message}"
                processedHtml = "<p style='color: red;'>Error processing markdown: ${e.message}</p>"
            } finally {
                isProcessing = false
            }
        }
    }

    Layout(title = "SHEVAPRO | $title", description = description) {
        Style {
            """
            @import url("https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.css");
            
            .loading-indicator {
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 2rem;
                color: #4a6fa5;
                font-style: italic;
                background-color: rgba(0, 0, 0, 0.03);
                border-radius: 4px;
                margin: 1rem 0;
                position: relative;
            }
            
            .loading-indicator::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 3px;
                background: linear-gradient(to right, #4a6fa5, #9eacd5);
                animation: loading-bar 2s infinite ease-in-out;
                border-radius: 4px 4px 0 0;
            }
            
            @keyframes loading-bar {
                0% { width: 0; left: 0; }
                50% { width: 100%; left: 0; }
                100% { width: 0; left: 100%; }
            }
            
            .error-message {
                background-color: rgba(220, 53, 69, 0.1);
                border-left: 4px solid #dc3545;
                padding: 1rem;
                margin: 1rem 0;
                color: #dc3545;
                border-radius: 4px;
            }
            
            .fetch-error {
                background-color: rgba(255, 193, 7, 0.1);
                border-left: 4px solid #ffc107;
                padding: 1rem;
                margin: 1rem 0;
                color: #856404;
                border-radius: 4px;
            }
            
            .katex-display {
                margin: 1.5rem 0;
                text-align: center;
            }
            
            .katex {
                font-size: 1.1em;
            }
            
            .katex-display .katex {
                font-size: 1.3em;
            }
            """.trimIndent()
        }

        Div(attrs = { classes("rounded", "min-h-full", "pt-8") }) {
            Section(attrs = {
                classes("bg-slate-100", "flex", "flex-col", "justify-around", "items-center", "p-2")
            }) {
                Picture(attrs = {
                    classes("w-full", "max-w-md", "md:max-w-lg", "mx-auto")
                }) {
                    // Mobile Image
                    Source(
                        attrs = {
//                            attr("media", "(max-width: 600px)")
                            attr("srcset", coverUrl)
                        }
                    )

                    // Desktop Image (commented out for later)
                    // Source(
                    //     attrs = {
                    //         attr("media", "(min-width: 601px)")
                    //         attr("srcset", coverUrl)
                    //     }
                    // )

                    // Fallback Image
                    Img(
                        src = coverUrl,
                        attrs = {
                            attr("aria-hidden", "true")
                            attr("loading", "lazy")
                            attr("decoding", "async")
                            attr("alt", "Article cover image")
                        }
                    )
                }

                H1(attrs = {
                    classes(
                        "m-4", "w-full", "font-mono", "text-center", "underline",
                        "text-2xl", "font-bold", "md:text-4xl"
                    )
                }) {
                    Text(title)
                }
            }

            Section(   attrs = {
                classes("flex", "flex-col")
                style {
                    minHeight(60.vh)
                }
            }) {
                Article(attrs = {
                    classes("processed-content", "px-2", "min-h-full", "md:px-5", "font-sans","flex-grow")
                }) {
                    when {
                        isFetching -> {
                            Div(attrs = { classes("loading-indicator") }) {
                                Text("Loading content from server...")
                            }
                        }

                        fetchError != null -> {
                            Div(attrs = { classes("fetch-error") }) {
                                Text("⚠️ $fetchError")
                            }
                        }

                        isProcessing -> {
                            Div(attrs = { classes("loading-indicator") }) {
                                Text("Processing content for display...")
                            }
                        }

                        processingError != null -> {
                            Div(attrs = { classes("error-message") }) {
                                Text("❌ $processingError")
                            }
//                            content()
                        }

                        processedHtml != null -> {
                            DisposableEffect(processedHtml) {
                                val currentElement = kotlinx.browser.document.querySelector("article.processed-content")
                                currentElement?.innerHTML = processedHtml!!
                                onDispose { }
                            }
                        }

                        else -> {
//                            content()
                        }
                    }
                }
            }
        }
    }
}