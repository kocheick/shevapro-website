package com.shevapro.website.external

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement

@JsModule("viewerjs")
@JsNonModule
external class Viewer(element: HTMLElement, options: dynamic = definedExternally) {
    fun show()
    fun hide()
    fun prev()
    fun next()
    fun zoom(ratio: Double)
    fun zoomTo(ratio: Double)
    fun rotateTo(degree: Double)
    fun scaleTo(scaleX: Double, scaleY: Double = definedExternally)
    fun play()
    fun stop()
    fun full()
    fun exit()
    fun tooltip()
    fun toggle()
    fun reset()
    fun resize()
    fun destroy()
}

data class ToolbarOptions(
    val zoomIn: Boolean = true,
    val zoomOut: Boolean = true,
    val oneToOne: Boolean = true,
    val reset: Boolean = true,
    val prev: Boolean = true,
    val play: Boolean = false,
    val next: Boolean = true,
    val rotateLeft: Boolean = false,
    val rotateRight: Boolean = false,
    val flipHorizontal: Boolean = false,
    val flipVertical: Boolean = false
)

data class ViewerOptions(
    val backdrop: dynamic = true,
    val button: Boolean = true,
    val navbar: dynamic = true,
    val title: dynamic = true,
    val toolbar: dynamic = ToolbarOptions(),
    val tooltip: Boolean = true,
    val movable: Boolean = true,
    val zoomable: Boolean = true,
    val rotatable: Boolean = false,
    val scalable: Boolean = false,
    val transition: Boolean = true,
    val fullscreen: Boolean = true,
    val keyboard: Boolean = true,
    val url: String? = null,
    val container: Element? = null,
    val filter: ((Element, Int) -> Boolean)? = null,
    val toggleOnDblclick: Boolean = true,
    val zoomRatio: Double = 0.2,
    val minZoomRatio: Double = 0.01,
    val maxZoomRatio: Double = 100.0,
    val zoomOnTouch: Boolean = true,
    val zoomOnWheel: Boolean = true,
    val slideOnTouch: Boolean = true,
    val ready: ((dynamic) -> Unit)? = null,
    val show: ((dynamic) -> Unit)? = null,
    val shown: ((dynamic) -> Unit)? = null,
    val hide: ((dynamic) -> Unit)? = null,
    val hidden: ((dynamic) -> Unit)? = null,
    val view: ((dynamic) -> Unit)? = null,
    val viewed: ((dynamic) -> Unit)? = null,
    val move: ((dynamic) -> Unit)? = null,
    val moved: ((dynamic) -> Unit)? = null,
    val rotate: ((dynamic) -> Unit)? = null,
    val rotated: ((dynamic) -> Unit)? = null,
    val scale: ((dynamic) -> Unit)? = null,
    val scaled: ((dynamic) -> Unit)? = null,
    val zoom: ((dynamic) -> Unit)? = null,
    val zoomed: ((dynamic) -> Unit)? = null
)
