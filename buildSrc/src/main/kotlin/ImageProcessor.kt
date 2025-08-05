import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.imageio.stream.FileImageOutputStream
import kotlin.math.min

object ImageProcessor {
    
    init {
        // Initialize WebP ImageIO plugin
        try {
            // Force ImageIO to scan for plugins (helps discover WebP writer classes added via dependencies)
            javax.imageio.ImageIO.scanForPlugins()
            // Check if WebP writers are available (plugin should auto-register)
            val webpWriters = ImageIO.getImageWritersByFormatName("webp")
            if (webpWriters.hasNext()) {
                println("✅ WebP ImageIO plugin initialized successfully")
            } else {
                println("⚠️ WebP ImageIO plugin not available - WebP generation disabled")
            }
        } catch (e: Exception) {
            println("⚠️ WebP ImageIO plugin initialization failed: ${e.message}")
        }
    }
    
    data class CropConfig(
        val maxWidth: Int = 600,     // Max width for mobile (anything above is desktop)
        val maxHeight: Int = 800,    // Max height for mobile (allow portrait images)
        val suffix: String = "-m",   // Mobile version suffix
        val generateWebP: Boolean = true  // Generate WebP versions too
    )
    
    data class ProcessResult(
        val processed: Boolean,
        val skipped: Boolean = false,
        val message: String = ""
    )
    
    fun processImages(
        sourceDir: File,
        config: CropConfig = CropConfig(),
        extensions: Set<String> = setOf("jpg", "jpeg", "png", "webp")
    ) {
        if (!sourceDir.exists()) {
            println("⚠️ Source directory does not exist: ${sourceDir.absolutePath}")
            return
        }
        
        println("🖼️ Processing images in: ${sourceDir.absolutePath}")
        
        val imageFiles = sourceDir.walkTopDown()
            .filter { it.isFile }
            .filter { file -> 
                extensions.any { ext -> 
                    file.name.lowercase().endsWith(".$ext") 
                }
            }
            .filter { !it.name.contains(config.suffix) } // Skip already processed files (with -m suffix)
            .filter { file -> !hasMobileVersion(file, config.suffix) } // Skip if mobile version already exists
            .toList()
        
        println("📸 Found ${imageFiles.size} images to process (${getTotalImageCount(sourceDir, extensions)} total images)")
        
        var processed = 0
        var skipped = 0
        var failed = 0
        
        imageFiles.forEach { file ->
            try {
                val result = processImage(file, config)
                if (result.processed) processed++ else skipped++

                // Generate WebP version of original image (if not already WebP)
                if (config.generateWebP && file.extension.lowercase() != "webp") {
                    try {
                        val originalImage = ImageIO.read(file)
                        if (originalImage != null) {
                            val originalWebpFile = File(file.parent, "${file.nameWithoutExtension}.webp")
                            if (!originalWebpFile.exists()) {
                                saveImage(originalImage, originalWebpFile, "webp")
                                println("🔄 Generated original WebP: ${originalWebpFile.name}")
                            }
                        }
                    } catch (e: Exception) {
                        println("⚠️ Original WebP generation failed for ${file.name}: ${e.message}")
                    }
                }
            } catch (e: Exception) {
                println("❌ Error processing ${file.name}: ${e.message}")
                failed++
            }
        }
        
        println("\n📊 Processing Summary:")
        println("   ✅ Processed: $processed")
        println("   ⏭️ Skipped: $skipped") 
        println("   ❌ Failed: $failed")
        println("   📁 Total images in directory: ${getTotalImageCount(sourceDir, extensions)}")
    }
    
    private fun hasMobileVersion(file: File, suffix: String): Boolean {
        val nameWithoutExt = file.nameWithoutExtension
        val extension = file.extension
        val mobileFile = File(file.parent, "$nameWithoutExt$suffix.$extension")
        return mobileFile.exists()
    }
    
