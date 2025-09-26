package com.shevapro.website.data

import com.shevapro.website.models.Service

val ServicesCatalog = listOf(
    Service(
        id = "mobile",
        title = "Mobile App Development",
        subtitle = "Create engaging mobile experiences that users love and businesses benefit from.",
        outcomes = listOf(
            "Better user engagement and retention",
            "Smoother user experience with reliable performance",
            "Faster development process to get your app to market"
        ),
        valueProps = listOf(
            "Modern development practices for efficient delivery",
            "User-focused design that drives engagement",
            "Seamless integration with your existing systems"
        ),
        proofPoints = listOf(
            "Kotlin & Compose Multiplatform expertise",
            "Clean Architecture & automated deployment"
        )
    ),
    Service(
        id = "web",
        title = "Web Development",
        subtitle = "Build fast, responsive websites that work great on any device.",
        outcomes = listOf(
            "Better user experience leads to higher conversions",
            "Fast loading times keep visitors engaged",
            "Scalable architecture that grows with your business"
        ),
        valueProps = listOf(
            "Clean, conversion-focused design",
            "Mobile-first responsive approach",
            "Performance optimized for real-world usage"
        ),
        proofPoints = listOf(
            "Modern web technologies (Kobweb, Compose for Web)",
            "SEO-friendly, accessible, and responsive by default"
        )
    ),
    Service(
        id = "consulting",
        title = "Software Consulting",
        subtitle = "Get expert guidance to make smart technology decisions for your project.",
        outcomes = listOf(
            "Faster delivery with fewer bugs and issues",
            "Better architectural decisions that save time later",
            "Optimized infrastructure costs and performance"
        ),
        valueProps = listOf(
            "Technology choices aligned with your business goals",
            "Identify and solve bottlenecks before they become problems",
            "Proven patterns that reduce maintenance overhead"
        ),
        proofPoints = listOf(
            "Code reviews and architecture assessments",
            "Team workflow optimization and developer experience improvements"
        )
    ),
    Service(
        id = "custom",
        title = "Custom Software Solutions",
        subtitle = "Build tailored solutions that solve your specific business challenges.",
        outcomes = listOf(
            "Automate repetitive tasks to save valuable time",
            "Reduce errors with built-in validations and monitoring",
            "Scalable systems that adapt as your business grows"
        ),
        valueProps = listOf(
            "Streamlined workflows that free up your team",
            "Integration with tools you already use",
            "Reliable, secure platforms built for long-term success"
        ),
        proofPoints = listOf(
            "Event-driven architectures and API integrations",
            "Comprehensive testing and monitoring practices"
        )
    )
)
