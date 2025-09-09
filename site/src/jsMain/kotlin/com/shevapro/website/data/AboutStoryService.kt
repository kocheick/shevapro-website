package com.shevapro.website.data

import com.shevapro.website.components.sections.StoryNode

/**
 * Service for managing About page story data.
 * Centralizes story content for easier maintenance and potential future data source integration.
 */
object AboutStoryService {

    /**
     * Returns the list of story nodes for the About page timeline.
     */
    fun getStoryNodes(): List<StoryNode> = listOf(
        StoryNode(
            id = "start",
            title = "Curiosity sparked the journey",
            period = "Early days",
            summary = "A deep curiosity for tech pushed me to self‑learn and build the foundation: one concept at a time until the bigger picture clicked.",
            details = listOf(
                "My journey into coding began with a deep‑rooted passion for technology, and I've always dreamt of creating my own innovative solutions. While self‑learning was no easy feat, my unwavering curiosity fueled my progress, leading me from one discovery to another until I grasped the bigger picture of coding fundamentals."
            ),
            tags = listOf("self‑taught", "foundations"),
            emoji = "✨"
        ),
        StoryNode(
            id = "first-site",
            title = "First real project: an artist's website",
            period = "The first launch",
            summary = "Built a site to showcase a local artist's music. Seeing real users interact with my work hooked me for good.",
            details = listOf(
                "My foray into app development started when I built a website for a local artist to showcase his music. The experience ignited my excitement to delve deeper into product development and create for real people."
            ),
            tags = listOf("web", "music"),
            emoji = "🎵"
        ),
        StoryNode(
            id = "android-kotlin",
            title = "Falling for Android & Kotlin",
            period = "Why mobile",
            summary = "Dove into Android for its reach and openness. Kotlin made it a joy — expressive, safe, and productive.",
            details = listOf(
                "I chose Android as my platform of choice due to its accessibility and widespread popularity. And let me tell you, learning Kotlin was an absolute delight — expressive, safe, and productive."
            ),
            tags = listOf("android", "kotlin"),
            emoji = "🤖"
        ),
        StoryNode(
            id = "craft",
            title = "Crafting end‑to‑end experiences",
            period = "Today",
            summary = "I love shaping ideas into polished apps — collaborating with diverse people, learning fast, and delivering value.",
            details = listOf(
                "What truly drives me is the sheer joy I find in crafting apps from conception to completion. The process of transforming an idea into a fully functional app is exhilarating, and I thrive on collaborating with individuals from diverse backgrounds. Each project is a valuable learning experience that expands my skill set and broadens my horizons."
            ),
            tags = listOf("product", "ux", "delivery"),
            emoji = "🧩"
        ),
        StoryNode(
            id = "values",
            title = "Build what delights and helps",
            summary = "My goal: software that actually helps people and leaves a positive mark. Technology should enrich our lives.",
            details = listOf(
                "My ultimate goal as a Software Engineer is to contribute to products that not only delight users but also provide tangible value. I strive to create seamless and engaging experiences that leave a lasting impact. I firmly believe that technology should enrich our lives, and I'm passionate about bringing that vision to reality.",
                "When I'm not immersed in development, you might find me lacing up my running shoes, engrossed in a captivating book, or exploring nature through invigorating hikes. I believe in maintaining a well‑rounded lifestyle that fuels creativity and keeps me inspired.",
                "I invite you to reach out to me about any exciting challenges or opportunities you may have. Let's connect and discuss how I can contribute my skills and expertise to help you achieve your goals."
            ),
            tags = listOf("impact", "quality"),
            emoji = "🌱"
        )
    )
}