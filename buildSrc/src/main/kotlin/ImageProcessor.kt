import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter
import javax.imageio.stream.FileImageOutputStream
import kotlin.math.min

object ImageProcessor {
    
    data class CropConfig(
        val maxWidth: Int = 600,     // Max width for mobile (anything above is desktop)
        val maxHeight: Int = 800,    // Max height for mobile (allow portrait images)
        val quality: Float = 0.95f,  // High quality compression
        val suffix: String = "-m"    // Mobile version suffix
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
        val scale = min(
            config.maxWidth.toDouble() / originalWidth,
            config.maxHeight.toDouble() / originalHeight
        )
        
        // Only process if image needs to be scaled down
        if (scale >= 1.0) {
            println("⏭️ Skipping ${file.name} (already smaller than mobile size)")
            return ProcessResult(processed = false, skipped = true, message = "Already optimized size")
        }
        
        val newWidth = (originalWidth * scale).toInt()
        val newHeight = (originalHeight * scale).toInt()
        
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
        
        // Save mobile version with quality control
        saveImageWithQuality(mobileImage, mobileFile, extension, config.quality)
        
        val originalSize = file.length() / 1024
        val mobileSize = mobileFile.length() / 1024
        val reduction = ((originalSize - mobileSize).toDouble() / originalSize * 100).toInt()
        
        println("✅ ${file.name} -> ${mobileFile.name} (${originalWidth}x${originalHeight} -> ${newWidth}x${newHeight}, ${reduction}% smaller)")
        
        return ProcessResult(processed = true, message = "Successfully processed")
    }
    
    private fun saveImageWithQuality(image: BufferedImage, outputFile: File, format: String, quality: Float) {
        when (format.lowercase()) {
            "jpg", "jpeg" -> {
                // Use ImageWriter for JPEG with quality control
                val writers = ImageIO.getImageWritersByFormatName("jpeg")
                if (writers.hasNext()) {
                    val writer = writers.next()
                    val writeParam = writer.defaultWriteParam
                    writeParam.compressionMode = ImageWriteParam.MODE_EXPLICIT
                    writeParam.compressionQuality = quality
                    
                    FileImageOutputStream(outputFile).use { output ->
                        writer.output = output
                        writer.write(null, javax.imageio.IIOImage(image, null, null), writeParam)
                    }
                    writer.dispose()
                } else {
                    // Fallback to basic ImageIO
                    ImageIO.write(image, "jpeg", outputFile)
                }
            }
            "png" -> {
                // PNG is lossless, but we can optimize compression
                val writers = ImageIO.getImageWritersByFormatName("png")
                if (writers.hasNext()) {
                    val writer = writers.next()
                    val writeParam = writer.defaultWriteParam
                    
                    // PNG compression is lossless, so we focus on compression level
                    if (writeParam.canWriteCompressed()) {
                        writeParam.compressionMode = ImageWriteParam.MODE_EXPLICIT
                        // Lower values = better compression, slower
                        writeParam.compressionQuality = 1.0f - quality // Invert for PNG
                    }
                    
                    FileImageOutputStream(outputFile).use { output ->
                        writer.output = output
                        writer.write(null, javax.imageio.IIOImage(image, null, null), writeParam)
                    }
                    writer.dispose()
                } else {
                    // Fallback to basic ImageIO
                    ImageIO.write(image, "png", outputFile)
                }
            }
            else -> {
                // For other formats, use basic ImageIO
                ImageIO.write(image, format, outputFile)
            }
        }
    }
}