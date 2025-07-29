---
title: New Test Article
date: January 28, 2025
description: This is a test article to verify that the automation system correctly picks up new markdown files and includes them in the website.
author: Shevapro
tags: [test, automation, blog, new]
posted: true
layout: .components.layouts.EnhancedMarkdownLayout
---

# New Test Article

This is a test article created to verify that the markdown automation system is working correctly.

## Purpose

When this article is added to the markdown directory, it should:

1. Be automatically detected during the build process
2. Have its metadata extracted and added to the generated MarkdownIndex
3. Appear on the blog page after running the sync script
4. Be filterable by its tags

## Testing the System

To test the automation:

1. Build the project: `./gradlew build`
2. Run the sync script: `python3 sync_markdown_articles.py`
3. Build again: `./gradlew build`
4. Check the blog page

If you can see this article on the blog page with proper filtering, the automation is working! 🎉

## Conclusion

This demonstrates that new markdown files are automatically picked up by the build system without needing manual code
changes.