package com.shevapro.website.external

import kotlinext.js.Object
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement

@Suppress("unused")
private val _viewerCss = kotlinext.js.require("viewerjs/dist/viewer.css")

@JsModule("viewerjs")
@JsNonModule
external class Viewer(element: HTMLElement, val options: dynamic = definedExternally) {
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


external interface Toolbar {
    var zoomIn: Boolean
    var zoomOut: Boolean
    var oneToOne: Boolean
    var reset: Boolean
    var prev: Boolean
    var play: Boolean
    var next: Boolean
    var rotateLeft: Boolean
    var rotateRight: Boolean
    var flipHorizontal: Boolean
    var flipVertical: Boolean
}

fun Toolbar(): Toolbar = js("{}").unsafeCast<Toolbar>().apply {
    zoomIn = true
    zoomOut = true
    oneToOne = true
    reset = true
    prev = true
    play = false
    next = true
    rotateLeft = false
    rotateRight = false
    flipHorizontal = false
    flipVertical = false
}
external interface Options {
    var backdrop: Boolean
    var button: Boolean
    var navbar: dynamic
    var title: dynamic
    var toolbar: Toolbar
    var tooltip: Boolean
    var movable: Boolean
    var zoomable: Boolean
    var rotatable: Boolean
    var scalable: Boolean
    var transition: Boolean
    var fullscreen: Boolean
    var keyboard: Boolean
    var url: String?
    var container: Element?
    var filter: ((Element, Int) -> Boolean)?
    var toggleOnDblclick: Boolean
    var zoomRatio: Double
    var minZoomRatio: Double
    var maxZoomRatio: Double
    var zoomOnTouch: Boolean
    var zoomOnWheel: Boolean
    var slideOnTouch: Boolean
    var ready: ((dynamic) -> Unit)?
    var show: ((dynamic) -> Unit)?
    var shown: ((dynamic) -> Unit)?
    var hide: ((dynamic) -> Unit)?
    var hidden: ((dynamic) -> Unit)?
    var view: ((dynamic) -> Unit)?
    var viewed: ((dynamic) -> Unit)?
    var move: ((dynamic) -> Unit)?
    var moved: ((dynamic) -> Unit)?
    var rotate: ((dynamic) -> Unit)?
    var rotated: ((dynamic) -> Unit)?
    var scale: ((dynamic) -> Unit)?
    var scaled: ((dynamic) -> Unit)?
    var zoom: ((dynamic) -> Unit)?
    var zoomed: ((dynamic) -> Unit)?
}

// For default initialization
fun Options(): Options = js("({})").unsafeCast<Options>().apply {
    backdrop = false
    button = true
    navbar = true 
    title = true
    tooltip = true
    movable = false
    zoomable = true
    rotatable = false
    scalable = false
    transition = true
    fullscreen = false
    keyboard = true
    toggleOnDblclick = true
    zoomRatio = 0.2
    minZoomRatio = 0.01
    maxZoomRatio = 100.0
    zoomOnTouch = true
    zoomOnWheel = true
    slideOnTouch = true
}