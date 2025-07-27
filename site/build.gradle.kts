import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kizzy.tailwind.utils.setupTailwindProject

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.kotlin.serialization)
    id("io.github.dead8309.tailwind-kt").version("0.0.4")

}

group = "com.shevapro.website"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {

////            description.set("Made with ❤ by Shevapro")
        }
    }

}
//
tasks.register<Copy>("copy404") {
    // Adjust the path if your export location is different
    from("$rootDir/site/.kobweb/site/not-found.html")
    into("$rootDir/site/.kobweb/site")
    rename("not-found.html", "404.html")
}

tasks.named("kobwebExport").configure {
    finalizedBy("copy404")
}



kotlin {
    configAsKobwebApplication("website")
    setupTailwindProject()

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlinx.serialization.json)

            implementation(project(":worker"))
        }
    }
}
