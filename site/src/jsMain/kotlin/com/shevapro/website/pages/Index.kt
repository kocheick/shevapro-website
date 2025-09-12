package com.shevapro.website.pages

import androidx.compose.runtime.Composable
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.ui.HomeHero
import com.shevapro.website.components.widgets.ProjectCard
import com.shevapro.website.utils.Constants
import com.shevapro.website.utils.getArticles
import com.varabyte.kobweb.compose.css.ColorInterpolationMethod
import com.varabyte.kobweb.compose.css.backgroundImage
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.attributes.href
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun HomePage() {
    val router = rememberPageContext().router

    Layout(title = "Cheick's brand | Software Engineer | Artist", Constants.Site.DESCRIPTION) {
        Div(attrs = {
            classes(
                "min-h-full", "pt-16",
//                "bg-opacity-40",
//                "bg-gradient-to-tr",
//                "from-purple-600",
//                "to-blue-300",
                "rounded-2xl"
            )
            style {
//                backgroundImage(SiteTheme.Gradients.background)
                backgroundImage(linearGradient(interpolation = ColorInterpolationMethod.Oklab,
                    dir = LinearGradient.Direction.ToTopRight
                ){
                    add( Color("#e3e1efb0"))
//                    add( Color("#0b03c859"))
                    add( Color("#f0edff"))
                    add(Color("rgba(171, 134, 209, 1)"))
//                    add(Color(rgba(255,10,60,0.8).toString()),stop = 50.percent)
                })

            }
        }) {
            // Hero Section
            Section(attrs = { classes("my-2") }) {
                HomeHero( )
            }
            Br {}

            // Removed Under Construction teaser for now (keeping the function below for future use)

            // Latest Projects Section
            Section(attrs = { classes("mb-8") }) {
                H2(attrs = {
                    classes(
                        "text-3xl",
                        "md:text-4xl",
                        "text-gray-900",
                        "p-2",
                        "bg-white",
                        "bg-opacity-70",
                        "font-semibold",
                        "rounded-lg",
                        "shadow",
                        "m-3",
                        "mt-2"
                    )
                }) { Text("Latest Projects") }

                val portfolioProjects = getArticles("portfolio").take(3) // Show latest 3 projects

                if (portfolioProjects.isNotEmpty()) {
                    Div(attrs = {
                        classes(
                            "flex",
                            "flex-wrap",
                            "justify-center",
                            "gap-4",
                            "mt-6",
                            "bg-white",
                            "bg-opacity-60",
                            "backdrop-blur-sm",
                            "rounded-xl",
                            "p-4"
                        )
                    }) {
                        portfolioProjects.forEach { project ->
                            ProjectCard(
                                project = project,
                                onClick = {
                                    router.navigateTo("/portfolio/${project.slug}")
                                },
                                onTagClick = { tag ->
                                    router.navigateTo("/portfolio?tag=$tag")
                                }
                            )
                        }
                    }
                } else {
                    Div(attrs = {
                        classes(
                            "bg-white",
                            "rounded-lg",
                            "shadow",
                            "p-6",
                            "text-gray-700",
                            "text-center",
                            "mt-6"
                        )
                    }) {
                        Text("Projects coming soon...")
                    }
                }

                // View All Projects Button
                Div(attrs = {
                    classes("text-center", "mt-8")
                }) {
                    A(
                        attrs = {
                            href("/portfolio")
                            classes(
                                "inline-block",
                                "px-6",
                                "py-3",
                                "bg-purple-600",
                                "hover:bg-purple-700",
                                "text-white",
                                "font-semibold",
                                "rounded-lg",
                                "shadow-md",
                                "hover:shadow-lg",
                                "transition-all",
                                "duration-300",
                                "transform",
                                "hover:scale-105"
                            )
                        }
                    ) {
                        Text("View All Projects →")
                    }
                }
            }

            // Music Section (stub)
//            Section(attrs = { }) {
//                H2(attrs = {
//                    classes(
//                        "text-3xl",
//                        "font-bold",
//                        "mb-4",
//                        "text-pink-800"
//                    )
//                }) { Text("Music COMING SOON..") }
//                // TODO: Future music section
//            }
        }
    }
}

@Composable
private fun UnderConstructionSection() {
    Div({
        classes(
//            "m-4",
//                    "md:m-8",
//                    "lg:m-12",
            "mt-12",
            "w-full",
            "max-w-md",
            "md:max-w-lg",
            "lg:max-w-2xl",
            "mx-auto",
            "mb-8",
            "p-6",
            "md:p-10",
            "rounded-xl",
            "bg-gradient-to-br",
            "from-yellow-200",
            "via-pink-200",
            "to-purple-200",
            "shadow-lg")
    }){
        H1(
            attrs = {
                classes(
                    "text-2xl",
                    "md:text-3xl",
                    "font-bold",
                    "mb-4",
                    "text-gray-900",
                    "text-center"
                )
            }
        ) {
            Text("🚧 Under Construction")
        }

        P(
            attrs = {
                classes(
                    "text-base",
                    "md:text-lg",
                    "text-gray-900",
                    "text-center",
                    "leading-relaxed"
                )
            }
        ) {
            Text(
                "This portfolio is currently being migrated from React to Kobweb. " +
                        "The original hero content has been preserved above. Check back soon for the complete experience!"
            )
        }
    }
}
