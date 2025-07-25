# Updated Migration Plan: Kotlin/JS React to Kobweb

This document provides an updated migration plan for converting the existing Kotlin/JS React portfolio website (`shevapro-webapp`) to the Kobweb framework (`shevapro-website`). This plan incorporates findings from analyzing both projects and provides a comprehensive roadmap for implementation teams.

## Current Progress Summary

### Completed
- [x] Documentation structure created
- [x] Migration plan document established
- [x] Component architecture guidelines documented
- [x] Styling guidelines documented
- [x] Basic project directory structure created
- [x] Theme system implementation (SiteTheme.kt)
- [x] Basic Header component implemented

### In Progress
- [ ] Layout components (Footer, Layout wrapper)
- [ ] Homepage conversion
- [ ] Additional page implementations

### Next Steps
- [ ] Complete layout component implementation
- [ ] Create data models for portfolio content
- [ ] Implement additional pages (About, Portfolio, Blog, etc.)

## Migration Plan

### Phase 1: Project Setup and Structure
- [x] Create documentation structure
- [x] Set up basic project structure following Kobweb conventions
- [x] Implement theme system (SiteTheme.kt)
- [ ] Configure build scripts and dependencies
- [ ] Migrate static resources (images, fonts, etc.)

### Phase 2: Frontend Migration
- [ ] Complete layout components implementation
  - [x] Header component
  - [ ] Footer component
  - [ ] Layout wrapper component
- [ ] Convert React pages to Kobweb @Page composables
  - [ ] HomePage
  - [ ] AboutPage
  - [ ] PortfolioPage
  - [ ] BlogPage
  - [ ] MusicPage
  - [ ] DesignPage
- [ ] Implement reusable UI components
  - [ ] Buttons, cards, and other UI elements
  - [ ] Blog post previews
  - [ ] Portfolio items
  - [ ] Music player components
  - [ ] Design gallery components
- [ ] Convert React state management to Compose state
- [ ] Implement routing using Kobweb's @Page system

### Phase 3: Backend Migration
- [ ] Implement API endpoints using Kobweb's API system
- [ ] Create data models for content
- [ ] Set up server configuration
- [ ] Implement data fetching and state management

### Phase 4: Content and Styling Migration
- [ ] Apply consistent styling using Silk UI
- [ ] Migrate all content from the original site
- [ ] Implement responsive design
- [ ] Add SEO optimization

### Phase 5: Testing and Deployment
- [ ] Implement testing strategy
- [ ] Optimize performance
- [ ] Configure deployment pipeline
- [ ] Launch the migrated site

## Implementation Guidelines

### Project Structure Mapping

| Original (shevapro-webapp) | New (shevapro-website) |
|----------------------------|------------------------|
| src/clientMain/kotlin/components | site/src/jsMain/kotlin/com/shevapro/website/components |
| src/clientMain/kotlin/components/commons | site/src/jsMain/kotlin/com/shevapro/website/components/ui |
| src/clientMain/kotlin/components/pages | site/src/jsMain/kotlin/com/shevapro/website/pages |
| src/clientMain/kotlin/clientModels | site/src/jsMain/kotlin/com/shevapro/website/models |
| src/clientMain/kotlin/clientUtils | site/src/jsMain/kotlin/com/shevapro/website/utils |
| src/clientMain/kotlin/hooks | site/src/jsMain/kotlin/com/shevapro/website/utils |
| src/clientMain/resources | site/src/jsMain/resources/public |
| src/commonMain/kotlin/commonModels | site/src/jsMain/kotlin/com/shevapro/website/models |
| src/serverMain/kotlin/org/shevapro/application | site/src/jsMain/kotlin/com/shevapro/website/api |

### Component Migration Patterns

#### React Component to Compose Component

```
// React
val Component = FC<Props> { props -> ... }

// Compose
@Composable fun Component(param1: Type, param2: Type) { ... }
```

#### State Management

```
// React
val (state, setState) = useState(initialValue)

// Compose
var state by remember { mutableStateOf(initialValue) }
```

#### Effects

```
// React
useEffect { ... }

// Compose
LaunchedEffect(key) { ... }
```

## Team Assignments and Timeline

### Team 1: Infrastructure and Layout
- **Tasks**: Project setup, layout components, theme implementation
- **Timeline**: Weeks 1-3
- **Dependencies**: None

### Team 2: Pages and Components
- **Tasks**: Page implementation, UI components, state management
- **Timeline**: Weeks 3-8
- **Dependencies**: Team 1 (layout components)

### Team 3: Backend and Data
- **Tasks**: API endpoints, data models, server configuration
- **Timeline**: Weeks 4-9
- **Dependencies**: Team 2 (page structure)

### Team 4: Content and Styling
- **Tasks**: Content migration, styling, responsive design
- **Timeline**: Weeks 6-11
- **Dependencies**: Team 2 (components), Team 3 (data models)

### Team 5: Testing and Deployment
- **Tasks**: Testing, performance optimization, deployment
- **Timeline**: Weeks 10-14
- **Dependencies**: All other teams

## Communication and Coordination

### Weekly Sync Meetings
- All team leads meet weekly to discuss progress and blockers
- Cross-team dependencies are reviewed and prioritized

### Handoff Procedures
- Team 1 → Team 2: Layout components documentation and examples
- Team 2 → Team 3: Page structure and component API documentation
- Team 3 → Team 4: Data model documentation and API endpoints
- All Teams → Team 5: Component and feature completion for testing

## Conclusion

This updated migration plan provides a comprehensive roadmap for converting the existing Kotlin/JS React portfolio website to Kobweb. By following this structured approach and clear team assignments, the migration can be completed efficiently while maintaining all functionality from the original site.

The migration leverages Kobweb's modern architecture and Compose for Web's declarative UI paradigm to create a more maintainable, performant, and feature-rich portfolio website.