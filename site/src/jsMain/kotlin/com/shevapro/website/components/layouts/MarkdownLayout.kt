package com.shevapro.website.components.layouts

// Import our external declarations from the proper package
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.shevapro.website.external.Options
import com.shevapro.website.external.Toolbar
import com.shevapro.website.external.Viewer
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobwebx.markdown.markdown
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement

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
            println("⚠️ Received HTML instead of markdown - file might not exist")
            throw Exception("File not found or server returned HTML page")
        }

        // Extract content after frontmatter
        val frontMatterEnd = text.indexOf("---", 3)
        return if (frontMatterEnd > 0) {
//            println("Found frontmatter, extracting content...")
            text.substring(frontMatterEnd + 3).trim()
        } else {
            println("No frontmatter found, using full content...")
            text
        }
    } catch (e: Exception) {
        println("❌ Error fetching markdown: ${e.message}")
        throw e
    }
}

// Helper function to set link tags
private fun setLinkTag(rel: String, href: String, media: String = "all") {
    val head = kotlinx.browser.document.head!!
    val existingLink = head.querySelector("link[rel='$rel'][href='$href']")

    if (existingLink == null) {
        val linkElement = kotlinx.browser.document.createElement("link")
        linkElement.setAttribute("rel", rel)
        linkElement.setAttribute("href", href)
        linkElement.setAttribute("media", media)
        head.appendChild(linkElement)
    }
}

// Helper function to set script tags
private fun setScriptTag(src: String, defer: Boolean = false) {
    val head = kotlinx.browser.document.head!!
    val existingScript = head.querySelector("script[src='$src']")

    if (existingScript == null) {
        val scriptElement = kotlinx.browser.document.createElement("script")
        scriptElement.setAttribute("src", src)
        if (defer) {
            scriptElement.setAttribute("defer", "")
        }
        head.appendChild(scriptElement)
    }
}

// Helper function to set deferred CSS links that load after page render
private fun setDeferredCssLink(href: String) {
    val head = kotlinx.browser.document.head!!
    val existingLink = head.querySelector("link[href='$href']")

    if (existingLink == null) {
        val linkElement = kotlinx.browser.document.createElement("link")
        linkElement.setAttribute("rel", "stylesheet")
        linkElement.setAttribute("href", href)
        linkElement.setAttribute("media", "print")
        linkElement.setAttribute("onload", "this.media='all'; this.onload=null;")
        head.appendChild(linkElement)
    }
}

// Helper function to load page-specific resources
private fun loadPageResources() {
    // Add deferred script
//    setScriptTag("/website.js", defer = true)

    // Add deferred CSS for ViewerJS
    setDeferredCssLink("https://cdn.jsdelivr.net/npm/viewerjs@1.11.7/dist/viewer.min.css")

    // Add deferred CSS for KaTeX
    setDeferredCssLink("https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.css")
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



    LaunchedEffect(Unit) {
        loadPageResources()
    }

    Layout(title = "SHEVAPRO | $title", description = description) {

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
                            attr("loading", "lazy")
                            attr("decoding", "async")
                            attr("alt", "Article cover image for $title")
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
                            attr("alt", "Article cover image for $title")
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

            Section(attrs = {
                classes("flex", "flex-col")
                style {
                    minHeight(60.vh)
                }
            }) {
                Article(attrs = {
                    classes("processed-content", "px-2", "min-h-full", "md:px-5", "font-sans", "flex-grow")
                }) {
                    content()

                    LaunchedEffect(Unit){


                            val screenshotsContainer =
                                kotlinx.browser.document.getElementById("screenshots-container")  as? HTMLElement
                            if (screenshotsContainer != null) {
//                                        println("Found screenshots container with ID: ${screenshotsContainer.id}")
//                                        println("Container children count: ${screenshotsContainer.children.length}")


                                try {
                                    val toolBar =
                                        Toolbar().apply {
                                        }


                                    val options = Options().apply {
                                        backdrop = true
                                        button = true
                                        toolbar = toolBar
                                        fullscreen = true
                                    }

                                    val viewer = Viewer(screenshotsContainer, options)
//                                            console.log("ViewerJS initialized successfully!")
//                                            console.log("ViewerJS instance:", options)
                                } catch (e: Exception) {
                                    console.error("Failed to initialize ViewerJS:", e)
                                    console.error("Error details:", e.message)
                                }
                            } else {
//                                println("No screenshots container found with ID 'screenshots-container'")
                                // Debug: let's see what IDs are available
//                                        val allElementsWithIds = kotlinx.browser.document.querySelectorAll("[id]")
//                                        println("Available elements with IDs:")
//                                        for (i in 0 until allElementsWithIds.length) {
//                                            val element = allElementsWithIds[i] as HTMLElement
//                                            println("- ID: ${element.id}")
//                                        }
                            }

                    }
                }
            }
        }
    }
}