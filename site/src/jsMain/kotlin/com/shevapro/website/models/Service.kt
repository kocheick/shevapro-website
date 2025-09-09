// models/Service.kt
package com.shevapro.website.models

data class Service(
    val id: String,
    val title: String,
    val subtitle: String,
    val outcomes: List<String>,
    val valueProps: List<String>,
    val proofPoints: List<String> = emptyList(),
    val ctaText: String = "Contact Me",
    val ctaHref: String = "/contact"
)
