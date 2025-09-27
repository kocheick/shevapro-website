package com.shevapro.website.utils

/**
 * Data class representing a design image with metadata
 */
data class DesignImage(
    val fileName: String,
    val title: String,
    val category: String,
    val tags: List<String> = emptyList()
)

/**
 * Utility object for handling design images
 */
object ImageUtils {

    // Common image extensions
    private val IMAGE_EXTENSIONS = setOf("jpg", "jpeg", "png", "webp", "gif")

    // Available design images - this could be loaded from a JSON file or API
    private val DESIGN_IMAGES_METADATA = mapOf(
        "fit.jpg" to DesignImageMetadata(
            title = "Fitness Branding",
            category = "Branding",
            tags = listOf("fitness", "health", "branding", "logo"),
            description = "Modern fitness brand identity design"
        ),
        "g1.jpg" to DesignImageMetadata(
            title = "Gallery Exhibition",
            category = "Exhibition",
            tags = listOf("gallery", "art", "exhibition", "showcase"),
            description = "Contemporary gallery exhibition layout"
        ),
        "m.jpg" to DesignImageMetadata(
            title = "Modern Minimalist",
            category = "Minimalism",
            tags = listOf("modern", "minimalist", "clean", "simple"),
            description = "Clean and minimal design approach"
        ),
        "s2.jpg" to DesignImageMetadata(
            title = "Studio Photography",
            category = "Photography",
            tags = listOf("studio", "photography", "portrait", "professional"),
            description = "Professional studio photography setup"
        ),
        "s31.jpg" to DesignImageMetadata(
            title = "Creative Studio",
            category = "Photography",
            tags = listOf("studio", "creative", "professional", "setup"),
            description = "Creative studio environment design"
        ),
        "s5.jpg" to DesignImageMetadata(
            title = "Studio Lighting",
            category = "Photography",
            tags = listOf("studio", "lighting", "setup", "photography"),
            description = "Studio lighting setup and design"
        ),
        "sf.jpg" to DesignImageMetadata(
            title = "Studio Focus",
            category = "Photography",
            tags = listOf("studio", "focus", "photography", "professional"),
            description = "Focused studio photography composition"
        ),
        "sheick3.jpg" to DesignImageMetadata(
            title = "Artistic Portrait",
            category = "Portrait",
            tags = listOf("portrait", "artistic", "person", "photography"),
            description = "Artistic portrait photography work"
        ),
        "sp.jpg" to DesignImageMetadata(
            title = "Special Project",
            category = "Creative",
            tags = listOf("creative", "special", "project", "design"),
            description = "Special creative design project"
        )
    )

    /**
     * Internal data class for metadata
     */
    private data class DesignImageMetadata(
        val title: String,
        val category: String,
        val tags: List<String>,
        val description: String = ""
    )

    /**
     * Get all available design images with generated metadata
     */
    fun  getDesignImages(): List<DesignImage> {
        return DESIGN_IMAGES_METADATA.map { (fileName, metadata) ->
            DesignImage(
                fileName = fileName,
                title = metadata.title,
                category = metadata.category,
                tags = metadata.tags
            )
        }
    }

    /**
     * Get design images and automatically discover any new ones
     * This method attempts to discover images dynamically
     */
    fun getDesignImagesWithAutoDiscovery(): List<DesignImage> {
        // Start with known images
        val knownImages = getDesignImages().toMutableList()

        // In a real implementation, you might scan the assets folder
        // For now, we'll use the predefined list but this shows the structure
        // for future enhancement

        return knownImages
    }

    /**
     * Generate a category based on filename patterns
     * This is a fallback method for unknown files
     */
    fun generateCategoryFromFilename(filename: String): String {
        val name =
            filename.lowercase().removeSuffix(".jpg").removeSuffix(".png").removeSuffix(".jpeg").removeSuffix(".webp")

        return when {
            name.contains("fit") || name.contains("health") || name.contains("gym") -> "Health & Fitness"
            name.contains("studio") || name.startsWith("s") -> "Photography"
            name.contains("gallery") || name.startsWith("g") -> "Gallery & Exhibition"
            name.contains("portrait") || name.contains("person") || name.contains("face") -> "Portrait"
            name.contains("modern") || name.startsWith("m") -> "Modern Design"
            name.contains("brand") || name.contains("logo") -> "Branding"
            name.contains("web") || name.contains("ui") || name.contains("ux") -> "Web Design"
            name.contains("print") || name.contains("poster") -> "Print Design"
            name.contains("creative") || name.contains("art") -> "Creative Arts"
            name.contains("event") || name.contains("concert") -> "Event Design"
            else -> "Design"
        }
    }

