# Styling Guidelines

This document outlines the styling approach and guidelines for the Kobweb portfolio website migration using Silk UI.

## Styling Architecture

### Theme Structure

```kotlin
// Theme definition
object SiteTheme {
    object Colors {
        val primary = Color.rgb(0x3B82F6)
        val secondary = Color.rgb(0x10B981)
        val accent = Color.rgb(0xF59E0B)
        val background = Color.rgb(0xFAFAFA)
        val surface = Color.rgb(0xFFFFFF)
        val text = Color.rgb(0x1F2937)
        val textSecondary = Color.rgb(0x6B7280)
    }
    
    object Typography {
        val h1 = 3.rem
        val h2 = 2.5.rem
        val h3 = 2.rem
        val h4 = 1.5.rem
        val body = 1.rem
        val small = 0.875.rem
    }
    
    object Spacing {
        val xs = 4.px
        val sm = 8.px
        val md = 16.px
        val lg = 24.px
        val xl = 32.px
        val xxl = 48.px
    }
}
```

## Component Styling Patterns

### Base Component Styles

```kotlin
val ButtonStyle = ComponentStyle("custom-button") {
    base {
        Modifier
            .padding(12.px, 24.px)
            .borderRadius(6.px)
            .border(1.px, LineStyle.Solid, Colors.Transparent)
            .fontSize(14.px)
            .fontWeight(FontWeight.Medium)
            .cursor(Cursor.Pointer)
            .transition(CSSTransition("all", 200.ms))
    }
    
    hover {
        Modifier.transform { scale(1.02) }
    }
}

val ButtonVariant = ButtonStyle.addVariant("primary") {
    Modifier
        .backgroundColor(SiteTheme.Colors.primary)
        .color(Colors.White)
}

val ButtonOutlineVariant = ButtonStyle.addVariant("outline") {
    Modifier
        .backgroundColor(Colors.Transparent)
        .border(1.px, LineStyle.Solid, SiteTheme.Colors.primary)
        .color(SiteTheme.Colors.primary)
}
```

### Card Components

```kotlin
val CardStyle = ComponentStyle("card") {
    base {
        Modifier
            .backgroundColor(SiteTheme.Colors.surface)
            .borderRadius(12.px)
            .boxShadow(0.px, 4.px, 6.px, (-1).px, rgba(0, 0, 0, 0.1))
            .padding(SiteTheme.Spacing.lg)
            .transition(CSSTransition("transform", 300.ms))
    }
    
    hover {
        Modifier
            .transform { translateY((-4).px) }
            .boxShadow(0.px, 10.px, 25.px, (-5).px, rgba(0, 0, 0, 0.1))
    }
}
```

### Layout Styles

```kotlin
val ContainerStyle = ComponentStyle("container") {
    base {
        Modifier
            .maxWidth(1200.px)
            .margin(leftRight = auto)
            .padding(leftRight = SiteTheme.Spacing.md)
    }
    
    breakpoint(Breakpoint.MD) {
        Modifier.padding(leftRight = SiteTheme.Spacing.lg)
    }
    
    breakpoint(Breakpoint.LG) {
        Modifier.padding(leftRight = SiteTheme.Spacing.xl)
    }
}

val SectionStyle = ComponentStyle("section") {
    base {
        Modifier
            .padding(topBottom = SiteTheme.Spacing.xxl)
    }
    
    breakpoint(Breakpoint.MD) {
        Modifier.padding(topBottom = 80.px)
    }
}
```

## Responsive Design

### Breakpoint Strategy

```kotlin
// Mobile-first approach
val ResponsiveGridStyle = ComponentStyle("responsive-grid") {
    base {
        Modifier
            .display(DisplayStyle.Grid)
            .gridTemplateColumns("1fr")
            .gap(SiteTheme.Spacing.lg)
    }
    
    breakpoint(Breakpoint.SM) {
        Modifier.gridTemplateColumns("repeat(2, 1fr)")
    }
    
    breakpoint(Breakpoint.MD) {
        Modifier.gridTemplateColumns("repeat(3, 1fr)")
    }
    
    breakpoint(Breakpoint.LG) {
        Modifier
            .gridTemplateColumns("repeat(4, 1fr)")
            .gap(SiteTheme.Spacing.xl)
    }
}
```

### Typography Scaling

```kotlin
val HeadingStyle = ComponentStyle("heading") {
    base {
        Modifier
            .fontSize(SiteTheme.Typography.h3)
            .fontWeight(FontWeight.Bold)
            .lineHeight(1.2)
            .color(SiteTheme.Colors.text)
    }
    
    breakpoint(Breakpoint.MD) {
        Modifier.fontSize(SiteTheme.Typography.h2)
    }
    
    breakpoint(Breakpoint.LG) {
        Modifier.fontSize(SiteTheme.Typography.h1)
    }
}
```

## Animation and Transitions

### Smooth Transitions

```kotlin
val SmoothTransitionStyle = ComponentStyle("smooth-transition") {
    base {
        Modifier.transition(
            CSSTransition("all", 300.ms, AnimationTimingFunction.EaseInOut)
        )
    }
}

val FadeInStyle = ComponentStyle("fade-in") {
    base {
        Modifier
            .opacity(0)
            .transform { translateY(20.px) }
            .transition(
                CSSTransition("opacity", 600.ms),
                CSSTransition("transform", 600.ms)
            )
    }
}

// JavaScript for intersection observer animation
@Composable
fun AnimatedSection(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        // Implement intersection observer logic
    }
    
    Div(
        attrs = {
            classes(if (isVisible) "fade-in-visible" else "fade-in")
        },
        modifier = modifier
    ) {
        content()
    }
}
```

## Color System

### Color Palette

