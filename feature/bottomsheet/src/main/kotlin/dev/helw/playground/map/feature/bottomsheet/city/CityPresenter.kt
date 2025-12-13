package dev.helw.playground.map.feature.bottomsheet.city

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.location.CityRepository
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class CityPresenter(
    @Assisted private val screen: CityScreen,
    private val cityRepository: CityRepository
) : Presenter<CityScreen.CityState> {

    @Suppress("unused")
    @CircuitInject(CityScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: CityScreen): CityPresenter
    }

    @Composable
    override fun present(): CityScreen.CityState {
        return CityScreen.CityState(screen.city) {
            if (it == CityScreen.Event.Close) {
                cityRepository.clearSelectedCity()
            }
        }
    }
}