    /**
     * Generate a readable title from filename
     */
    fun generateTitleFromFilename(filename: String): String {
        val name = filename.removeSuffix(".jpg").removeSuffix(".png").removeSuffix(".jpeg").removeSuffix(".webp")

        // Check if we have metadata for this file
        DESIGN_IMAGES_METADATA[filename]?.let { metadata ->
            return metadata.title
        }

        // Generate from filename
        return when (name.lowercase()) {
            "fit" -> "Fitness Branding Design"
            "g1" -> "Gallery Exhibition Layout"
            "m" -> "Modern Minimalist Design"
            "s2" -> "Studio Photography Setup"
            "s31" -> "Professional Studio Design"
            "s5" -> "Studio Lighting Design"
            "sf" -> "Studio Focus Photography"
            "sheick3" -> "Artistic Portrait Photography"
            "sp" -> "Special Creative Project"
            else -> {
                // Convert camelCase or snake_case to readable title
                name.replace(Regex("([a-z])([A-Z])"), "$1 $2")
                    .replace("_", " ")
                    .replace("-", " ")
                    .split(" ")
                    .joinToString(" ") { word ->
                        word.replaceFirstChar { it.uppercase() }
                    } + " Design"
            }
        }
    }

    /**
     * Generate tags based on filename and category
     */
    fun generateTagsFromFilename(filename: String, category: String): List<String> {
        val name =
            filename.lowercase().removeSuffix(".jpg").removeSuffix(".png").removeSuffix(".jpeg").removeSuffix(".webp")
        val tags = mutableListOf<String>()

        // Add category-based tags
        when (category.lowercase()) {
            "photography" -> tags.addAll(listOf("photography", "professional"))
            "branding" -> tags.addAll(listOf("branding", "identity"))
            "portrait" -> tags.addAll(listOf("portrait", "person"))
            "minimalism", "modern design" -> tags.addAll(listOf("modern", "minimalist"))
            "creative arts", "creative" -> tags.addAll(listOf("creative", "artistic"))
        }

        // Add filename-based tags
        when {
            name.contains("studio") || name.startsWith("s") -> tags.add("studio")
            name.contains("fit") -> tags.addAll(listOf("fitness", "health"))
            name.contains("gallery") || name.startsWith("g") -> tags.add("gallery")
        }

        return tags.distinct()
    }

    /**
     * Get the full image URL for a design image
     */
    fun getDesignImageUrl(fileName: String): String {
        return "/assets/design/$fileName"
    }

    /**
     * Get fallback image URL
     */
    fun getFallbackImageUrl(): String {
        return "/assets/images/blank-image.webp"
    }

    /**
     * Filter images by category
     */
    fun getDesignImagesByCategory(category: String): List<DesignImage> {
        return getDesignImages().filter { it.category.equals(category, ignoreCase = true) }
    }

    /**
     * Get all unique categories
     */
    fun getDesignCategories(): List<String> {
        return getDesignImages().map { it.category }.distinct().sorted()
    }

    /**
     * Search images by tags or title
     */
    fun searchDesignImages(query: String): List<DesignImage> {
        val lowerQuery = query.lowercase()
        return getDesignImages().filter { image ->
            image.title.lowercase().contains(lowerQuery) ||
                    image.category.lowercase().contains(lowerQuery) ||
                    image.tags.any { tag -> tag.lowercase().contains(lowerQuery) }
        }
    }

    /**
     * Check if a filename is a valid image
     */
    fun isValidImageFile(filename: String): Boolean {
        val extension = filename.substringAfterLast(".", "").lowercase()
        return extension in IMAGE_EXTENSIONS
    }
}