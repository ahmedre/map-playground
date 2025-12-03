package dev.helw.playground.map.feature.bottomsheet.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.location.City
import dev.helw.playground.map.core.location.CityRepository
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class CityListPresenter(
    private val cityRepository: CityRepository,
    @Assisted private val onCitySelected: (city: City) -> Unit
) : Presenter<CityListScreen.CityListState> {

    @AssistedFactory
    fun interface Factory {
        fun create(onCitySelected: (city: City) -> Unit): CityListPresenter
    }

    @Composable
    override fun present(): CityListScreen.CityListState {
        val reverseSorting = remember { mutableStateOf(false) }
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
                is CityListScreen.Event.SelectCity -> onCitySelected(event.city)
                CityListScreen.Event.ToggleSorting -> reverseSorting.value = !reverseSorting.value
            }
        }
    }
}
