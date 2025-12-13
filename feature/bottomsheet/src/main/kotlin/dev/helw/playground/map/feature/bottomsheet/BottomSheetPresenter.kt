package dev.helw.playground.map.feature.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.location.CityRepository
import dev.helw.playground.map.core.ui.screen.BottomSheetScreen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject

@Suppress("unused")
@Inject
@CircuitInject(BottomSheetScreen::class, AppScope::class)
class BottomSheetPresenter(
    private val cityRepository: CityRepository,
) : Presenter<BottomSheetState> {

    @Composable
    override fun present(): BottomSheetState {
        val city = cityRepository.selectedCityFlow.collectAsState()
        return BottomSheetState(city.value) {}
    }
}