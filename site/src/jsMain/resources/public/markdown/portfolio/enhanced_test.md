---
title: Enhanced Markdown Test
date: 'July 28, 2025'
description: Testing enhanced markdown processing with unified, remark-gfm, and rehype-raw
thumbnailUrl: blank-image.jpeg
tags: [ 'test']
layout: .components.layouts.EnhancedMarkdownLayout
---

# Enhanced Markdown Processing Test

This content will be replaced by the unified processing pipeline with full GFM support!

## GitHub Flavored Markdown Features

### Strikethrough

~~This text should be struck through~~

### Tables

| Feature | Status | Notes |
|---------|--------|-------|
| Strikethrough | ✅ | Should work with remark-gfm |  
| Tables | ✅ | Like this one |
| Task Lists | ✅ | See below |
| HTML in Markdown | ✅ | With enhanced styling |

### Task Lists

- [x] Completed task
- [ ] Pending task
- [x] Another completed task
- [ ] Another pending task

### HTML Elements in Markdown

This should work with <u>underlined text</u> and <mark>highlighted text</mark>.

<div style="background-color: #f0f0f0; padding: 10px; border-left: 4px solid #007acc;">
<strong>Note:</strong> This is HTML content inside markdown that should be styled properly.
</div>

### Code Blocks

```javascript
// This is a JavaScript code block
function hello() {
    console.log("Hello from enhanced markdown!");
}
```

### Links and Autolinks

Regular link: [Kobweb Documentation](https://kobweb.varabyte.com)

Autolink: https://kobweb.varabyte.com

### Emphasis and Strong

*Italic text* and **bold text** and ***bold italic text***

---

If you can see all the above features working correctly, then Kobweb's built-in GFM support is working!