    private fun getTotalImageCount(sourceDir: File, extensions: Set<String>): Int {
        return sourceDir.walkTopDown()
            .filter { it.isFile }
            .count { file -> 
                extensions.any { ext -> 
                    file.name.lowercase().endsWith(".$ext") 
                }
            }
    }
    
    private fun processImage(file: File, config: CropConfig): ProcessResult {
        val originalImage = ImageIO.read(file)
        if (originalImage == null) {
            println("⚠️ Could not read image: ${file.name}")
            return ProcessResult(processed = false, skipped = true, message = "Could not read image")
        }
        
        val originalWidth = originalImage.width
        val originalHeight = originalImage.height
        
        // Calculate new dimensions while maintaining aspect ratio
        // Prioritize width scaling - scale based on maxWidth first
        val scale = config.maxWidth.toDouble() / originalWidth

        val newWidth = (originalWidth * scale).toInt()
        val newHeight = (originalHeight * scale).toInt()

        // Only process if image needs to be scaled down
        if (scale >= 1.0) {
            println("⏭️ Skipping ${file.name} (already smaller than mobile width)")
            return ProcessResult(processed = false, skipped = true, message = "Already optimized size")
        }
        
        // Preserve alpha channel for PNG images
        val imageType = when (file.extension.lowercase()) {
            "png" -> BufferedImage.TYPE_INT_ARGB
            else -> BufferedImage.TYPE_INT_RGB
        }
        
        // Create mobile version with proper image type
        val mobileImage = BufferedImage(newWidth, newHeight, imageType)
        val g2d: Graphics2D = mobileImage.createGraphics()
        
        // Set highest quality rendering hints
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY)
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY)
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE)
        
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null)
        g2d.dispose()
        
        // Generate output filename
        val nameWithoutExt = file.nameWithoutExtension
        val extension = file.extension
        val mobileFile = File(file.parent, "$nameWithoutExt${config.suffix}.$extension")
        
        // Save mobile version
        saveImage(mobileImage, mobileFile, extension)
        
        // Generate WebP version if enabled and WebP support is available
        if (config.generateWebP && extension.lowercase() != "webp") {
            val webpFile = File(file.parent, "$nameWithoutExt${config.suffix}.webp")
            try {
                saveImage(mobileImage, webpFile, "webp")
                println("🔄 Also generated WebP: ${webpFile.name}")
            } catch (e: Exception) {
                println("⚠️ WebP generation failed for ${file.name}: ${e.message}")
            }
        }
        
        val originalSize = file.length() / 1024
        val mobileSize = mobileFile.length() / 1024
        val reduction = ((originalSize - mobileSize).toDouble() / originalSize * 100).toInt()
        
        println("✅ ${file.name} -> ${mobileFile.name} (${originalWidth}x${originalHeight} -> ${newWidth}x${newHeight}, ${reduction}% smaller)")
        
        return ProcessResult(processed = true, message = "Successfully processed")
    }
    
    private fun saveImage(image: BufferedImage, outputFile: File, format: String) {
        when (format.lowercase()) {
            "webp" -> {
                // WebP support
                val writers = ImageIO.getImageWritersByFormatName("webp")
                if (writers.hasNext()) {
                    val writer = writers.next()
                    try {
                        FileImageOutputStream(outputFile).use { output ->
                            writer.output = output
                            writer.write(null, javax.imageio.IIOImage(image, null, null), null)
                        }
                    } catch (t: Throwable) {
                        // Catch *everything* (including NoClassDefFoundError coming from native libs)
                        println("⚠️ Failed to write WebP for ${outputFile.name}: ${t.message}. Falling back to PNG")
                        ImageIO.write(image, "png", File(outputFile.parent, outputFile.nameWithoutExtension + ".png"))
                    } finally {
                        writer.dispose()
                    }
                } else {
                    println("⚠️ WebP writer not available, falling back to PNG")
                    ImageIO.write(image, "png", File(outputFile.parent, outputFile.nameWithoutExtension + ".png"))
                }
            }
            else -> {
                // For all other formats, use basic ImageIO (highest quality)
                ImageIO.write(image, format, outputFile)
            }
        }
    }
}