```kotlin
object ColorPalette {
    // Primary colors
    val blue50 = Color.rgb(0xEFF6FF)
    val blue500 = Color.rgb(0x3B82F6)
    val blue900 = Color.rgb(0x1E3A8A)
    
    // Secondary colors
    val emerald50 = Color.rgb(0xECFDF5)
    val emerald500 = Color.rgb(0x10B981)
    val emerald900 = Color.rgb(0x064E3B)
    
    // Neutral colors
    val gray50 = Color.rgb(0xF9FAFB)
    val gray100 = Color.rgb(0xF3F4F6)
    val gray500 = Color.rgb(0x6B7280)
    val gray900 = Color.rgb(0x111827)
}

val SurfaceVariant = ComponentStyle("surface") {
    base {
        Modifier.backgroundColor(ColorPalette.gray50)
    }
}

val SurfaceElevatedVariant = ComponentStyle("surface-elevated") {
    base {
        Modifier
            .backgroundColor(Colors.White)
            .boxShadow(0.px, 1.px, 3.px, rgba(0, 0, 0, 0.1))
    }
}
```

## Dark Mode Support

### Theme Toggle

```kotlin
enum class ThemeMode {
    Light, Dark, System
}

val LocalThemeMode = compositionLocalOf { ThemeMode.System }

@Composable
fun ThemeProvider(
    mode: ThemeMode = ThemeMode.System,
    content: @Composable () -> Unit
) {
    val isDark = when (mode) {
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
        ThemeMode.System -> window.matchMedia("(prefers-color-scheme: dark)").matches
    }
    
    CompositionLocalProvider(
        LocalThemeMode provides mode
    ) {
        Div(
            attrs = {
                classes(if (isDark) "dark" else "light")
            }
        ) {
            content()
        }
    }
}
```

### Dark Mode Styles

```kotlin
val DarkModeCardStyle = ComponentStyle("dark-card") {
    base {
        Modifier.backgroundColor(Colors.White)
    }
    
    cssRule(".dark &") {
        Modifier.backgroundColor(ColorPalette.gray900)
    }
}
```

## Utility Classes

### Common Modifiers

```kotlin
object CommonModifiers {
    val centerContent = Modifier
        .display(DisplayStyle.Flex)
        .justifyContent(JustifyContent.Center)
        .alignItems(AlignItems.Center)
    
    val fullWidthHeight = Modifier
        .width(100.percent)
        .height(100.percent)
    
    val visuallyHidden = Modifier
        .position(Position.Absolute)
        .width(1.px)
        .height(1.px)
        .margin((-1).px)
        .overflow(Overflow.Hidden)
        .clip("rect(0, 0, 0, 0)")
    
    val gradientBackground = Modifier.background(
        linearGradient(
            45.deg,
            stop(ColorPalette.blue500),
            stop(ColorPalette.emerald500)
        )
    )
}
```

## Performance Considerations

### CSS-in-JS Optimization

```kotlin
// Use ComponentStyle for reusable styles
val ReusableStyle = ComponentStyle("reusable") {
    base { /* styles */ }
}

// Avoid inline styles for complex styling
@Composable
fun OptimizedComponent() {
    // Good: Using ComponentStyle
    Div(ReusableStyle.toModifier()) {
        // Content
    }
    
    // Avoid: Inline complex styles
    // Div(Modifier.backgroundColor(Color.red).padding(16.px)) { }
}
```

### Bundle Size Optimization

```kotlin
// Tree-shake unused styles by organizing them properly
object Styles {
    val button = ComponentStyle("btn") { /* styles */ }
    val card = ComponentStyle("card") { /* styles */ }
}

// Only import what you need
import Styles.button
```

## Accessibility

### Focus Management

```kotlin
val FocusableStyle = ComponentStyle("focusable") {
    base {
        Modifier.outline("none")
    }
    
    focus {
        Modifier.boxShadow(
            0.px, 0.px, 0.px, 2.px, SiteTheme.Colors.primary.copy(alpha = 0.5)
        )
    }
}
```

### High Contrast Support

```kotlin
val HighContrastStyle = ComponentStyle("high-contrast") {
    cssRule("@media (prefers-contrast: high)") {
        Modifier
            .border(2.px, LineStyle.Solid, Colors.Black)
            .backgroundColor(Colors.White)
            .color(Colors.Black)
    }
}
```

## Best Practices

1. **Mobile-First**: Always start with mobile styles and enhance for larger screens
2. **Component Styles**: Use ComponentStyle for reusable styles rather than inline modifiers
3. **Consistent Spacing**: Use the defined spacing scale throughout the application
4. **Performance**: Minimize CSS-in-JS runtime overhead with static styles
5. **Accessibility**: Include focus states, high contrast support, and semantic markup
6. **Dark Mode**: Design with both light and dark themes in mind
7. **Animation**: Use subtle animations to enhance UX without being distracting
8. **Typography**: Maintain consistent typography hierarchy and line heights
9. **Kobweb Layout Components**: Use Kobweb's layout components (Row, Column) instead of Div elements with CSS properties for layout. This provides better integration with Kobweb's framework and more consistent behavior.

### Layout Component Examples

```kotlin
// Prefer this (using Kobweb's Row)
Row(
    modifier = MyStyle.toModifier(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    // Content
}

// Instead of this (using Compose HTML's Div with CSS)
Div(
    attrs = {
        classes(MyStyle)
    }
) {
    // Content
}

// For horizontal spacing between items, use Arrangement.spacedBy
Row(
    horizontalArrangement = Arrangement.spacedBy(16.px)
) {
    // Items will have 16px spacing between them
}

// Instead of using CSS gap property
Div(
    Modifier.display(DisplayStyle.Flex).gap(16.px)
) {
    // Items
}
```