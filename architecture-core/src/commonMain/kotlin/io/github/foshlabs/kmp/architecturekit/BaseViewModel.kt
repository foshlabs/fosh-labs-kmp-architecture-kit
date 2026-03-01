package io.github.foshlabs.kmp.architecturekit

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S : ViewModelState>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(
        viewModelScope = viewModelScope,
        value = initialState
    )

    protected var state: S
        get() = _state.value
        set(value) { _state.value = value }

    @NativeCoroutinesState
    val states: StateFlow<S> = _state.asStateFlow()
}

interface ViewModelState
