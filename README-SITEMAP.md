# Sitemap Management for Shevapro Website

A sitemap management system for the Shevapro website that improves SEO by providing search engines with structured information about the site's pages. The system includes automated scripts for updating timestamps and integrates with the build process to ensure the sitemap is always current.

This document explains how to manage the sitemap.xml file for the Kobweb-based shevapro website in detail.

## 📍 Sitemap Location

The sitemap is located at: `site/src/jsMain/resources/public/sitemap.xml`

This file will be served at: `https://www.shevapro.com/sitemap.xml`

## 🔄 Automatic Timestamp Updates

### Method 1: Use the Update Script (Recommended)

Run the update script before deploying:

```bash
./update-sitemap.sh
```

This will:

- ✅ Update all `<lastmod>` timestamps to current UTC time
- ✅ Create a backup of the original file
- ✅ Show a preview of the updated sitemap

### Method 2: Use the Complete Build Script

For a full build and deploy process:

```bash
./build-and-deploy.sh
```

This will:

- ✅ Update sitemap timestamps
- ✅ Build the Kobweb project with `kobweb export`
- ✅ Prepare static files for deployment

## 📋 Manual Process

If you prefer to update manually:

1. Open `site/src/jsMain/resources/public/sitemap.xml`
2. Replace all timestamps in `<lastmod>` tags with current UTC time
3. Use format: `YYYY-MM-DDTHH:MM:SSZ` (e.g., `2025-07-23T00:59:28Z`)

## 🔍 Current Sitemap Pages

The sitemap includes these pages (based on your original ServerUtils.kt):

- `/` (home) - monthly updates
- `/portfolio` - monthly updates
- `/blog` - weekly updates
- `/services` - monthly updates
- `/music` - weekly updates
- `/contact` - monthly updates
- `/about` - monthly updates

## 🚀 Deployment Workflow

### For Regular Updates:

```bash
# 1. Update sitemap
./update-sitemap.sh

# 2. Build project
cd site && kobweb export --layout static

# 3. Deploy the contents of site/.kobweb/site/
```

### For Complete Build:

```bash
# One command does it all
./build-and-deploy.sh
```

## 🔧 Adding New Pages

When you add new pages to your site:

1. Add the new URL to `sitemap.xml`
2. Set appropriate `changefreq` (weekly for dynamic content, monthly for static)
3. Run `./update-sitemap.sh` to update timestamps

Example entry:

```xml
<url>
    <loc>https://www.shevapro.com/new-page</loc>
    <changefreq>monthly</changefreq>
    <lastmod>2025-07-23T00:59:28Z</lastmod>
</url>
```

## 📄 Files Created

- `sitemap.xml` - The main sitemap file
- `update-sitemap.sh` - Script to update timestamps
- `build-and-deploy.sh` - Complete build and deploy script
- `sitemap.xml.backup` - Automatic backup (created when updating)

## ✅ Verification

After deployment, verify your sitemap is accessible:

- Visit: `https://www.shevapro.com/sitemap.xml`
- Test with Google Search Console
- Submit to search engines if needed