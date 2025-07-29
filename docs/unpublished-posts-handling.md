# Unpublished Posts Handling

This document explains how unpublished markdown posts (with `posted: false`) are handled in the Shevapro website project.

## Overview

The website uses Kobweb's markdown processing system to generate pages from markdown files. Each markdown file can have a `posted` flag in its front matter that indicates whether the post should be published or not.

Previously, when a markdown file had `posted: false`:
- The post would not appear in listings (blog or portfolio pages)
- The post would not be included in the sitemap
- However, the page was still generated and accessible via direct URL

The current implementation ensures that unpublished posts are completely inaccessible, even via direct URL.

## Implementation

The solution implements a runtime check in the markdown layout components that redirects users to the home page if they try to access an unpublished post.

### Changes Made

1. **EnhancedMarkdownLayout.kt**:
   - Added a check for the `posted` flag in the front matter
   - Added a `LaunchedEffect` block that redirects to the home page if the post is not published
   - Added logging to the console when an attempt to access an unpublished post is made

2. **MarkdownLayout.kt**:
   - Added a check for the `posted` flag in the front matter
   - Added a `LaunchedEffect` block that redirects to the home page if the post is not published
   - Added logging to the console when an attempt to access an unpublished post is made

### Code Example

```kotlin
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
```

## How It Works

1. When a user tries to access a page generated from a markdown file, the layout component checks the `posted` flag in the front matter.
2. If the `posted` flag is `false`, the user is redirected to the home page.
3. If the `posted` flag is `true` or not specified (defaults to `true`), the page is displayed normally.

## Benefits

- Unpublished posts are completely inaccessible, even via direct URL
- No changes to the build process were required
- The solution works with both layout components used in the project
- Logging provides visibility into attempts to access unpublished posts

## Future Improvements

While the current solution effectively prevents access to unpublished posts, there are potential improvements that could be made:

1. **Build-time filtering**: Modify the Kobweb build process to exclude unpublished markdown files from page generation entirely. This would be more efficient but would require changes to the Kobweb plugin configuration.

2. **Custom error page**: Instead of redirecting to the home page, show a custom error page explaining that the post is not published.

3. **Admin preview**: Add a mechanism for administrators to preview unpublished posts without making them publicly accessible.

## Usage

To mark a post as unpublished, add `posted: false` to the front matter of the markdown file:

```markdown
---
title: My Unpublished Post
date: 2023-09-08
description: This post is not published yet
thumbnailUrl: image.png
tags: ['tag1', 'tag2']
posted: false
---

Post content here...
```

The post will not appear in listings, will not be included in the sitemap, and will not be accessible via direct URL.