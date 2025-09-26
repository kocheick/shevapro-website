package com.shevapro.website.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HeroSection
import com.shevapro.website.external.Toolbar
import com.shevapro.website.external.Options
import com.shevapro.website.external.Viewer
import com.shevapro.website.styles.SiteTheme
import com.shevapro.website.utils.Constants
import com.shevapro.website.utils.ImageUtils
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement

@Page
@Composable
fun DesignPage() {
    Layout(title = Constants.Pages.Design.TITLE, description = Constants.Pages.Design.DESCRIPTION) {
        // Main container with gradient background
        Div(
            attrs = {
                classes(
                    "min-h-screen",
                    "bg-gradient-to-bl",
                    "from-red-200",
                    "via-purple-300",
                    "to-blue-500",
                    "bg-opacity-40"
                )
            }
        ) {
            // Hero section using the reusable component
            HeroSection(
                title = "Design",
                subtitle = "Elegant, modern and creative",
                description = "Admire a blend of various visual projects I created in no particular order for artists, events and businesses.",
                // Override default gradient for Design page
                gradientFrom = "from-purple-300",
                gradientTo = "to-pink-200"
            )

            // Design gallery section
            DesignGallerySection()
        }
    }
}

@Composable
private fun DesignGallerySection() {
    // Get design images from utility - this is now automated
    val designImages = remember { ImageUtils.getDesignImages() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topBottom = SiteTheme.Spacing.xxl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(90.percent)
                .maxWidth(1200.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Container for ViewerJS
            Div(attrs = {
                attr("id", "design-gallery-container")
            }) {
                // Gallery grid - masonry-style layout
                Div(
                    attrs = {
                        classes(
                            "grid",
                            "grid-cols-1",
                            "sm:grid-cols-2",
                            "lg:grid-cols-3",
                            "xl:grid-cols-4",
                            "gap-6",
                            "auto-rows-max"
                        )
                    }
                ) {
                    // Display design images
                    designImages.forEach { designImage ->
                        DesignGalleryItem(
                            imageUrl = ImageUtils.getDesignImageUrl(designImage.fileName),
                            title = designImage.title,
                            category = designImage.category,
                            tags = designImage.tags
                        )
                    }
                }


                // Initialize the image viewer with a single instance and proper disposal
                val viewerHolder = remember { mutableStateOf<Viewer?>(null) }
                DisposableEffect(Unit) {
                    val designGalleryContainer = document.getElementById("design-gallery-container") as? HTMLElement
                    if (designGalleryContainer != null && viewerHolder.value == null) {
                        val images = designGalleryContainer.querySelectorAll("img")
                        if (images.length > 0) {
                            try {
                                val toolBar = Toolbar().apply { rotateRight = true }
                                val options = Options().apply {
                                    backdrop = true
                                    button = true
                                    rotatable = true
                                    toolbar = toolBar
                                    fullscreen = true
                                }
                                val viewer = Viewer(designGalleryContainer, options)
                                viewerHolder.value = viewer
                                console.log("ViewerJS created successfully:", viewer)
                            } catch (e: Exception) {
                                console.log("ViewerJS initialization failed: ${e.message}")
                                console.log("Error details:", e)
                            }
                        } else {
                            console.log("No images found in container yet")
                        }
                    }

                    onDispose {
                        viewerHolder.value?.destroy()
                        viewerHolder.value = null
                    }
                }

            }
        }
    }
}

@Composable
private fun DesignGalleryItem(
    imageUrl: String,
    title: String,
    category: String,
    tags: List<String> = emptyList()
) {
    val imgSrc = if (imageUrl.isBlank()) ImageUtils.getFallbackImageUrl() else imageUrl

    Div(
        attrs = {
            classes(
                "relative",
                "rounded-2xl",
                "overflow-hidden",
                "shadow-lg",
                "hover:shadow-2xl",
                "transition-all",
                "duration-300",
                "cursor-pointer",
                "group",
                "bg-white"
            )
        }
    ) {
        // Image (ViewerJS will hook into this within the container)
        Img(
            src = imgSrc,
            attrs = Modifier
                .width(100.percent)
                .height(300.px)
                .objectFit(ObjectFit.Cover)
                .toAttrs {
                    attr("alt", title)
                    attr("data-title", title) // For ViewerJS title display
                    classes("transition-transform", "duration-300", "group-hover:scale-105")
                }
        )

        // Overlay content - positioned absolutely over the image
        Div(
            attrs = {
                classes(
                    "absolute",
                    "bottom-0",
                    "left-0",
                    "right-0",
                    "bg-gradient-to-t",
                    "from-black/80",
                    "via-black/50",
                    "to-transparent",
                    "p-4",
                    "text-white",
                    "transform",
                    "translate-y-2",
                    "group-hover:translate-y-0",
                    "transition-all",
                    "duration-300"
                )
            }
        ) {
            // Category badge
            Span(
                attrs = {
                    classes(
                        "inline-block",
                        "px-2",
                        "py-1",
                        "bg-white/20",
                        "backdrop-blur-sm",
                        "rounded-full",
                        "text-xs",
                        "font-medium",
                        "text-white/90",
                        "mb-2"
                    )
                }
            ) {
                Text(category)
            }

            // Title
            H3(
                attrs = {
                    classes(
                        "text-white",
                        "text-lg",
                        "font-bold",
                        "mb-1",
                        "line-clamp-2"
                    )
                }
            ) {
                Text(title)
            }

            // Tags (if available) - only show on hover for cleaner look
            if (tags.isNotEmpty()) {
                Div(
                    attrs = {
                        classes(
                            "flex",
                            "flex-wrap",
                            "gap-1",
                            "mt-2",
                            "opacity-0",
                            "group-hover:opacity-100",
                            "transition-opacity",
                            "duration-300"
                        )
                    }
                ) {
                    tags.take(3).forEach { tag ->
                        Span(
                            attrs = {
                                classes(
                                    "px-2",
                                    "py-1",
                                    "bg-white/20",
                                    "backdrop-blur-sm",
                                    "text-white/80",
                                    "text-xs",
                                    "rounded-full"
                                )
                            }
                        ) {
                            Text("#$tag")
                        }
                    }
                }
            }
        }
    }
}