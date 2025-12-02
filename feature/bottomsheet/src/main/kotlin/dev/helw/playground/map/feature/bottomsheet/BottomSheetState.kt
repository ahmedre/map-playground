package dev.helw.playground.map.feature.bottomsheet

import com.slack.circuit.runtime.CircuitUiState
import dev.helw.playground.map.feature.bottomsheet.city.CityScreen
import dev.helw.playground.map.feature.bottomsheet.list.CityListScreen

data class BottomSheetState(
    val cityListState: CityListScreen.CityListState,
    val cityState: CityScreen.CityState?,
    val eventSink: (Event) -> Unit
) : CircuitUiState

sealed class Event