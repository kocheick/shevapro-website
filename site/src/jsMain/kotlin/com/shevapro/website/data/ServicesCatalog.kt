package com.shevapro.website.data

import com.shevapro.website.models.Service

val ServicesCatalog = listOf(
    Service(
        id = "mobile",
        title = "Mobile App Development",
        subtitle = "Delight users and drive retention with high-performing apps.",
        outcomes = listOf(
            "Increase engagement by 8–15%",
            "Reduce churn through smoother UX and reliability",
            "Accelerate time-to-market by up to 40%"
        ),
        valueProps = listOf(
            "Ship faster with modern development practices",
            "Unlock new revenue via optimized onboarding and in-app flows",
            "Integrate seamlessly with business systems"
        ),
        proofPoints = listOf(
            "Kotlin & Compose Multiplatform",
            "Clean Architecture & CI/CD pipelines"
        )
    ),
    Service(
        id = "web",
        title = "Web Development",
        subtitle = "Turn browsers into buyers with fast, scalable web experiences.",
        outcomes = listOf(
            "Improve conversion by 5–12%",
            "Cut page load times by 30–50%",
            "Scale to handle launch spikes without downtime"
        ),
        valueProps = listOf(
            "Frictionless funnels that boost sales",
            "Responsive design that reaches every device",
            "Scalable infrastructure that lowers long-term cost"
        ),
        proofPoints = listOf(
            "Kobweb / Compose for Web, PWA support",
            "SEO, accessibility, and responsive design best practices"
        )
    ),
    Service(
        id = "consulting",
        title = "Software Consulting",
        subtitle = "Make smarter technology bets that reduce risk and cost.",
        outcomes = listOf(
            "Accelerate delivery speed while lowering defects",
            "Reduce costly rework with aligned architecture decisions",
            "Optimize infrastructure spend for sustainable growth"
        ),
        valueProps = listOf(
            "Align tech choices with business objectives",
            "Spot bottlenecks that slow delivery and fix them",
            "Lower maintenance costs through proven patterns"
        ),
        proofPoints = listOf(
            "Architecture reviews and code quality audits",
            "Team process optimization and DX improvements"
        )
    ),
    Service(
        id = "custom",
        title = "Custom Software Solutions",
        subtitle = "Tailored systems that pay for themselves.",
        outcomes = listOf(
            "Automate manual processes to save hours every week",
            "Lower error rates with validations and monitoring",
            "Design for scale so systems grow with your business"
        ),
        valueProps = listOf(
            "Automate workflows to free up team time",
            "Integrate with your existing tools for visibility",
            "Deliver secure, scalable platforms built for the long haul"
        ),
        proofPoints = listOf(
            "Event-driven integrations",
            "Robust testing, SLIs/SLOs, security-minded defaults"
        )
    )
)
