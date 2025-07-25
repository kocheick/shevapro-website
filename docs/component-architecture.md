# Component Architecture Guidelines

This document outlines the component architecture and patterns for the Kobweb portfolio website migration.

## Component Structure

### Directory Organization

```
site/src/jsMain/kotlin/com/shevapro/website/
├── components/
│   ├── layout/           # Layout components (Header, Footer, Layout)
│   ├── sections/         # Page sections (Hero, About, Projects, etc.)
│   ├── ui/              # Reusable UI components (Button, Card, etc.)
│   └── widgets/         # Complex widgets (ContactForm, ProjectCard, etc.)
├── pages/               # Page definitions with @Page annotation
├── models/              # Data models and DTOs
├── api/                 # API endpoints and handlers
├── styles/              # Style definitions and themes
└── utils/               # Utility functions and extensions
```

## Component Patterns

### Layout Components

#### Header Component

```kotlin
@Composable
fun Header(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {}
) {
    // Navigation bar implementation
}
```

#### Footer Component

```kotlin
@Composable
fun Footer(
    modifier: Modifier = Modifier
) {
    // Footer with social links and copyright
}
```

#### Layout Wrapper

```kotlin
@Composable
fun Layout(
    title: String = "Shevapro",
    content: @Composable () -> Unit
) {
    // Main layout structure with header and footer
}
```

### Section Components

#### Hero Section

```kotlin
@Composable
fun HeroSection(
    modifier: Modifier = Modifier
) {
    // Main hero section with name, title, and CTA
}
```

#### About Section

```kotlin
@Composable
fun AboutSection(
    modifier: Modifier = Modifier
) {
    // About section with personal description and photo
}
```

#### Projects Section

```kotlin
@Composable
fun ProjectsSection(
    projects: List<ProjectItem>,
    modifier: Modifier = Modifier
) {
    // Featured projects showcase
}
```

### UI Components

#### Custom Button

```kotlin
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    variant: ButtonVariant = ButtonVariant.Primary,
    modifier: Modifier = Modifier
) {
    // Custom styled button component
}

enum class ButtonVariant {
    Primary, Secondary, Outline, Ghost
}
```

#### Project Card

```kotlin
@Composable
fun ProjectCard(
    project: ProjectItem,
    onReadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Project showcase card
}
```

#### Skill Badge

```kotlin
@Composable
fun SkillBadge(
    skill: String,
    level: SkillLevel = SkillLevel.Intermediate,
    modifier: Modifier = Modifier
) {
    // Skill display badge
}

enum class SkillLevel {
    Beginner, Intermediate, Advanced, Expert
}
```

## State Management Patterns

### Component State

```kotlin
@Composable
fun MyComponent() {
    var localState by remember { mutableStateOf("initial") }
    
    // Component logic here
}
```

### Shared State with CompositionLocal

```kotlin
val LocalTheme = compositionLocalOf<ThemeData> { 
    error("Theme not provided") 
}

@Composable
fun App() {
    CompositionLocalProvider(LocalTheme provides themeData) {
        // App content
    }
}
```

### Effect Patterns

```kotlin
@Composable
fun DataComponent() {
    var data by remember { mutableStateOf<List<Item>?>(null) }
    
    LaunchedEffect(Unit) {
        data = fetchData()
    }
    
    // Render based on data state
}
```

## Styling Patterns

### Component Styles

```kotlin
val HeroSectionStyle = ComponentStyle("hero-section") {
    base {
        Modifier
            .fillMaxWidth()
            .padding(vertical = 80.px)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .alignItems(AlignItems.Center)
    }
    
    breakpoint(Breakpoint.MD) {
        Modifier.padding(vertical = 120.px)
    }
}
```

### Responsive Design

```kotlin
val ResponsiveGridStyle = ComponentStyle("responsive-grid") {
    base {
        Modifier
            .display(DisplayStyle.Grid)
            .gridTemplateColumns("1fr")
            .gap(16.px)
    }
    
    breakpoint(Breakpoint.MD) {
        Modifier.gridTemplateColumns("repeat(2, 1fr)")
    }
    
    breakpoint(Breakpoint.LG) {
        Modifier.gridTemplateColumns("repeat(3, 1fr)")
    }
}
```

## Navigation Patterns

### Page Navigation

```kotlin
@Composable
fun NavigationLink(
    text: String,
    path: String,
    modifier: Modifier = Modifier
) {
    val router = rememberPageContext().router
    
    Link(
        path = path,
        modifier = modifier,
        text = text
    )
}
```

### Programmatic Navigation

```kotlin
@Composable
fun NavigateButton() {
    val router = rememberPageContext().router
    
    Button(
        onClick = { router.navigateTo("/about") }
    ) {
        Text("Go to About")
    }
}
```

## Data Fetching Patterns

### API Calls

```kotlin
@Composable
fun BlogList() {
    var posts by remember { mutableStateOf<List<BlogPost>?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(Unit) {
        try {
            posts = window.fetch("/api/blog/posts")
                .await()
                .json()
                .await()
                .unsafeCast<Array<BlogPost>>()
                .toList()
        } catch (e: Exception) {
            error = e.message
        } finally {
            loading = false
        }
    }
    
    when {
        loading -> LoadingSpinner()
        error != null -> ErrorMessage(error)
        posts != null -> BlogPostList(posts!!)
    }
}
```

## Component Testing Patterns

### Component Testing Structure

```kotlin
class ComponentTest {
    @Test
    fun testComponentRendering() {
        // Test component rendering and behavior
    }
    
    @Test
    fun testUserInteraction() {
        // Test user interactions
    }
}
```

## Performance Optimization

### Memoization

```kotlin
@Composable
fun ExpensiveComponent(data: List<Item>) {
    val processedData = remember(data) {
        data.map { processItem(it) }
    }
    
    // Use processedData
}
```

### Lazy Loading

```kotlin
@Composable
fun ImageGrid(images: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp)
    ) {
        items(images) { imageUrl ->
            AsyncImage(
                src = imageUrl,
                loading = "lazy"
            )
        }
    }
}
```

## Best Practices

1. **Component Composition**: Prefer composition over inheritance
2. **Single Responsibility**: Each component should have a single, well-defined purpose
3. **Props Interface**: Define clear interfaces for component parameters
4. **State Management**: Keep state as close to where it's used as possible
5. **Performance**: Use `remember` and `LaunchedEffect` appropriately
6. **Accessibility**: Include proper ARIA attributes and semantic HTML
7. **Responsive Design**: Design mobile-first with progressive enhancement
8. **Error Handling**: Implement proper error boundaries and loading states