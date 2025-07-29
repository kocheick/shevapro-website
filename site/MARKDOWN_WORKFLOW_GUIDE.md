# Markdown Article Workflow Guide

This guide explains the workflow for adding new markdown articles to the Shevapro website and keeping the system in sync.

## Understanding the System

The website uses a fully automated approach for managing markdown articles:

1. **Build-time Processing**: 
   - The build system processes markdown files from specific directories
   - It extracts metadata from the frontmatter of each markdown file
   - It generates a complete `MarkdownArticles.kt` file with all article metadata
   - When files are removed, they're automatically excluded from the next build

2. **Complete Lifecycle Management**:
   - Articles are automatically added when markdown files are created
   - Articles are automatically removed when markdown files are deleted
   - No manual code changes are required for any part of the article lifecycle

3. **Automatic Generation**: 
   - The entire `MarkdownArticles.kt` file is generated during the build process
   - No manual updates are required when adding or removing markdown files
   - The file includes all necessary functions and article descriptors

4. **Runtime Display**: 
   - The Blog and Portfolio pages use functions from the generated `MarkdownArticles.kt` file
   - These functions filter articles based on their route (`/blog/` or `/portfolio/`)

## Complete Workflow for Managing Articles

### Adding New Articles

#### Step 1: Create Markdown File

Add your file to the appropriate directory:

- Blog: `src/jsMain/resources/public/markdown/blog/your_article.md`
- Portfolio: `src/jsMain/resources/public/markdown/portfolio/your_project.md`

#### Step 2: Add Frontmatter

Include frontmatter with metadata at the top of your markdown file:

```markdown
---
title: "Your Article Title"
description: "A brief description of your article"
date: "January 1, 2025"
tags: ["tag1", "tag2", "tag3"]
thumbnailUrl: "optional-thumbnail.jpg"  # Optional, for portfolio items
---

Your article content here...
```

#### Step 3: Build the Project

You can build the project in two ways:

##### Option 1: Using Gradle directly

```bash
cd site  # If you're in the project root
./gradlew build
```

##### Option 2: Using the convenience script

```bash
cd site  # If you're in the project root
./update_articles.sh
```

The convenience script:
- Cleans previous build artifacts
- Builds the project
- Lists all markdown files in the blog and portfolio directories
- Provides helpful feedback

That's it! The build process automatically:
1. Detects your new markdown file
2. Extracts metadata from the frontmatter
3. Generates a complete `MarkdownArticles.kt` file with your new article included
4. Compiles the project with the updated file

#### Step 4: Verify

Check that your new article appears on the website:

- Blog articles: http://localhost:8080/blog
- Portfolio projects: http://localhost:8080/portfolio

### Removing Articles

Removing articles from the website is just as straightforward as adding them:

#### Step 1: Delete the Markdown File

Simply delete the markdown file from the appropriate directory:

```bash
# Remove a blog article
rm src/jsMain/resources/public/markdown/blog/article_to_remove.md

# Remove a portfolio project
rm src/jsMain/resources/public/markdown/portfolio/project_to_remove.md
```

#### Step 2: Build the Project

Run the build process just like when adding articles:

```bash
# Using Gradle directly
cd site  # If you're in the project root
./gradlew build

# Or using the convenience script
cd site  # If you're in the project root
./update_articles.sh
```

#### Step 3: Verify

Check that the article has been removed from the website:

- Blog articles: http://localhost:8080/blog
- Portfolio projects: http://localhost:8080/portfolio

The build process automatically:
1. Scans the markdown directories and only processes existing files
2. Generates MarkdownArticles.kt without the removed article
3. Compiles the project with the updated article list

No manual code changes are required - the system automatically handles the entire lifecycle of articles.

## Troubleshooting

### New Article Not Appearing

If your new article doesn't appear on the website:

1. **Check Build Output**: 
   - Look for errors in the build output
   - Verify that the markdown file was processed
   - Check for any warnings or errors related to `MarkdownArticles.kt` generation

2. **Check Generated Files**: 
   - Look at `build/generated/kobweb/markdown/MarkdownIndex.kt` (for reference)
   - Verify that your article is included in the list
   - Check the generated `src/jsMain/kotlin/com/shevapro/website/utils/MarkdownArticles.kt` file
   - Verify that your article is included in the `markdownIndex` list

3. **Check Frontmatter**: 
   - Verify that your markdown file has the correct frontmatter format
   - Make sure all required fields are present (title, description, date, tags)
   - Check for any syntax errors in the frontmatter (e.g., missing quotes, invalid YAML)

4. **Try a Clean Build**: 
   - Run `./gradlew clean build` to ensure a fresh build
   - This will regenerate all files from scratch

### Build Process Issues

If you encounter issues with the build process:

1. **Check Gradle Configuration**: 
   - Verify that the build.gradle.kts file is correctly configured
   - Make sure the markdown processing task is properly set up

2. **Check File Permissions**: 
   - Ensure that the build process has permission to write to the source directory
   - Check if the generated MarkdownArticles.kt file is read-only

3. **Check for Conflicts**: 
   - Make sure there are no conflicts with manually edited files
   - The build process should overwrite the MarkdownArticles.kt file

## Automated Workflow

The current workflow is fully automated:

1. Add your markdown file to the appropriate directory
2. Build the project
3. The build process automatically generates MarkdownArticles.kt with your new article
4. The website displays your new article

This approach ensures that the code compiles and works correctly with minimal manual intervention.

## Understanding the Code

### MarkdownArticles.kt

This file:
- Is **automatically generated** during the build process
- Contains a list of all articles (`markdownIndex`) from the markdown directories
- Includes functions to convert article descriptors to Article objects
- Provides functions to get blog and portfolio articles that filter the article list
- Is completely regenerated each time you build the project
- Requires no manual updates when adding new articles

### Build.gradle.kts

The build file contains:
- Configuration for processing markdown files
- Logic for generating both `MarkdownIndex.kt` and `MarkdownArticles.kt` files
- A template for the MarkdownArticles.kt file structure
- Code to extract metadata from markdown frontmatter
- Logic to insert article descriptors into the template
- Addition of the generated directory to the source set

### Generated Files

Two files are generated during the build process:

1. **MarkdownArticles.kt**:
   - Located in `src/jsMain/kotlin/com/shevapro/website/utils/`
   - Contains the complete implementation with article list and utility functions
   - Used directly by the Blog and Portfolio pages
   - Automatically updated when you build the project

2. **MarkdownIndex.kt** (for backward compatibility):
   - Located in `build/generated/kobweb/markdown/`
   - Contains just the article list
   - Not directly used by the application
   - Maintained for reference and backward compatibility

## Conclusion

By following this workflow, you can add new markdown articles to the website with zero manual intervention. The system automatically detects new files, extracts metadata, generates the necessary code, and displays the articles on the website.

Simply add your markdown files to the appropriate directory and build the project - everything else is handled automatically!