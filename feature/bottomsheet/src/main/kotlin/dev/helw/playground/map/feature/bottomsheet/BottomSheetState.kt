package dev.helw.playground.map.feature.bottomsheet

import com.slack.circuit.runtime.CircuitUiState
import dev.helw.playground.map.core.location.City

data class BottomSheetState(
    val city: City?,
    val eventSink: (Event) -> Unit
) : CircuitUiState

sealed class Event