// components/ServiceCard.kt
package com.shevapro.website.components.widgets

import androidx.compose.runtime.Composable
import com.shevapro.website.models.Service
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*

@Composable
fun ServiceCard(service: Service, modifier: Modifier = Modifier) {
    Div(attrs = modifier.toAttrs {
        classes(
            "w-full","p-6","bg-white","bg-opacity-90",
            "rounded-2xl","shadow-lg","hover:shadow-xl","transition-all"
        )
        }

    ) {
        H3({ classes("text-2xl","md:text-3xl","font-bold","text-gray-900","mb-1") }) { Text(service.title) }
        P ({ classes("text-base","md:text-lg","text-gray-700","mb-4") }) { Text(service.subtitle) }

        H4({ classes("text-lg","font-semibold","text-gray-900","mb-2") }) { Text("Expected Outcomes") }
        Ul({ classes("list-disc","pl-6","space-y-1","mb-4") }) {
            service.outcomes.forEach { outcome ->
                Li({ classes("text-base","text-gray-700") }) { Text(outcome) }
            }
        }

        H4({ classes("text-lg","font-semibold","text-gray-900","mb-2") }) { Text("How We Deliver") }
        Ul({ classes("list-disc","pl-6","space-y-1","mb-4") }) {
            service.valueProps.forEach { prop ->
                Li({ classes("text-base","text-gray-700") }) { Text(prop) }
            }
        }

        if (service.proofPoints.isNotEmpty()) {
            H4({ classes("text-lg","font-semibold","text-gray-900","mb-2") }) { Text("Proof Points") }
            Ul({ classes("list-disc","pl-6","space-y-1","mb-6") }) {
                service.proofPoints.forEach { proof ->
                    Li({ classes("text-sm","text-gray-600") }) { Text(proof) }
                }
            }
        }

        A(href = service.ctaHref, attrs = {
            classes("inline-block","bg-purple-600","text-white","text-sm","font-medium",
                    "px-6","py-3","rounded-lg","no-underline","transition-all","hover:bg-purple-500")
        }) { Text(service.ctaText) }
    }
}
