package dev.helw.playground.map.feature.settings

import com.slack.circuit.runtime.CircuitUiState

data class SettingsState(val eventSink: (Event) -> Unit) : CircuitUiState

sealed class Event {
    data object CloseSettings : Event()
}