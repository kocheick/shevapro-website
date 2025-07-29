---
title: Unified Markdown Processing Test
date: 'January 28, 2025'
description: Testing unified markdown processing with remark-gfm and rehype-raw plugins
thumbnailUrl: blank-image.jpeg
tags: ['unified', 'remark', 'rehype', 'gfm', 'test']
posted: true
isproject: true
layout: .components.layouts.EnhancedMarkdownLayout
---

# Unified Markdown Processing Test

This content demonstrates **GitHub Flavored Markdown** features processed with unified, remark-gfm, and rehype-raw!

## GitHub Flavored Markdown Features

### Strikethrough

~~This text should be struck through~~

### Tables

| Feature | Status | Notes |
|---------|--------|-------|
| Strikethrough | ✅ | Should work with remark-gfm |  
| Tables | ✅ | Like this one |
| Task Lists | ✅ | See below |
| HTML in Markdown | ✅ | With rehype-raw |

### Task Lists

- [x] Completed task
- [ ] Pending task
- [x] Another completed task
- [ ] Another pending task

### HTML Elements in Markdown

This should work with <u>underlined text</u> and <mark>highlighted text</mark>.

<div style="background-color: #f0f0f0; padding: 10px; border-left: 4px solid #007acc;">
<strong>Note:</strong> This is HTML content inside markdown that should be preserved with rehype-raw.
</div>

### Code Blocks

```javascript
// This is a JavaScript code block
function hello() {
    console.log("Hello from unified markdown processing!");
}
```

### Links and Autolinks

Regular link: [Kobweb Documentation](https://kobweb.varabyte.com)

Autolink: https://kobweb.varabyte.com

### Emphasis and Strong

*Italic text* and **bold text** and ***bold italic text***

---

✅ If you can see all the above features working correctly, then the unified markdown processing with remark-gfm and
rehype-raw is functioning properly!