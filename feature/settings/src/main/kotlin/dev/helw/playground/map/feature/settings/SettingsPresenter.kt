package dev.helw.playground.map.feature.settings

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.ui.screen.SettingsScreen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class SettingsPresenter(
    @Assisted private val navigator: Navigator
): Presenter<SettingsState> {

    @CircuitInject(SettingsScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): SettingsPresenter
    }

    @Composable
    override fun present(): SettingsState {
        return SettingsState {
            if (it == Event.CloseSettings) {
                navigator.pop()
            }
        }
    }
}