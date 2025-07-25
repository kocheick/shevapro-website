---
title: Building Web UIs with Compose HTML
date: March 10, 2024
description: Explore the power of Compose HTML for web development. Learn about best practices, performance optimization, and how to create responsive web applications.
author: Shevapro
tags: [compose, web, ui, kotlin]
posted: true
layout: .components.layouts.MarkdownLayout
---

# Building Web UIs with Compose HTML

Compose HTML brings the familiar Jetpack Compose paradigm to web development, allowing you to build modern web
applications using Kotlin and a declarative UI approach. In this article, we'll explore best practices, tips, and tricks
for creating responsive and performant web applications.

## What is Compose HTML?

Compose HTML is part of the Compose Multiplatform project, enabling developers to create web UIs using the same
declarative approach as Jetpack Compose for Android. It compiles to JavaScript and manipulates the DOM directly.

## Key Advantages

- **Familiar API**: If you know Jetpack Compose, you already know Compose HTML
- **Type Safety**: Full Kotlin type safety in your web development
- **Reactive Updates**: Automatic UI updates when state changes
- **CSS-in-Kotlin**: Style your components without leaving Kotlin

## Getting Started

### Basic Setup

```kotlin
@Composable
fun App() {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
                padding(20.px)
            }
        }
    ) {
        H1 { Text("Welcome to Compose HTML!") }
        Button(
            attrs = {
                onClick { console.log("Button clicked!") }
            }
        ) {
            Text("Click me")
        }
    }
}
```

## Styling Best Practices

### 1. Use CSS-in-Kotlin for Component Styles

```kotlin
val HeaderStyle = CssStyle {
    base {
        fontSize(24.px)
        fontWeight(FontWeight.Bold)
        color(Color.darkblue)
        margin(16.px)
    }
    
    hover {
        color(Color.blue)
    }
}

@Composable
fun Header(text: String) {
    H1(HeaderStyle.toModifier()) {
        Text(text)
    }
}
```

### 2. Create Reusable Style Variables

```kotlin
object AppStyles {
    val primaryColor = Color.blue
    val spacing = 16.px
    val borderRadius = 8.px
    
    val cardStyle = CssStyle {
        base {
            backgroundColor(Color.white)
            padding(spacing)
            borderRadius(borderRadius)
            boxShadow(0.px, 2.px, 4.px, rgba(0, 0, 0, 0.1))
        }
    }
}
```

## Responsive Design

### Using CSS Grid and Flexbox

```kotlin
@Composable
fun ResponsiveGrid(items: List<String>) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(250px, 1fr))")
                gap(16.px)
                padding(20.px)
            }
        }
    ) {
        items.forEach { item ->
            Card(item)
        }
    }
}
```

### Media Queries with CSS

```kotlin
val ResponsiveStyle = CssStyle {
    base {
        fontSize(16.px)
        padding(10.px)
    }
    
    media(mediaMinWidth(768.px)) {
        self {
            fontSize(18.px)
            padding(20.px)
        }
    }
}
```

## State Management

### Using remember and mutableStateOf

```kotlin
@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    
    Div {
        P { Text("Count: $count") }
        Button(
            attrs = {
                onClick { count++ }
            }
        ) {
            Text("Increment")
        }
    }
}
```

### Handling Form State

```kotlin
@Composable
fun ContactForm() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    
    Form(
        attrs = {
            onSubmit { event ->
                event.preventDefault()
                // Handle form submission
                submitForm(name, email, message)
            }
        }
    ) {
        Input(
            type = InputType.Text,
            attrs = {
                placeholder("Your name")
                value(name)
                onInput { name = it.value }
            }
        )
        
        Input(
            type = InputType.Email,
            attrs = {
                placeholder("Your email")
                value(email)
                onInput { email = it.value }
            }
        )
        
        TextArea(
            attrs = {
                placeholder("Your message")
                value(message)
                onInput { message = it.value }
            }
        )
        
        Button(
            type = ButtonType.Submit
        ) {
            Text("Send Message")
        }
    }
}
```

## Performance Tips

### 1. Use Key for List Items

```kotlin
@Composable
fun ItemList(items: List<Item>) {
    items.forEach { item ->
        key(item.id) {
            ItemCard(item)
        }
    }
}
```

### 2. Optimize Recomposition

```kotlin
@Composable
fun ExpensiveComponent(data: String) {
    val processedData = remember(data) {
        // Expensive computation only runs when data changes
        processData(data)
    }
    
    Div {
        Text(processedData)
    }
}
```

### 3. Use LaunchedEffect for Side Effects

```kotlin
@Composable
fun DataLoader(userId: String) {
    var userData by remember { mutableStateOf<UserData?>(null) }
    
    LaunchedEffect(userId) {
        userData = fetchUserData(userId)
    }
    
    userData?.let { data ->
        UserProfile(data)
    } ?: LoadingSpinner()
}
```

## Common Patterns

### Loading States

```kotlin
@Composable
fun AsyncContent() {
    var isLoading by remember { mutableStateOf(true) }
    var data by remember { mutableStateOf<List<String>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(Unit) {
        try {
            data = fetchData()
            isLoading = false
        } catch (e: Exception) {
            error = e.message
            isLoading = false
        }
    }
    
    when {
        isLoading -> LoadingSpinner()
        error != null -> ErrorMessage(error!!)
        else -> DataList(data)
    }
}
```

### Conditional Rendering

```kotlin
@Composable
fun ConditionalContent(showAdvanced: Boolean) {
    Div {
        Text("Basic content")
        
        if (showAdvanced) {
            AdvancedFeatures()
        }
    }
}
```

## Integration with JavaScript

### Calling JavaScript Functions

```kotlin
@JsModule("./utils.js")
external fun formatDate(timestamp: Long): String

@Composable
fun DateDisplay(timestamp: Long) {
    val formattedDate = remember(timestamp) {
        formatDate(timestamp)
    }
    
    Span { Text(formattedDate) }
}
```

## Conclusion

Compose HTML offers a powerful way to build web applications using familiar Kotlin and Compose concepts. By following
these best practices, you can create performant, maintainable, and responsive web applications.

Key takeaways:

- Leverage CSS-in-Kotlin for styling
- Use proper state management patterns
- Optimize for performance with keys and remember
- Create reusable components and styles
- Handle loading and error states gracefully

The ecosystem is rapidly evolving, so stay updated with the latest developments in Compose HTML and Kotlin/JS.

## Resources

- [Compose HTML Documentation](https://github.com/JetBrains/compose-multiplatform)
- [Kotlin/JS Documentation](https://kotlinlang.org/docs/js-overview.html)
- [CSS-in-Kotlin Styling Guide](https://github.com/JetBrains/compose-multiplatform/tree/master/tutorials/HTML/Style_Dsl)

Happy web development with Compose HTML! 🌐