#!/bin/bash

# Script to update sitemap.xml with posted markdown articles
# Now uses the Kobweb markdown processing which automatically updates the sitemap

echo "🚀 Processing markdown files and updating sitemap..."

cd site
../gradlew kobwebMarkdownProcess

if [ $? -eq 0 ]; then
    echo "✅ Sitemap update completed successfully!"
    echo "📍 Location: site/src/jsMain/resources/public/sitemap.xml"
    
    # Show a preview of the updated file
    echo ""
    echo "Preview of updated sitemap:"
    head -20 "src/jsMain/resources/public/sitemap.xml"
else
    echo "❌ Sitemap update failed!"
    exit 1
fi