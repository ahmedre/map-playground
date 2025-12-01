package dev.helw.playground.map.feature.mapscreen

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object MapWrapperScreen : Screen {
    data class MapState(
        val eventSink: (Event) -> Unit
    ) : CircuitUiState {

        sealed class Event
    }
}