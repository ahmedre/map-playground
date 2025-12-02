package dev.helw.playground.map.feature.bottomsheet.city

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dev.helw.playground.map.feature.bottomsheet.model.City
import kotlinx.parcelize.Parcelize

@Parcelize
data object CityScreen : Screen {
    data class CityState(
        val city: City,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed class Event {
        data object Close : Event()
    }
}
