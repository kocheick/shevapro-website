package com.shevapro.website.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.fetch.RequestInit
import kotlin.js.Promise

// Import our external declarations from the proper package
import com.shevapro.website.external.unified
import com.shevapro.website.external.remarkParse
import com.shevapro.website.external.remarkGfm
import com.shevapro.website.external.remarkRehype
import com.shevapro.website.external.rehypeRaw
import com.shevapro.website.external.rehypeStringify
import com.varabyte.kobwebx.markdown.markdown

// Function to get test content as fallback
private fun getTestContent(): String {
    return """
        # GFM Test Content

        ## Strikethrough
        ~~This text should be struck through~~

        ## Tables
        | Feature | Status | Notes |
        |---------|--------|-------|
        | Strikethrough | ✅ | Should work with remark-gfm |  
        | Tables | ✅ | Like this one |
        | Task Lists | ✅ | See below |
        | HTML in Markdown | ✅ | With rehype-raw |

        ## Task Lists
        - [x] Completed task
        - [ ] Pending task  
        - [x] Another completed task
        - [ ] Another pending task

        ## HTML Elements
        This should work with <u>underlined text</u> and <mark>highlighted text</mark>.

        <div style="background-color: #f0f0f0; padding: 10px; border-left: 4px solid #007acc;">
        <strong>Note:</strong> This is HTML content inside markdown that should be preserved with rehype-raw.
        </div>
    """.trimIndent()
}

