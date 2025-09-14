package com.shevapro.website.pages

import androidx.compose.runtime.Composable
import com.shevapro.website.components.layouts.Layout
import com.shevapro.website.components.sections.ArticleListSection
import com.shevapro.website.components.ui.HomeHero
import com.shevapro.website.components.widgets.BlogPostCard
import com.shevapro.website.components.widgets.ProjectCard
import com.shevapro.website.utils.Constants
import com.shevapro.website.utils.getArticles
import com.varabyte.kobweb.compose.css.ColorInterpolationMethod
import com.varabyte.kobweb.compose.css.backgroundImage
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
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

            // Latest Blog Articles Section (new)
            run {
                val latestBlog = getArticles("blog").take(3)
                ArticleListSection(
                    title = "Latest Blog Articles",
                    articles = latestBlog,
                    emptyText = "No blog articles found.",
                    viewAllPath = "/blog"
                ) { article ->
                    BlogPostCard(
                        article = article,
                        onClick = { router.navigateTo("/blog/${article.slug}") }
                    )
                }
            }

            // Latest Projects Section (refactored)
            run {
                val portfolioProjects = getArticles("portfolio").take(3)
                ArticleListSection(
                    title = "Latest Projects",
                    articles = portfolioProjects,
                    emptyText = "Projects coming soon...",
                    viewAllPath = "/portfolio"
                ) { project ->
                    ProjectCard(
                        project = project,
                        onClick = { router.navigateTo("/portfolio/${project.slug}") },
                        onTagClick = { tag -> router.navigateTo("/portfolio?tag=$tag") }
                    )
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
