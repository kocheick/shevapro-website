# Comprehensive Migration Plan: Kotlin/JS React to Kobweb

This document outlines the complete migration plan for converting the existing Kotlin/JS React portfolio website (
`shevapro-webapp`) to the Kobweb framework (`shevapro-website`). This plan provides a comprehensive roadmap for
implementation teams.

## Progress Summary

### Completed

- [x] Documentation structure created
- [x] Migration plan document established
- [x] Component architecture guidelines documented
- [x] Styling guidelines documented
- [x] Basic project directory structure created
- [x] Site theme system with colors, spacing, typography
- [x] Basic Layout component with Header and Footer
- [x] Updated HomePage with original Hero section content (exact match)
- [x] Theme provider with dark/light mode support
- [x] About page with original content migrated
- [x] Data models created (Article, Project)
- [x] Basic Button component created
- [x] Development server running successfully at http://localhost:8083

### In Progress

- [ ] Silk CssStyle implementation for components (some challenges with API)
- [ ] Portfolio page implementation
- [ ] Blog page implementation
- [ ] Additional reusable UI components
- [ ] Responsive design improvements
- [ ] Additional page implementations (About, Portfolio, Blog, etc.)
- [ ] Data models for content

### Next Steps

- [ ] Create Portfolio page with project showcase
- [ ] Create Blog page with article listings
- [ ] Create Music and Design pages
- [ ] Add real content and data
- [ ] Implement proper navigation between pages
- [ ] Style enhancements and responsive breakpoints
- [ ] Add animations and transitions
- [ ] Complete remaining layout components with working styles
- [ ] Create reusable UI components (Button, Card, etc.)
- [ ] Implement additional pages (About, Portfolio, Blog, Music, Design)
- [ ] Add content models and API endpoints
- [ ] Migrate content from original React project

## Phase 1: Project Setup and Structure

### 1.1 Create Documentation Structure

- [x] Create a `docs` folder in the project root to store all documentation
- [x] Add this migration plan document as `docs/migration-plan.md`
- [x] Create additional documentation for component architecture, styling guidelines, and API specifications

### 1.2 Project Structure Alignment

Migrate from the current structure to Kobweb's conventions:

- **From**: `src/clientMain` → **To**: `site/src/jsMain`
- **From**: `org.shevapro` → **To**: `com.shevapro.website`

Create appropriate subdirectories following Kobweb conventions:

```
site/src/jsMain/
├── kotlin/com/shevapro/website/
│   ├── components/     # UI components
│   ├── pages/          # Page definitions
│   ├── models/         # Data models
│   ├── api/            # API endpoints
│   ├── styles/         # Style definitions
│   └── utils/          # Utility functions
└── resources/
    └── public/         # Static assets
```

### 1.3 Gradle Configuration

Update build scripts from the current multiplatform setup to Kobweb's configuration:

- Replace the current complex `build.gradle.kts` with Kobweb's plugin setup
- Update from Kotlin 1.7.21 to the version used by Kobweb
- Configure the Kobweb application settings in `site/build.gradle.kts`
- Update dependencies to use Kobweb-specific libraries

### 1.4 Resource Migration

Move static assets:

- **Images**: `src/clientMain/resources/images` → `site/src/jsMain/resources/public`
- **Fonts**: `src/clientMain/resources/fonts` → `site/src/jsMain/resources/public`
- Update any hardcoded paths in the code to use Kobweb's resource system

## Phase 2: Frontend Migration

### 2.1 Page Structure Implementation

Create the basic application structure:

- [x] Implement `AppEntry.kt` as the main entry point (already exists)
- [ ] Configure routing in the Kobweb application
- [ ] Set up the main layout components (header, footer, main content area)

### 2.2 Page Conversion

Convert each React page to a Kobweb `@Page` composable:

- [x] HomePage (`components/pages/HomePage.kt`)
- [x] AboutPage (`components/pages/about/AboutPage.kt`)
- [ ] PortfolioPage (`components/pages/portfolio/PortfolioPage.kt`)
- [ ] BlogPage (`components/pages/BlogPage.kt`)
- [ ] MusicPage (`components/pages/MusicPage.kt`)
- [ ] DesignPage (`components/pages/DesignPage.kt`)
- [ ] ArticlePage for individual blog/portfolio articles

### 2.3 Component Migration

Convert React components to Compose UI components:

- [x] Navbar → Create a Kobweb header component
- [x] Footer → Create a Kobweb footer component
- [x] Main content wrapper → Create a Kobweb layout component
- [x] Create reusable UI components for:
  - [x] Buttons
  - [ ] Cards, and other UI elements
    - [ ] Blog post previews
    - [ ] Portfolio items
    - [ ] Music player components
    - [ ] Design gallery components

### 2.4 State Management

Replace React state management with Compose state:

- [ ] Convert `useState` hooks to `mutableStateOf`
- [ ] Replace context providers (like `ResourceContext.Provider`) with Compose's `CompositionLocal`
- [ ] Implement proper state hoisting for shared state
- [ ] Use `remember` and `rememberSaveable` for component-level state

### 2.5 Routing Implementation

Replace React Router with Kobweb's routing system:

- [ ] Define routes using Kobweb's `@Page` annotation instead of React Router's Route components
- [ ] Implement navigation using Kobweb's navigation APIs
- [ ] Preserve the same URL structure for SEO purposes:
    - Home: `/`
    - Portfolio: `/portfolio`
    - Blog: `/blog`
    - Blog Post: `/blog/:title`
    - Music: `/music`
    - Design: `/design`
    - About: `/about`

## Phase 3: Backend Migration

### 3.1 API Endpoints

Convert Ktor server routes to Kobweb API endpoints:

