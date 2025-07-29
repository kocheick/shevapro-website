@file:JsModule("unified")
@file:JsNonModule

package com.shevapro.website.external

import kotlin.js.Promise

@JsName("unified")
external fun unified(): Processor

external interface Processor {
    fun use(plugin: dynamic, options: dynamic = definedExternally): Processor
    fun process(content: String): Promise<VFile>
}

external interface VFile {
    val value: String
}