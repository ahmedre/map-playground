package dev.helw.playground.map.feature.mapscreen

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dev.helw.playground.map.core.location.City
import kotlinx.parcelize.Parcelize

@Parcelize
data object MapWrapperScreen : Screen {
    data class MapState(
        val styleUrl: String,
        val selectedCity: City? = null,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState {

        sealed class Event {
            data object Settings : Event()
            data object SwapTheme : Event()
        }
    }
}