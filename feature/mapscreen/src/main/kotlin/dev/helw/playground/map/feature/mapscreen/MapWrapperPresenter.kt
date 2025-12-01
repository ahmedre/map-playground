package dev.helw.playground.map.feature.mapscreen

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope

@CircuitInject(MapWrapperScreen::class, AppScope::class)
class MapWrapperPresenter : Presenter<MapWrapperScreen.MapState> {

    @Composable
    override fun present(): MapWrapperScreen.MapState {
        return MapWrapperScreen.MapState { }
    }
}