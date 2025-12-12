package dev.helw.playground.map.feature.bottomsheet.city

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class CityPresenter(
    @Assisted private val screen: CityScreen,
    @Assisted private val onClearCity: () -> Unit
) : Presenter<CityScreen.CityState> {

    @Suppress("unused")
    @AssistedFactory
    fun interface Factory {
        fun create(screen: CityScreen, onClearCity: () -> Unit): CityPresenter
    }

    @Composable
    override fun present(): CityScreen.CityState {
        return CityScreen.CityState(screen.city) {
            if (it == CityScreen.Event.Close) {
                onClearCity()
            }
        }
    }
}