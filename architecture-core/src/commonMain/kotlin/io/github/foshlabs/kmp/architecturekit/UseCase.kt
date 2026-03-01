package io.github.foshlabs.kmp.architecturekit

abstract class UseCase<Input, Output> {
    abstract operator fun invoke(input: Input): Output
}

abstract class UnitUseCase<Output> {
    abstract operator fun invoke(): Output
}
