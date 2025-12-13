package dev.helw.playground.map.feature.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import dev.helw.playground.map.core.ui.screen.BottomSheetScreen
import dev.helw.playground.map.feature.bottomsheet.city.CityScreen
import dev.helw.playground.map.feature.bottomsheet.list.CityListScreen
import dev.zacsweers.metro.AppScope

@CircuitInject(BottomSheetScreen::class, AppScope::class)
@Composable
fun BottomSheetContent(state: BottomSheetState, modifier: Modifier = Modifier) {
    if (state.city != null) {
        CircuitContent(CityScreen(state.city), modifier)
    } else {
        CircuitContent(CityListScreen, modifier)
    }
}