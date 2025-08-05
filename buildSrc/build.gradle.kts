plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    // WebP support for image processing (read + write)
    implementation("io.github.darkxanter:webp-imageio:0.3.3")
}