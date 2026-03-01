# Fosh Labs KMP Architecture Kit

[![Maven Central](https://img.shields.io/maven-central/v/io.github.foshlabs.kmp.architecturekit/architecture-core)](https://central.sonatype.com/artifact/io.github.foshlabs.kmp.architecturekit/architecture-core)

A Kotlin Multiplatform architecture library providing shared base classes and patterns for building clean, layered apps on iOS and Android.

## Modules

### `architecture-core`

KMP module (`commonMain` + `androidMain` + `iosMain`) containing:

- **`BaseViewModel<S>`** — Base ViewModel with lifecycle-aware state management. Exposes state via a `StateFlow<S>` and uses `@NativeCoroutinesState` so Swift can observe it on iOS.
- **`ViewModelState`** — Marker interface for ViewModel state classes.
- **`UseCase<Input, Output>`** — Synchronous use case base class.
- **`UnitUseCase<Output>`** — Synchronous use case with no input.
- **`SuspendUseCase<Input, Output>`** — Suspend (async) use case base class.
- **`UnitSuspendUseCase<Output>`** — Suspend use case with no input.
- **`FlowUseCase<Input, Output>`** — Reactive Flow use case base class.
- **`UnitFlowUseCase<Output>`** — Flow use case with no input.

## Requirements

- Kotlin 2.x
- For Android: minSdk 24+
- Depends on [KMP-ObservableViewModel](https://github.com/rickclephas/KMP-ObservableViewModel) (and its transitive dependencies).

## Installation

The library is published to [Maven Central](https://central.sonatype.com/artifact/io.github.foshlabs.kmp.architecturekit/architecture-core). Add the dependency to your project.

**Kotlin DSL (`build.gradle.kts`)**

In your shared KMP module’s `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            api("io.github.foshlabs.kmp.architecturekit:architecture-core:0.1.0")
        }
    }
}
```

For **iOS**, if you build a framework that re-exports this library, add the export in your framework block:

```kotlin
iosTarget.binaries.framework {
    baseName = "YourFramework"
    export("io.github.foshlabs.kmp.architecturekit:architecture-core:0.1.0")
}
```

Check [Maven Central](https://central.sonatype.com/artifact/io.github.foshlabs.kmp.architecturekit/architecture-core) for the latest version.

## Usage

### ViewModel and state

Define a state class and a ViewModel that extends `BaseViewModel`:

```kotlin
data class CounterState(val count: Int = 0) : ViewModelState

class CounterViewModel : BaseViewModel<CounterState>(CounterState()) {
    fun increment() {
        state = state.copy(count = state.count + 1)
    }
}
```

On iOS, observe the ViewModel’s `states` flow via the generated async sequence or callback APIs from KMP-NativeCoroutines.

### Use cases

**Sync:**

```kotlin
class GetUserNameUseCase : UseCase<UserId, String>() {
    override fun invoke(input: UserId): String = input.name
}
```

**Suspend:**

```kotlin
class LoadUserUseCase : SuspendUseCase<UserId, User>() {
    override suspend fun invoke(input: UserId): User = api.getUser(input)
}
```

**Flow:**

```kotlin
class ObserveMessagesUseCase : FlowUseCase<ChannelId, Message>() {
    override fun invoke(input: ChannelId): Flow<Message> = repo.messagesFor(input)
}
```

Use the `Unit*` variants when there is no input (e.g. `UnitSuspendUseCase<Result>`).

## License

MIT License. See the [artifact POM](https://central.sonatype.com/artifact/io.github.foshlabs.kmp.architecturekit/architecture-core) for details.
