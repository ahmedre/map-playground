package dev.helw.playground.map.feature.bottomsheet.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.location.CityRepository
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@Suppress("unused")
@SingleIn(AppScope::class)
@CircuitInject(CityListScreen::class, AppScope::class)
@Inject
class CityListPresenter(
    private val cityRepository: CityRepository
) : Presenter<CityListScreen.CityListState> {

    private val reverseSorting = mutableStateOf(false)

    @Composable
    override fun present(): CityListScreen.CityListState {
        val cityList = remember { cityRepository.cities }

        val currentCityList = remember(reverseSorting.value) {
            if (reverseSorting.value) {
                cityList.reversed()
            } else {
                cityList
            }
        }

        return CityListScreen.CityListState(reverseSorting.value, currentCityList) { event ->
            when (event) {
                is CityListScreen.Event.SelectCity -> cityRepository.setSelectedCity(event.city)
                CityListScreen.Event.ToggleSorting -> reverseSorting.value = !reverseSorting.value
            }
        }
    }
}
