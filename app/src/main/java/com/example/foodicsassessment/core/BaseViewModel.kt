package com.example.foodicsassessment.core
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.Serializable

abstract class UiEvent

abstract class UiState : Serializable

@Composable
fun <viewModel : LifecycleObserver> viewModel.ObserveLifecycleEvents(lifecycle: Lifecycle) {
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(this@ObserveLifecycleEvents)
        onDispose {
            lifecycle.removeObserver(this@ObserveLifecycleEvents)
        }
    }
}


abstract class BaseViewModel<Event : UiEvent, State : UiState> : ViewModel(),
    DefaultLifecycleObserver {



    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (viewModelScope.isActive) {
            viewModelScope.coroutineContext.cancelChildren()
        }
    }

    private val _effectFlow = Channel<UiEffect>()
    val effectFlow = _effectFlow.receiveAsFlow()

    private val initialState: State by lazy { initialState() }

    abstract fun initialState(): State

    var viewState by mutableStateOf(initialState)
        private set

    abstract fun onEvent(event: Event)

    fun setState(reducer: State.() -> State) {
        val newState = viewState.reducer()
        viewState = newState
    }

    // Show error dialog
    fun showErrorDialog(uiText: UiText) {
        viewModelScope.launch {
            setEffect { CommonUiEffect.ShowDialog(uiText, false) }

        }
    }

    // Show success dialog
    fun showSuccessDialog(uiText: UiText) {
        viewModelScope.launch {
            setEffect { CommonUiEffect.ShowDialog(uiText, true) }
        }
    }

    // Show toast for success or error
    fun showToast(uiText: UiText) {
        viewModelScope.launch {
            setEffect { CommonUiEffect.ShowToast(uiText) }
        }
    }

    protected fun showUnauthenticatedState(uiText: UiText) {
        viewModelScope.launch {
            setEffect { CommonUiEffect.ShowUnauthenticatedState(uiText) }
        }
    }

    // Navigation effect
    protected fun navigate(route: Any, popUpTo: Any? = null, inclusive: Boolean = false, singleTop: Boolean = false) {
        viewModelScope.launch {
            setEffect { CommonUiEffect.Navigate(route, popUpTo, inclusive, singleTop) }
        }
    }

    // Navigate up
    protected fun navigateUp() {
        viewModelScope.launch { setEffect { CommonUiEffect.NavigateUp } }
    }

    // Set side effects
    protected suspend fun setEffect(builder: () -> UiEffect) {
        val effect = builder()
        _effectFlow.send(effect)
    }

    // Show error dialog by resource ID
    suspend fun showErrorDialog(@StringRes messageResId: Int) {
        _effectFlow.send(
            CommonUiEffect.ShowDialog(
                UiText.StringResource(messageResId)
            )
        )
    }

    // Show error dialog with a dynamic message
    suspend fun showErrorDialog(message: UiText, isSuccess: Boolean = false) {
        _effectFlow.send(
            CommonUiEffect.ShowDialog(
                uiText = message,
                success = isSuccess
            )
        )
    }


}
