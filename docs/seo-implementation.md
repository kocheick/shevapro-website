# SEO Implementation Guide

This document explains how SEO metadata is implemented in the Shevapro website project.

## Overview

The SEO implementation follows a two-tier approach:
1. **Site-wide metadata** - Set in the `build.gradle.kts` file and as constants in the `Layout.kt` file
2. **Page-specific metadata** - Set when calling the `Layout` component in each page

This approach ensures that:
- Common metadata that doesn't change per page is consistently applied across the site
- Page-specific metadata can be easily customized for each page
- All SEO best practices are followed with minimal effort from developers

## Site-wide Metadata

Site-wide metadata is defined in two places:

### 1. In `build.gradle.kts`

```kotlin
kobweb {
    app {
        index {
            // Set site-wide description for SEO
            description.set("Shevapro's personal website showcasing portfolio, blog, and services")
        }
    }
}
```

### 2. In `Layout.kt` as constants

```kotlin
object SeoDefaults {
    const val SITE_NAME = "Shevapro"
    const val TITLE = "Shevapro"
    const val DESCRIPTION = "Shevapro's personal website showcasing portfolio, blog, and services"
    const val AUTHOR = "Shevapro"
    const val TWITTER_SITE = "@shevapro"
    const val TWITTER_CREATOR = "@shevapro"
    const val LOCALE = "en_US"
    const val ROBOTS = "index, follow"
    
    // Default keywords for the site - can be overridden per page
    const val KEYWORDS = "shevapro, development, portfolio, blog, services, kotlin, web development"
}
```

## Page-specific Metadata

Page-specific metadata is set when calling the `Layout` component in each page. The `Layout` component accepts several parameters that can be used to customize the SEO metadata for a specific page:

```kotlin
@Page
@Composable
fun ServicesPage() {
    Layout(
        title = "Services | Shevapro",
        description = "Professional services offered by Shevapro including web development, app development, and consulting",
        keywords = "services, web development, app development, consulting, kotlin, android",
        canonicalUrl = "https://www.shevapro.com/services"
    ) {
        // Page content
    }
}
```

## How It Works

The `Layout` component uses `LaunchedEffect` to set the page metadata whenever any of the SEO parameters change:

```kotlin
LaunchedEffect(title, description, imageUrl, canonicalUrl, keywords, author) {
    document.setPageMetadata(
        title = title,
        description = description,
        imageUrl = imageUrl,
        canonicalUrl = canonicalUrl,
        keywords = keywords,
        author = author,
        siteName = SeoDefaults.SITE_NAME,
        twitterSite = SeoDefaults.TWITTER_SITE,
        twitterCreator = SeoDefaults.TWITTER_CREATOR,
        locale = SeoDefaults.LOCALE,
        robots = SeoDefaults.ROBOTS
    )
}
```

The `setPageMetadata` function then sets all the necessary metadata tags in the document head:

- Basic metadata (title, description, keywords, author)
- Open Graph tags for social media sharing
- Twitter Card tags
- Canonical URL

## Usage Examples

### Basic Page

```kotlin
@Page
@Composable
fun AboutPage() {
    Layout(
        title = "About | Shevapro",
        description = "Learn more about Shevapro and what we do"
    ) {
        // Page content
    }
}
```

### Blog Post Page

```kotlin
@Page
@Composable
fun BlogPostPage(blogPost: BlogPost) {
    Layout(
        title = "${blogPost.title} | Shevapro Blog",
        description = blogPost.description,
        keywords = blogPost.tags.joinToString(", "),
        imageUrl = blogPost.imageUrl,
        canonicalUrl = "https://www.shevapro.com/blog/${blogPost.slug}"
    ) {
        // Blog post content
    }
}
```

### Portfolio Item Page

```kotlin
@Page
@Composable
fun PortfolioItemPage(portfolioItem: PortfolioItem) {
    Layout(
        title = "${portfolioItem.title} | Shevapro Portfolio",
        description = portfolioItem.description,
        keywords = portfolioItem.tags.joinToString(", "),
        imageUrl = portfolioItem.imageUrl,
        canonicalUrl = "https://www.shevapro.com/portfolio/${portfolioItem.slug}"
    ) {
        // Portfolio item content
    }
}
```

## Best Practices

1. **Always provide a unique title and description for each page**
   - The title should be concise and include the page name and site name
   - The description should be 150-160 characters and summarize the page content

2. **Use keywords relevant to the page content**
   - Don't stuff keywords; use them naturally
   - Include 3-5 relevant keywords for each page

3. **Provide canonical URLs for all pages**
   - This helps prevent duplicate content issues
   - Use the full URL including the domain name

4. **Include images for social media sharing when relevant**
   - This improves the appearance of shared links on social media
   - Use high-quality images that represent the page content

5. **Keep the site-wide defaults updated**
   - If the site name, author, or other site-wide metadata changes, update the `SeoDefaults` object