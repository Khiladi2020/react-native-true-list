## react-native-true-list

A truly native list for React Native. No JS-side calculations for item layout or rendering—heavy lifting is delegated to the platform’s native UI layer for consistently smooth performance.

### Highlights

-   Native item templating for flexible, reusable cells
-   Unmatched scroll performance, even with long lists and rapid flings
-   No blank gaps or flashes during ultra-fast scrolling

### Why this exists

JS-driven measurement can bottleneck large lists in React Native. `react-native-true-list` minimizes JS work by relying on native layout and rendering, preserving React Native ergonomics while delivering native-class performance.

### Status

Early development. API and docs are evolving.

### Getting started

-   Prerequisites: Node.js, Xcode (iOS), Android Studio (Android), CocoaPods (iOS), Watchman (macOS).
-   Install: Coming soon (package not yet published).
-   Local development:
    1. Clone the repo
    2. Install dependencies
    3. Install iOS pods (if building for iOS)
    4. Run on a simulator/device

```bash
git clone <repo-url>
cd react-native-true-list
npm install

# iOS
npx pod-install
npm run ios

# Android
npm run android
```

### Usage

Documentation and examples are in progress. Planned capabilities include:

-   Item templates with prop-driven configuration
-   Header/footer support
-   Sticky headers
-   Large data sets with near-zero JS overhead

### Performance goals

-   60fps+ scrolling on mid-range devices
-   O(1) JS work per frame during steady-state scrolling
-   No layout thrashing or reflows driven by JS

### Contributing

PRs and discussions are welcome. Please open an issue to propose API changes or report performance findings.

### License

TBD
