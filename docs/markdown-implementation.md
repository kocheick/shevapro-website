# Markdown Implementation for Portfolio and Blog Articles

This document describes how markdown files are read and rendered for portfolio and blog articles in the Shevapro website.

## Overview

The website uses Kobweb's markdown rendering capabilities to display content from markdown files. There are different
types of articles organized by category:

1. **Portfolio Articles**: Project showcases displayed on the Portfolio page
2. **Blog Articles**: Blog posts displayed on the Blog page
3. **Design Articles**: Design projects displayed on the Design page

All types of articles are stored as markdown files with YAML frontmatter and are rendered using the same
`EnhancedMarkdownLayout` component.

## File Structure

Markdown files are stored in the following directories:

- Portfolio articles: `src/jsMain/resources/public/markdown/portfolio/{slug}.md`
- Blog articles: `src/jsMain/resources/public/markdown/blog/{slug}.md`
- Design articles: `src/jsMain/resources/public/markdown/design/{slug}.md`

## Markdown Format

Each markdown file has a YAML frontmatter section at the beginning, followed by the markdown content:

```markdown
---
title: Article Title
date: 'Month Day, Year'
description: A brief description of the article
thumbnailUrl: image-filename.jpg
tags: ['tag1', 'tag2', 'tag3']
posted: true
layout: .components.layouts.EnhancedMarkdownLayout
---

## Markdown Content

Your article content goes here...
```

### Frontmatter Properties

- `title`: The title of the article
- `date`: The date the article was published
- `description`: A brief description of the article
- `thumbnailUrl`: The filename of the thumbnail image (stored in `/assets/images/`)
- `tags`: A list of tags associated with the article
- `posted`: Whether the article should be displayed (true/false)
- `layout`: The layout component to use for rendering

## Implementation

### Data Model

All articles use the same `Article` data model:

```kotlin
data class Article(
    val id: String,
    val slug: String = id,
    val title: String,
    val content: String,
    val description: String,
    val author: String,
    val dateAdded: String,
    val tags: List<String> = emptyList(),
    val imageUrl: String = "/assets/images/blank-image.jpeg",
    val coverImage: String? = null,
    val readTime: Int = 0,
    val posted: Boolean = true
)
```

### Article Management

The `MarkdownArticles` utility provides functions to get articles by category:

1. `getArticles(category: String)`: Returns articles from any category (e.g., "blog", "portfolio", "design")
2. `getBlogArticles()`: Convenience function for blog articles
3. `getPortfolioArticles()`: Convenience function for portfolio articles
4. `getDesignArticles()`: Convenience function for design articles

### Markdown Rendering

The `EnhancedMarkdownLayout` component renders markdown content using a unified processing pipeline with support for
GitHub Flavored Markdown (GFM).

## Page Implementation

### Category Pages

Each category has its own page that displays a list of articles:

1. **Portfolio Page** (`/portfolio`): Displays portfolio articles
2. **Blog Page** (`/blog`): Displays blog articles
3. **Design Page** (`/design`): Displays design articles

Each page:

1. Fetches articles using the appropriate function (e.g., `getPortfolioArticles()`)
2. Filters articles based on selected tags
3. Displays articles using card components
4. Links to individual article pages at `/{category}/{slug}`

### Article Pages

Individual articles are displayed at `/{category}/{slug}` using the `EnhancedMarkdownLayout`.

## Adding New Articles

To add a new article:

1. Create a new markdown file in the appropriate directory:
    - Portfolio article: `src/jsMain/resources/public/markdown/portfolio/{slug}.md`
    - Blog article: `src/jsMain/resources/public/markdown/blog/{slug}.md`
    - Design article: `src/jsMain/resources/public/markdown/design/{slug}.md`

2. Add the YAML frontmatter with the required properties

3. Add the markdown content

4. Build the project - the build system will automatically detect the new file and update the article index

## Future Improvements

1. Add pagination for articles on category pages
2. Implement search functionality for articles
3. Add support for more frontmatter properties (e.g., read time calculation)
4. Implement automatic tag extraction and management