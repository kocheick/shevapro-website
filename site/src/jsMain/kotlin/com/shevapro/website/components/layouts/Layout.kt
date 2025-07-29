package com.shevapro.website.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.shevapro.website.components.sections.Footer
import com.shevapro.website.components.sections.Header
import com.shevapro.website.styles.ThemeMode
import com.shevapro.website.styles.ThemeProvider
import com.shevapro.website.utils.Constants
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.overflowY
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Main
import org.w3c.dom.Document
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import org.w3c.dom.set

/**
 * Constants for scroll position persistence
 */
private const val SCROLL_POSITION_KEY = "mainContentScrollPosition"

/**
 * Main layout component for the website.
 * 
 * This component sets up the basic structure of the website and handles SEO metadata.
 * It uses the unified Constants for site-wide metadata when specific values aren't provided.
 * 
 * @param title Page title, defaults to site-wide title
 * @param description Page description, defaults to site-wide description
 * @param imageUrl Optional image URL for social media sharing
 * @param canonicalUrl Optional canonical URL for the page
 * @param keywords Optional keywords for the page, defaults to site-wide keywords
 * @param author Page author, defaults to site-wide author
 * @param themeMode Theme mode (light, dark, system)
 * @param modifier Modifier for the layout
 * @param content Content to be displayed in the layout
 */
@Composable
fun Layout(
    title: String = Constants.Site.TITLE,
    description: String = Constants.Site.DESCRIPTION,
    imageUrl: String? = null,
    canonicalUrl: String? = null,
    keywords: String? = Constants.Site.KEYWORDS,
    author: String = Constants.Site.AUTHOR,
    themeMode: ThemeMode = ThemeMode.System,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Set page metadata whenever any of the SEO parameters change
    LaunchedEffect(title, description, imageUrl, canonicalUrl, keywords, author) {
        // Auto-generate canonical URL if not provided
        val finalCanonicalUrl = canonicalUrl ?: "${Constants.Site.FULL_URL}${window.location.pathname}"

        document.setPageMetadata(
            title = title,
            description = description,
            imageUrl = imageUrl,
            canonicalUrl = finalCanonicalUrl,
            keywords = keywords,
            author = author,
            siteName = Constants.Site.NAME,
            twitterSite = Constants.Site.TWITTER_SITE,
            twitterCreator = Constants.Site.TWITTER_CREATOR,
            locale = Constants.Site.LOCALE,
            robots = Constants.Site.ROBOTS
        )
    }

    val scrollPosition = remember { window.sessionStorage.getItem(Constants.Client.SCROLL_POSITION_KEY) }

    // Restore scroll position on page load
    LaunchedEffect(Unit) {
        val mainContent = document.getElementById("main-content") as? HTMLElement
        if (mainContent != null && scrollPosition != null) {
            try {
                val savedPosition = scrollPosition.toDouble()
                mainContent.scrollTop = savedPosition
            } catch (e: NumberFormatException) {
                // Invalid scroll position, ignore
            }
        }
    }

    // Save scroll position on scroll
    LaunchedEffect(Unit) {
        val mainContent = document.getElementById("main-content") as? HTMLElement
        if (mainContent != null) {
            val scrollHandler: (dynamic) -> Unit = {
                window.sessionStorage.setItem(Constants.Client.SCROLL_POSITION_KEY, mainContent.scrollTop.toString())
            }
            mainContent.addEventListener("scroll", scrollHandler)

            // Cleanup function - remove event listener when component unmounts
            kotlinx.coroutines.coroutineScope {
                try {
                    kotlinx.coroutines.awaitCancellation()
                } finally {
                    mainContent.removeEventListener("scroll", scrollHandler)
                }
            }
        }
    }

    ThemeProvider(mode = themeMode) {
        // Header - will be sticky due to CSS in Header component
        Header()

        // Main content - scrollable area that fills remaining space
        Main(
            attrs = {
                id("main-content")
                classes(
                    "p-2",
                    "border-red-700",
                    "items-center",
                    "justify-center",
                    "flex-grow",
                    "overflow-y-scroll"
                )
                style {
//                        property("flex", "1")
//                        property("overflow-y", "auto")
//                        property("overflow-x", "hidden")
//                        overflowY(Overflow.Scroll)
//                        width(100.percent)
//                        display(DisplayStyle.Flex)
//                        flexGrow(1)
//                        justifyContent(JustifyContent.Center)
//                        alignItems(AlignItems.FlexStart)
//                        padding(16.px)
                }
            }
        ) {
            content()

        }

        // Footer - stays at bottom of screen
        Footer()

    }
}

/**
 * Sets comprehensive SEO metadata for the page including:
 * - Basic metadata (title, description, keywords, author)
 * - Open Graph tags for social media sharing
 * - Twitter Card tags
 * - Canonical URL
 */
private fun Document.setPageMetadata(
    title: String,
    description: String,
    imageUrl: String? = null,
    canonicalUrl: String? = null,
    keywords: String? = null,
    author: String? = null,
    siteName: String = Constants.Site.NAME,
    twitterSite: String? = Constants.Site.TWITTER_SITE,
    twitterCreator: String? = Constants.Site.TWITTER_CREATOR,
    locale: String = Constants.Site.LOCALE,
    robots: String = Constants.Site.ROBOTS
) {
    // Set document title
    this.title = title
    
    val head = this.head!!
    
    // Helper function to set or create meta tags
    fun setMetaTag(name: String? = null, property: String? = null, content: String?) {
        if (content == null) return
        
        val selector = when {
            name != null -> "meta[name='$name']"
            property != null -> "meta[property='$property']"
            else -> return
        }
        
        (head.querySelector(selector)
            ?: this.createElement("meta").apply {
                if (name != null) setAttribute("name", name)
                if (property != null) setAttribute("property", property)
                head.appendChild(this)
            }
        ).setAttribute("content", content)
    }
    
    // Helper function to set link tags
    fun setLinkTag(rel: String, href: String) {
        (head.querySelector("link[rel='$rel']")
            ?: this.createElement("link").apply {
                setAttribute("rel", rel)
                head.appendChild(this)
            }
        ).setAttribute("href", href)
    }
    
    // Basic SEO meta tags
    setMetaTag(name = "description", content = description)
    setMetaTag(name = "keywords", content = keywords)
    setMetaTag(name = "author", content = author)
    setMetaTag(name = "robots", content = robots)
    setMetaTag(name = "language", content = locale.substring(0, 2))
    
    // Open Graph tags for social media
    setMetaTag(property = "og:title", content = title)
    setMetaTag(property = "og:description", content = description)
    setMetaTag(property = "og:type", content = "website")
    setMetaTag(property = "og:site_name", content = siteName)
    setMetaTag(property = "og:locale", content = locale)
    if (imageUrl != null) {
        setMetaTag(property = "og:image", content = imageUrl)
    }
    if (canonicalUrl != null) {
        setMetaTag(property = "og:url", content = canonicalUrl)
    }
    
    // Twitter Card tags
    setMetaTag(name = "twitter:card", content = "summary_large_image")
    setMetaTag(name = "twitter:title", content = title)
    setMetaTag(name = "twitter:description", content = description)
    if (twitterSite != null) {
        setMetaTag(name = "twitter:site", content = twitterSite)
    }
    if (twitterCreator != null) {
        setMetaTag(name = "twitter:creator", content = twitterCreator)
    }
    if (imageUrl != null) {
        setMetaTag(name = "twitter:image", content = imageUrl)
    }
    
    // Canonical URL
    if (canonicalUrl != null) {
        setLinkTag(rel = "canonical", href = canonicalUrl)
    }
}