- [ ] Implement the blog API endpoints using Kobweb's API system
- [ ] Create API handlers for fetching blog posts and other data
- [ ] Update the frontend fetch calls to use the new API paths
- [ ] Implement proper error handling and loading states

### 3.2 Data Models

Migrate data models from the existing application:

- [x] Blog Article model
- [x] Portfolio Item model
- [ ] Music/Song model
- [ ] Design/Photo model
- [ ] Ensure models are compatible with Kobweb's serialization system

### 3.3 Server Configuration

Configure Kobweb server settings:

- [ ] Set up CORS, caching, and other server settings
- [ ] Implement security headers (already present in the original app)
- [ ] Configure proper production deployment settings

## Phase 4: Content and Styling Migration

### 4.1 Styling System Implementation

Convert CSS/styling approach to Silk UI:

- [ ] Replace inline styles and CSS classes with Silk's styling system
- [ ] Create a consistent theme using Silk's theming capabilities
- [ ] Implement responsive design using Silk's modifiers
- [ ] Create custom style modifiers for common patterns

### 4.2 Content Migration

Transfer all content from the existing site:

- [x] Migrate About page content
- [ ] Migrate blog posts and articles
- [ ] Transfer portfolio items and project descriptions
- [ ] Move music files and metadata
- [ ] Transfer design gallery items
- [ ] Update the other page content

### 4.3 SEO Optimization

Implement proper SEO for all pages:

- [ ] Set up metadata for each page (title, description, etc.)
- [ ] Configure site maps and robots.txt (already present in original app)
- [ ] Ensure all routes are properly indexed
- [ ] Implement proper semantic HTML structure

## Phase 5: Testing and Deployment

### 5.1 Testing Strategy

Implement comprehensive testing:

- [ ] Create unit tests for components and pages
- [ ] Test all API endpoints and data fetching
- [ ] Verify responsive design across devices
- [ ] Implement accessibility testing

### 5.2 Performance Optimization

Optimize the application for performance:

- [ ] Implement lazy loading for images and content
- [ ] Configure proper caching strategies
- [ ] Minimize bundle size through code splitting
- [ ] Optimize asset loading and delivery

### 5.3 Deployment Pipeline

Set up the deployment process:

- [ ] Configure Kobweb's export functionality for static deployment
- [ ] Set up CI/CD for automatic deployment
- [ ] Implement monitoring and analytics
- [ ] Configure proper environment variables for different environments

## Implementation Guidelines

### React to Compose Migration Patterns

When converting React components to Compose:

**Component Structure:**

```kotlin
// React
val Component = FC<Props> { props -> ... }

// Compose
@Composable fun Component(param1: Type, param2: Type) { ... }
```

**State Management:**

```kotlin
// React
val (state, setState) = useState(initialValue)

// Compose
var state by remember { mutableStateOf(initialValue) }
```

**Effects:**

```kotlin
// React
useEffect { ... }

// Compose
LaunchedEffect(key) { ... }
```

**Context:**

```kotlin
// React
ResourceContext.Provider { value = resource }

// Compose
CompositionLocalProvider(LocalResource provides resource) { ... }
```

**Styling:**

```kotlin
// React
className = "tailwind-classes"

// Compose
Modifier.styleModifier()
```

### Kobweb-Specific Patterns

**Page Definition:**

```kotlin
@Page
@Composable
fun PageName() {
    // Page content
}
```

**API Endpoints:**

```kotlin
@Api
fun apiEndpoint(ctx: ApiContext) {
    // Handle API request
}
```

**Routing:**

```kotlin
// Navigation
onClick { router.navigateTo("/path") }
```

**Styling with Silk:**

```kotlin
// Define styles
val CustomStyle = ComponentStyle {
    base {
        Modifier.padding(12.px)
    }
    // Variants and responsive styles
}

// Apply styles
Div(CustomStyle.toModifier()) {
    // Content
}
```

## Timeline and Milestones

### Week 1-2: Project Setup and Structure

- Complete project structure setup
- Configure build scripts
- Migrate resources

### Week 3-5: Core UI Components

- Implement main layout components
- Convert essential pages (Home, About)
- Set up routing

### Week 6-8: Feature Pages

- Implement Portfolio, Blog, Music, and Design pages
- Create reusable components

### Week 9-10: Backend and API

- Implement API endpoints
- Set up data fetching

### Week 11-12: Styling and Content

- Apply consistent styling
- Migrate all content
- Optimize for responsive design

### Week 13-14: Testing and Optimization

- Implement tests
- Performance optimization
- SEO implementation

### Week 15: Deployment

- Configure deployment pipeline
- Final testing
- Launch

## Team Assignments

### Team Infrastructure (Phase 1)

- Project setup and structure migration
- Gradle configuration
- Resource migration

### Team Frontend Core (Phase 2.1-2.3)

- Layout components
- Page structure implementation
- Core component migration

### Team Components (Phase 2.4-2.5)

- State management migration
- Routing implementation
- Reusable component creation

### Team Backend (Phase 3)

- API endpoint migration
- Data model updates
- Server configuration

### Team Content (Phase 4)

- Styling system implementation
- Content migration
- SEO optimization

### Team QA & Deploy (Phase 5)

- Testing strategy implementation
- Performance optimization
- Deployment pipeline setup

## Conclusion

This migration plan provides a comprehensive roadmap for converting the existing Kotlin/JS React portfolio website to
Kobweb. By following this structured approach, implementation teams can ensure a smooth transition while preserving all
functionality and content from the original site.

The migration leverages Kobweb's modern architecture and Compose for Web's declarative UI paradigm to create a more
maintainable, performant, and feature-rich portfolio website.