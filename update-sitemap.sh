#!/bin/bash

# Script to update sitemap.xml timestamps
# Run this before building/deploying your Kobweb site

SITEMAP_FILE="site/src/jsMain/resources/public/sitemap.xml"
CURRENT_TIMESTAMP=$(date -u +"%Y-%m-%dT%H:%M:%SZ")

echo "Updating sitemap timestamps to: $CURRENT_TIMESTAMP"

if [ ! -f "$SITEMAP_FILE" ]; then
    echo "Sitemap not found at $SITEMAP_FILE"
    exit 1
fi

# Create a backup
cp "$SITEMAP_FILE" "$SITEMAP_FILE.backup"

# Update timestamps in sitemap
sed -i '' "s/<lastmod>.*<\/lastmod>/<lastmod>$CURRENT_TIMESTAMP<\/lastmod>/g" "$SITEMAP_FILE"

echo "✅ Sitemap timestamps updated successfully!"
echo "📍 Location: $SITEMAP_FILE"
echo "🕐 New timestamp: $CURRENT_TIMESTAMP"

# Show a preview of the updated file
echo ""
echo "Preview of updated sitemap:"
head -20 "$SITEMAP_FILE"