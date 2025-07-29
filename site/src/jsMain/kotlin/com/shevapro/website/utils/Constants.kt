package com.shevapro.website.utils

/**
 * Unified constants for the Shevapro website
 *
 * This object contains all site-wide constants including SEO defaults, routes,
 * and other configuration values in a single, centralized location.
 */
object Constants {

    object Site {
        const val NAME = "Shevapro"
        const val TITLE = "Shevapro"
        const val DESCRIPTION = "Shevapro's personal website showcasing portfolio, blog, and services"
        const val AUTHOR = "Shevapro"
        const val TWITTER_SITE = "@theshevapro"
        const val TWITTER_CREATOR = "@theshevapro"
        const val LOCALE = "en_US"
        const val ROBOTS = "index, follow"
        const val KEYWORDS = "shevapro, development, portfolio, blog, services, kotlin, web development"
        const val FULL_URL = "https://www.shevapro.com"
        const val STATIC_ASSETS_URL = "/assets"
    }

    object Routes {
        const val HOME = "/"
        const val BLOG = "/blog"
        const val BLOG_POST = "/blog/:title"
        const val PORTFOLIO = "/portfolio"
        const val PORTFOLIO_ARTICLE = "/portfolio/:title"
        const val SERVICES = "/services"
        const val DESIGN = "/design"
        const val MUSIC = "/music"
        const val ABOUT = "/about"
        const val CONTACT = "/contact"
        const val ERROR = "*"
    }

    object Pages {
        object Blog {
            const val TITLE = "Shevapro | Blog - Insights and practical Tips for Mobile and System developers"
            const val DESCRIPTION =
                "Welcome to 'Shevapro.com/blog', where you'll find practical insights and proven tips on Mobile app architecture and Systems engineering; from best practices to project management and the latest industry trends. Join our community of readers and take your architecture skills to the next level."
        }

        object Portfolio {
            const val TITLE =
                "Shevapro | Portfolio - Freelance Android Developer | Custom Apps and projects for Clients Across Industries."
            const val DESCRIPTION =
                "Explore Shevapro's portfolio of work as an experienced Mobile Developer. From application design, to development, to testing, to maintenance and support, Shevapro has a proven track record of delivering high-quality work to clients. Browse Shevapro's portfolio and see how their expertise and attention to detail can bring your mobile app projects to life."
            const val HEADLINE = "Projects, Concepts exploration and Playground.."
            const val SECONDARY_HEADLINE =
                "Showcasing a few projects I have worked on (from creation to finish), challenges on the way and lessons learned."
        }

        object Services {
            const val TITLE = "Shevapro | Services - Freelance Software Developer experienced in Android"
            const val DESCRIPTION =
                "As a freelance Android developer, Shevapro provides expert services to clients looking to build, maintain, or optimize their mobile apps. Shevapro has a proven track record of delivering high-quality work that exceeds client expectations. From custom app development to mobile strategy consulting, Shevapro offers a range of services to help clients achieve their mobile app goals. Contact SHEVAPRO today to discuss how they can help take your mobile app to the next level."
        }

        object Design {
            const val TITLE = "Shevapro | Design - Experienced Photo Editor"
            const val DESCRIPTION =
                "Admire a blend of various visual projects I created in no particular order for artists, events and businesses."
        }

        object Music {
            const val TITLE =
                "Shevapro|Music - Talented Music Producer | Expert in Crafting High-Quality Sound and Elevating Music Creation to New Heights"
            const val DESCRIPTION =
                "With a passion for music and a natural talent for crafting exceptional sounds, Shevapro has honed their skills over years of hard work and dedication. With a focus on bringing out the best in artists and helping them achieve their creative vision, Shevapro offers a range of services to help clients take their music to the next level."
        }

        object Contact {
            const val TITLE = "Contact - ShevaPro | Get in Touch"
            const val DESCRIPTION =
                "Have a question or want to work together? Fill out the form below or use the contact information to get in touch."
        }

        object About {
            const val TITLE = "About - Shevapro | Learn More About Me"
            const val DESCRIPTION =
                "Learn more about Shevapro, a passionate software engineer and mobile app developer with expertise in Android development, design, and music production."
        }
    }

    object Client {
        const val SCROLL_POSITION_KEY = "mainContentScrollPosition"
        const val AVG_WORDS_PER_MINUTE = 185
        const val DISPLAY_ARTICLES_COUNT = 3
        const val BLANK_IMAGE_URL = "${Site.STATIC_ASSETS_URL}/images/blank-image.jpeg"
        const val SONGS_PATH = "${Site.STATIC_ASSETS_URL}/songs/audios/"
    }


}