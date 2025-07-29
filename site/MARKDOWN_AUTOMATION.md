# Markdown Articles Automation

## Overview

Both the Blog and Portfolio pages now automatically load articles from markdown files stored in
`src/jsMain/resources/public/markdown/`. The system supports:

- **Blog articles**: Files in `markdown/blog/` directory
- **Portfolio articles**: Files in `markdown/portfolio/` directory

## How It Works

1. **Markdown Processing**: The Kobweb build system automatically:
    - Processes all `.md` files in the markdown directories
    - Extracts frontmatter metadata (title, description, date, tags, thumbnail)
    - Generates a `MarkdownIndex.kt` file with article descriptors
    - Creates routes based on the folder structure (e.g., `markdown/blog/article.md` → `/blog/article`)

2. **Article Loading**: The pages use utility functions from `utils/MarkdownArticles.kt`:
    - `getBlogArticles()` - Returns articles from `/blog/` routes
    - `getPortfolioArticles()` - Returns articles from `/portfolio/` routes

## Current Implementation

Due to build-time import issues with the generated `MarkdownIndex.kt`, we currently use a manual approach in
`utils/MarkdownArticles.kt` that mirrors the generated data.

## Adding New Articles

1. **Create the markdown file** in the appropriate directory:
    - Blog: `src/jsMain/resources/public/markdown/blog/your-article.md`
    - Portfolio: `src/jsMain/resources/public/markdown/portfolio/your-project.md`

2. **Add frontmatter** at the top of your markdown file:
   ```yaml
   ---
   title: Your Article Title
   date: January 15, 2024
   description: A brief description of your article
   author: Shevapro
   tags: [tag1, tag2, tag3]
   thumbnailUrl: your-image.png  # Optional, for portfolio items
   posted: true
   layout: .components.layouts.MarkdownLayout
   ---
   ```

3. **Update the utility file**: Currently, you need to manually add the article descriptor to `markdownArticles` list in
   `utils/MarkdownArticles.kt`. Copy the format from existing entries.

4. **Build the project**: Run `./gradlew build` to process the new markdown file.

## Future Improvements

- [ ] Fix the generated `MarkdownIndex.kt` import issues to eliminate manual maintenance
- [ ] Create a script to automatically sync `utils/MarkdownArticles.kt` with the generated index
- [ ] Add validation to ensure manual entries match the generated ones

## Current Articles

### Blog Articles

- welcome-to-my-blog
- kotlin-multiplatform-guide

### Portfolio Articles

- file-sorter-app
- example
- markdown-example
- unified-test
- enhanced-test

## File Structure

```
src/jsMain/resources/public/markdown/
├── blog/
│   ├── welcome_to_my_blog.md
│   └── kotlin_multiplatform_guide.md
└── portfolio/
    ├── example.md
    ├── markdown_example.md
    ├── unified_test.md
    ├── file_sorter_app.md
    └── enhanced_test.md
```