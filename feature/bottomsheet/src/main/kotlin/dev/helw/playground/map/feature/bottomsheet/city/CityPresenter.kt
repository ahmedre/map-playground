package dev.helw.playground.map.feature.bottomsheet.city

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.location.City
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class CityPresenter(
    @Assisted private val city: City,
    @Assisted private val onClearCity: () -> Unit
) : Presenter<CityScreen.CityState> {

    @AssistedFactory
    fun interface Factory {
        fun create(city: City, onClearCity: () -> Unit): CityPresenter
    }

    @Composable
    override fun present(): CityScreen.CityState {
        return CityScreen.CityState(city) {
            if (it == CityScreen.Event.Close) {
                onClearCity()
            }
        }
    }
}