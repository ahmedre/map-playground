package dev.helw.playground.map.feature.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.location.City
import dev.helw.playground.map.core.ui.screen.BottomSheetScreen
import dev.helw.playground.map.feature.bottomsheet.city.CityPresenter
import dev.helw.playground.map.feature.bottomsheet.city.CityScreen
import dev.helw.playground.map.feature.bottomsheet.list.CityListPresenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject

@Suppress("unused")
@Inject
@CircuitInject(BottomSheetScreen::class, AppScope::class)
class BottomSheetPresenter(
    private val cityPresenterFactory: CityPresenter.Factory,
    private val cityListPresenterFactory: CityListPresenter.Factory,
) : Presenter<BottomSheetState> {

    @Composable
    override fun present(): BottomSheetState {
        val city = remember { mutableStateOf<City?>(null) }
        val resetCityLambda = { city.value = null }
        val cityPresenter = remember(city.value) {
            val currentCity = city.value
            if (currentCity != null) {
                cityPresenterFactory.create(CityScreen(currentCity), resetCityLambda)
            } else {
                null
            }
        }

        val haveCity = remember { derivedStateOf { city.value != null } }
        val cityListPresenter = remember(haveCity.value) {
            if (haveCity.value) {
                null
            } else {
                cityListPresenterFactory.create { city.value = it }
            }
        }

        return BottomSheetState(
            cityListPresenter?.present(),
            cityPresenter?.present()
        ) {}
    }
}