package dev.helw.playground.map.feature.bottomsheet.list

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dev.helw.playground.map.core.location.City
import kotlinx.parcelize.Parcelize

@Parcelize
data object CityListScreen : Screen {
    data class CityListState(
        val isReverseSort: Boolean,
        val cities: List<City>,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed class Event {
        data class SelectCity(val city: City): Event()
        data object ToggleSorting : Event()
    }
}