// Function to extract markdown content from HTML
private fun extractMarkdownFromHtml(htmlContent: String): String? {
    // Check if the content is actually HTML
    val isHtml = htmlContent.trim().startsWith("<!DOCTYPE html>") || htmlContent.contains("<html")
    if (!isHtml) {
        return null // Not HTML, no extraction needed
    }
    
    console.log("Attempting to extract markdown content from HTML...")
    
    // Try different extraction methods
    
    // Method 1: Look for content between ```markdown and the last ```
    val markdownStart = htmlContent.indexOf("```markdown")
    val markdownEnd = htmlContent.lastIndexOf("```")
    
    if (markdownStart > 0 && markdownEnd > markdownStart) {
        // Extract content between ```markdown and the last ```
        console.log("Found markdown content between code blocks")
        return htmlContent.substring(markdownStart + 11, markdownEnd).trim()
    }
    
    // Method 2: Look for content in a pre tag
    val preStart = htmlContent.indexOf("<pre>")
    val preEnd = htmlContent.indexOf("</pre>")
    
    if (preStart > 0 && preEnd > preStart) {
        // Extract content between <pre> and </pre>
        console.log("Found content in pre tags")
        return htmlContent.substring(preStart + 5, preEnd).trim()
    }
    
    // Method 3: Try to find content in a div with class containing "markdown"
    val divClassStart = htmlContent.indexOf("class=\"")
    if (divClassStart > 0) {
        val classNameStart = divClassStart + 7
        val classNameEnd = htmlContent.indexOf("\"", classNameStart)
        if (classNameEnd > classNameStart) {
            val className = htmlContent.substring(classNameStart, classNameEnd)
            if (className.contains("markdown") || className.contains("md-content")) {
                // This is a potential markdown container div
                val divStart = htmlContent.lastIndexOf("<div", divClassStart)
                if (divStart > 0) {
                    // Find the closing div
                    var depth = 1
                    var pos = divStart + 4
                    while (depth > 0 && pos < htmlContent.length) {
                        val openTag = htmlContent.indexOf("<div", pos)
                        val closeTag = htmlContent.indexOf("</div>", pos)
                        
                        if (closeTag < 0) break // No more closing tags
                        
                        if (openTag > 0 && openTag < closeTag) {
                            depth++
                            pos = openTag + 4
                        } else {
                            depth--
                            pos = closeTag + 6
                            if (depth == 0) {
                                // Found the matching closing div
                                val divContent = htmlContent.substring(divStart, closeTag + 6)
                                console.log("Found content in div with markdown class")
                                // Strip HTML tags
                                val strippedContent = js("divContent.replace(/<[^>]*>/g, '')")
                                return strippedContent as String
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Method 4: As a last resort, strip all HTML tags
    console.warn("Could not find specific markdown content. Stripping all HTML tags as fallback.")
    val strippedHtml = js("htmlContent.replace(/<[^>]*>/g, '')")
    return strippedHtml as String
}

// Function to construct markdown file path from route
private fun getMarkdownFilePath(route: String): String {
    val normalizedRoute = route.removePrefix("/")
    return when {
        normalizedRoute.startsWith("blog/") -> {
            val filename = normalizedRoute.substringAfter("blog/").replace("-", "_")
            "/markdown/blog/$filename.md"
        }

        normalizedRoute.startsWith("portfolio/") -> {
            val filename = normalizedRoute.substringAfter("portfolio/").replace("-", "_")
            "/markdown/portfolio/$filename.md"
        }

        else -> "/markdown/$normalizedRoute.md"
    }
}

// Function to fetch markdown content
private suspend fun fetchMarkdownContent(filePath: String): String {
    try {
        console.log("🔍 Fetching markdown from: $filePath")
        val response = kotlinx.browser.window.fetch(filePath).await()
        
        if (!response.ok) {
            throw Exception("Failed to fetch: ${response.status} ${response.statusText}")
        }
        
        val text = response.text().await()
        console.log("📄 Fetched ${text.length} characters")

        // Check if we got HTML instead of markdown
        if (text.trim().startsWith("<!DOCTYPE html>") || text.contains("<html")) {
            console.warn("⚠️ Received HTML instead of markdown - file might not exist")
            throw Exception("File not found or server returned HTML page")
        }

        // Extract content after frontmatter
        val frontMatterEnd = text.indexOf("---", 3)
        return if (frontMatterEnd > 0) {
            text.substring(frontMatterEnd + 3).trim()
        } else {
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
    val coverUrl = if (thumbnailUrl != null && thumbnailUrl.length > 42) thumbnailUrl
    else "/assets/images/${thumbnailUrl ?: "blank-image.jpeg"}"
    
    // Check if the post is published
    val isPosted = ctx.markdown!!.frontMatter["posted"]?.single()?.toBoolean() ?: true

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
            console.log("🔍 Fetching markdown content for: $markdownFilePath")
            isFetching = true
            fetchError = null

            markdownContent = fetchMarkdownContent(markdownFilePath)
            console.log("✅ Markdown content fetched successfully")
        } catch (e: Exception) {
            console.error("❌ Error fetching markdown:", e)
            fetchError = "Error fetching content: ${e.message}"
            markdownContent = getTestContent()
            console.log("📝 Using fallback test content")
        } finally {
            isFetching = false
        }
    }

    LaunchedEffect(markdownContent) {
        if (markdownContent != null) {
            try {
                console.log("🔄 Starting unified markdown processing...")
                console.log("Markdown content type: ${markdownContent!!::class.simpleName}")
                console.log("Markdown content first 100 chars: ${markdownContent!!.take(100)}")
                
                isProcessing = true
                processingError = null

                val processor = unified()
                    .use(remarkParse)
                    .use(remarkGfm, js("{ singleTilde: false }"))
                    .use(remarkRehype, js("{ allowDangerousHtml: true }"))
                    .use(rehypeRaw)
                    .use(rehypeStringify)

                console.log("✨ Processing markdown with unified pipeline...")
                val result = processor.process(markdownContent!!).await()
                processedHtml = result.value
                console.log("✅ Markdown processing completed successfully")
                console.log("Processed HTML length:", processedHtml?.length.toString())
                console.log("Processed HTML first 100 chars: ${processedHtml?.take(100)}")
            } catch (e: Exception) {
                console.error("❌ Error processing markdown:", e)
                console.error("Error details:", e.message, e.cause)
                console.error("Problematic content:", markdownContent)
                processingError = "Error processing markdown: ${e.message}"
                processedHtml = "<p style='color: red;'>Error processing markdown: ${e.message}</p>"
            } finally {
                isProcessing = false
            }
        }
    }

    // If the post is not published, redirect to the home page
    LaunchedEffect(isPosted) {
        if (!isPosted) {
            console.log("🚫 Attempted to access unpublished post: ${ctx.route.path}")
            // Redirect to the home page or show an error message
            ctx.router.navigateTo("/")
        }
    }

    Layout(title = "SHEVAPRO | $title", description = description) {
        Style {
            """
            .loading-indicator {
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 2rem;
                color: #9eacd5;
                font-style: italic;
                background-color: rgba(0, 0, 0, 0.03);
                border-radius: 4px;
                margin: 1rem 0;
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
            
            .unpublished-message {
                background-color: rgba(220, 53, 69, 0.1);
                border-left: 4px solid #dc3545;
                padding: 2rem;
                margin: 2rem 0;
                color: #dc3545;
                border-radius: 4px;
                text-align: center;
                font-size: 1.2rem;
            }
            """.trimIndent()
        }
        
        Div(attrs = { classes("rounded", "min-h-full", "pt-8") }) {
            Section(attrs = {
                classes("bg-slate-100", "flex", "flex-col", "justify-around", "items-center", "p-2")
            }) {
                Img(
                    src = coverUrl,
                    attrs = {
                        classes("rounded")
                        style { width(500.px) }
                    }
                )

                H1(attrs = {
                    classes(
                        "m-4", "w-full", "font-mono", "text-center", "underline",
                        "text-2xl", "font-bold", "md:text-4xl"
                    )
                }) {
                    Text(title)
                }
            }

            Section(attrs = { style { height(100.percent) } }) {
                Article(attrs = {
                    classes("processed-content", "px-2", "min-h-full", "md:px-5", "font-sans")
                }) {
                    when {
                        isFetching -> {
                            Div(attrs = { classes("loading-indicator") }) {
                                Text("🔍 Fetching markdown content from public directory...")
                            }
                        }
                        
                        fetchError != null -> {
                            Div(attrs = { classes("fetch-error") }) {
                                Text("⚠️ $fetchError")
                                P {
                                    Text("Using fallback content instead.")
                                }
                            }
                            
                            if (isProcessing) {
                                Div(attrs = { classes("loading-indicator") }) {
                                    Text("🔄 Processing fallback content...")
                                }
                            } else if (processedHtml != null) {
                                DisposableEffect(processedHtml) {
                                    val currentElement = kotlinx.browser.document.querySelector("article.processed-content")
                                    currentElement?.innerHTML = processedHtml!!
                                    onDispose { }
                                }
                            }
                        }
                        
                        isProcessing -> {
                            Div(attrs = { classes("loading-indicator") }) {
                                Text("🔄 Processing markdown with unified pipeline...")
                            }
                        }

                        processingError != null -> {
                            Div(attrs = { classes("error-message") }) {
                                Text("❌ $processingError")
                            }
                            content()
                        }

                        processedHtml != null -> {
                            DisposableEffect(processedHtml) {
                                val currentElement = kotlinx.browser.document.querySelector("article.processed-content")
                                currentElement?.innerHTML = processedHtml!!
                                onDispose { }
                            }
                        }

                        else -> {
                            content()
                        }
                    }
                }
            }
        }
    }
}