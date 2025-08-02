# Portfolio Migration - Shevapro Website

This document explains the portfolio system migration from the React webapp to the Kobweb website.

## Migration Status

### Completed Features

1. **Article System**
    - Markdown file parsing with YAML frontmatter
    - Article model with all original fields
    - Article fetching from public directory

2. **Portfolio Page**
    - Real article loading from markdown files
    - Tag filtering system
    - Responsive grid layout
    - Loading states

3. **Individual Article Pages**
    - Dynamic routing with `/portfolio/{slug}`
    - Markdown content rendering
    - Cover image display
    - Error handling for missing articles

4. **Content Migration**
    - File Sorter article migrated
    - Portfolio images copied
    - All markdown examples available

## File Structure

```
site/src/jsMain/resources/public/
├── markdown/
│   ├── portfolio/
│   │   ├── file_sorter_app.md      # Main File Sorter article
│   │   ├── example.md              # Sample article
│   │   └── markdown_example.md     # Markdown formatting example
│   ├── blog/                       # Blog articles
│   └── design/                     # Design projects
└── assets/
    └── images/
        ├── portfolio/              # Portfolio screenshots
        │   ├── fs_home.png
        │   ├── fs_add.png
        │   ├── fs_loading.png
        │   └── fs_demo-file-move.png
        └── file-sorter-logo.png    # App logo
```

## How It Works

### 1. Markdown File Format

Each article uses YAML frontmatter:

```yaml
---
title: File Sorter
date: 'September 8, 2023'
description: Your ultimate solution for keeping your digital life neat and tidy!
thumbnailUrl: file-sorter-logo.png
tags: ['android','app', 'hobby','productivity']
posted: true
layout: .components.layouts.EnhancedMarkdownLayout
---

## Markdown Content Here
```

### 2. Article Loading Process

1. **Build system** automatically scans markdown directories and generates article index
2. **getPortfolioArticles()** returns articles from the portfolio directory
3. **Articles** are filtered by `posted: true` and displayed in cards

### 3. Navigation Flow

```
/portfolio → Portfolio page with article cards
    ↓ (Click on article)
/portfolio/{slug} → Individual article page with full content
```

## Components

### Portfolio Page (`/portfolio`)

- Loads all project articles
- Provides tag filtering
- Shows article cards in responsive grid
- Handles loading states

### Article Page (`/portfolio/{slug}`)

- Extracts slug from URL
- Fetches individual article
- Renders markdown content with unified processor
- Shows cover image and metadata

### ProjectCard Component

- Displays article preview
- Shows cover image, title, description
- Clickable tags for filtering
- Links to full article

### EnhancedMarkdownLayout Component

- Converts markdown to HTML using unified pipeline
- Handles GitHub Flavored Markdown (GFM)
- Includes syntax highlighting and enhanced formatting
- Supports custom screenshot containers

## Available Articles

Currently migrated:

1. **File Sorter App** (`file-sorter-app`)
    - Android productivity app
    - Complete with screenshots
    - Tags: android, app, hobby, productivity

2. **Example Article** (`example`)
    - Sample article structure
    - Testing purposes

3. **Markdown Example** (`markdown-example`)
    - Demonstrates markdown formatting
    - Reference for content creation

## Usage

### Viewing Portfolio

Visit `/portfolio` to see all projects with filtering capabilities.

### Reading Articles

Click any project card to read the full article at `/portfolio/{slug}`.

### Adding New Articles

1. Create new `.md` file in `site/src/jsMain/resources/public/markdown/portfolio/`
2. Add YAML frontmatter with required fields
3. Build the project - the build system will automatically detect and include the new article
4. Add any images to `site/src/jsMain/resources/public/assets/images/`

Example:

```markdown
---
title: My New Project
date: '2025-01-01'
description: A cool new project I built
thumbnailUrl: my-project-logo.png
tags: ['kotlin','web','kobweb']
posted: true
layout: .components.layouts.EnhancedMarkdownLayout
---

# My Project

This is my awesome project...
```

## Next Steps

1. **Search Functionality**: Add text search across articles
2. **Categories**: Implement article categories beyond tags
3. **Related Articles**: Show related projects based on tags
4. **Comments System**: Add comments to individual articles
5. **SEO Optimization**: Improve metadata and structured data

## Responsive Design

The portfolio is fully responsive:

- **Mobile**: Stacked cards, vertical layout
- **Tablet**: 2-column grid
- **Desktop**: 3+ column grid with sidebar

## Performance

- Articles load asynchronously
- Images are lazy-loaded
- Markdown processing uses unified pipeline for better performance
- Lightweight components with minimal dependencies

## Technical Details

- **Framework**: Kobweb with Compose HTML
- **Routing**: Kobweb's `@Page` annotation system
- **State Management**: Compose's `remember` and `mutableStateOf`
- **Styling**: Tailwind CSS classes with responsive design
- **Content**: Markdown files with YAML frontmatter
- **Images**: Static assets in public directory
- **Build System**: Automated markdown processing with sitemap generation

The system successfully replicates the original React portfolio functionality while leveraging Kobweb's modern
architecture and automated build system!