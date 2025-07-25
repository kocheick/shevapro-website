# Markdown Implementation for Portfolio and Blog Articles

This document describes how markdown files are read and rendered for portfolio and blog articles in the Shevapro website.

## Overview

The website uses Kobweb's markdown rendering capabilities to display content from markdown files. There are two types of articles:

1. **Portfolio Articles**: Project showcases displayed on the Portfolio page
2. **Blog Articles**: Blog posts displayed on the Blog page

Both types of articles are stored as markdown files with YAML frontmatter and are rendered using the same `MarkdownRenderer` component.

## File Structure

Markdown files are stored in the following directories:

- Portfolio articles: `/articles/projects/{slug}.md`
- Blog articles: `/articles/blog/{slug}.md`

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
isproject: true  # true for portfolio articles, false for blog articles
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
- `isproject`: Whether the article is a portfolio project (true) or a blog post (false)

## Implementation

### Data Model

Both portfolio and blog articles use the same `Article` data model:

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
    val imageUrl: String? = null,
    val coverImage: String? = null,
    val readTime: Int = 0,
    val isPortfolioArticle: Boolean = false,
    val posted: Boolean = true
)
```

### Markdown Parsing

The `MarkdownParser` utility handles fetching and parsing markdown files:

1. `parseMarkdown(text: String)`: Parses a markdown file with YAML frontmatter and returns a `ParsedMarkdown` object
2. `toArticle(slug: String, parsed: ParsedMarkdown)`: Converts a `ParsedMarkdown` object to an `Article` object
3. `fetchArticle(slug: String)`: Fetches a portfolio article markdown file from `/articles/projects/{slug}.md`
4. `fetchBlogArticle(slug: String)`: Fetches a blog article markdown file from `/articles/blog/{slug}.md`
5. `getAvailableArticles()`: Returns a list of available portfolio article slugs
6. `getAvailableBlogArticles()`: Returns a list of available blog article slugs

### Markdown Rendering

The `MarkdownRenderer` component renders markdown content using Kobweb's markdown parsing capabilities:

```kotlin
@Composable
fun MarkdownRenderer(
    content: String,
    modifier: Modifier = Modifier
) {
    val markdownTree = remember(content) {
        MarkdownTree.parse(content)
    }

    Div(attrs = modifier.toAttrs { classes("markdown-content") }) {
        // CSS styles for markdown content
        Style { /* ... */ }
        
        // Render markdown content
        markdownTree.toComposeHtml()
    }
}
```

## Page Implementation

### Portfolio Page

The Portfolio page (`/portfolio`) displays a list of portfolio articles:

1. Fetches portfolio articles using `MarkdownParser.getAvailableArticles()` and `MarkdownParser.fetchArticle(slug)`
2. Filters articles based on selected tags
3. Displays articles using the `ProjectCard` component
4. Links to individual article pages at `/portfolio/{slug}`

### Blog Page

The Blog page (`/blog`) displays a list of blog articles:

1. Fetches blog articles using `MarkdownParser.getAvailableBlogArticles()` and `MarkdownParser.fetchBlogArticle(slug)`
2. Filters articles based on selected tags
3. Displays articles using the `BlogPostCard` component
4. Links to individual article pages at `/blog/{slug}`

### Article Pages

Individual articles are displayed at:

- Portfolio articles: `/portfolio/{slug}`
- Blog articles: `/blog/{slug}`

Both use the same rendering logic:

1. Fetches the article using either `MarkdownParser.fetchArticle(slug)` or `MarkdownParser.fetchBlogArticle(slug)`
2. Displays the article title, date, and other metadata
3. Renders the markdown content using the `MarkdownRenderer` component

## Adding New Articles

To add a new article:

1. Create a new markdown file in the appropriate directory:
   - Portfolio article: `/articles/projects/{slug}.md`
   - Blog article: `/articles/blog/{slug}.md`

2. Add the YAML frontmatter with the required properties

3. Add the markdown content

4. Update the list of available articles in `MarkdownParser`:
   - Portfolio articles: Add to `getAvailableArticles()`
   - Blog articles: Add to `getAvailableBlogArticles()`

## Future Improvements

1. Implement dynamic loading of available articles instead of hardcoding them
2. Add pagination for articles on the Portfolio and Blog pages
3. Implement search functionality for articles
4. Add support for more frontmatter properties (e.g., author, read time, etc.)