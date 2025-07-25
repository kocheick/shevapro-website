#!/bin/bash

# Build and Deploy Script for Kobweb Shevapro Website
# This script updates sitemap timestamps and builds the project

set -e  # Exit on any error

echo "🚀 Starting build and deploy process..."

# Step 1: Update sitemap timestamps
echo ""
echo "📅 Step 1: Updating sitemap timestamps..."
./update-sitemap.sh

# Step 2: Navigate to site directory and run Kobweb build
echo ""
echo "🔨 Step 2: Building Kobweb project..."
cd site

# Export the site (production build)
echo "Running kobweb export..."
kobweb export --layout static

echo ""
echo "✅ Build completed successfully!"
echo "📁 Static files are ready in site/.kobweb/site/"
echo ""
echo "📋 Next steps for deployment:"
echo "   1. Upload contents of site/.kobweb/site/ to your web server"
echo "   2. Ensure sitemap.xml is accessible at https://www.shevapro.com/sitemap.xml"
echo "   3. Submit updated sitemap to search engines if needed"