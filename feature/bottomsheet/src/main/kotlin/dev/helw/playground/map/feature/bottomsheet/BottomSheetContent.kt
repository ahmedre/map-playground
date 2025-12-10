package dev.helw.playground.map.feature.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.core.ui.screen.BottomSheetScreen
import dev.helw.playground.map.feature.bottomsheet.city.CityDetails
import dev.helw.playground.map.feature.bottomsheet.list.CityList
import dev.zacsweers.metro.AppScope

@CircuitInject(BottomSheetScreen::class, AppScope::class)
@Composable
fun BottomSheetContent(state: BottomSheetState, modifier: Modifier = Modifier) {
    if (state.cityState != null) {
        CityDetails(state.cityState, modifier)
    } else if (state.cityListState != null) {
        CityList(state.cityListState, modifier)
    }
}