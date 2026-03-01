# Fosh Labs KMP Architecture Kit

[![Maven Central](https://img.shields.io/maven-central/v/io.github.foshlabs.kmp.architecturekit/architecture-core)](https://central.sonatype.com/artifact/io.github.foshlabs.kmp.architecturekit/architecture-core)

A Kotlin Multiplatform architecture library providing shared base classes and patterns for building clean, layered apps on iOS and Android.

## Modules

### `architecture-core`

KMP module (commonMain + androidMain + iosMain) containing:

- `BaseViewModel` — Base ViewModel with lifecycle-aware state management
- `ViewModelState` — Marker interface for ViewModel state classes
- `UseCase` — Synchronous use case base class
- `SuspendUseCase` — Suspend (async) use case base class
- `FlowUseCase` — Reactive Flow use case base class

## Installation

The library is published to [Maven Central](https://central.sonatype.com/artifact/io.github.foshlabs.kmp.architecturekit/architecture-core). Add the dependency to your project:

**Option 1: Kotlin DSL (`build.gradle.kts`)**

// In your shared KMP module's build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            api("io.github.foshlabs.kmp.architecturekit:architecture-core:0.1.0")
        }
    }
}
// For iOS: add export("io.github.foshlabs.kmp.architecturekit:architecture-core:0.1.0") to your framework block
