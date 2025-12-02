package dev.helw.playground.map.feature.mapscreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.di.ApplicationContext
import dev.helw.playground.map.core.ui.MapStyle
import dev.helw.playground.map.core.ui.screen.SettingsScreen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

class MapWrapperPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    @param:ApplicationContext private val appContext: Context
): Presenter<MapWrapperScreen.MapState> {

    @CircuitInject(MapWrapperScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): MapWrapperPresenter
    }

    @Composable
    override fun present(): MapWrapperScreen.MapState {
        val mapTheme = remember { mutableStateOf(MapStyle.DAY) }
        val styleUrl = remember(mapTheme.value) {
            mutableStateOf(
                when (mapTheme.value) {
                    MapStyle.DAY -> appContext.getString(R.string.map_style_day)
                    MapStyle.NIGHT -> appContext.getString(R.string.map_style_night)
                }
            )
        }

        return MapWrapperScreen.MapState(styleUrl.value) {
            if (it == MapWrapperScreen.MapState.Event.Settings) {
                navigator.goTo(SettingsScreen)
            } else if (it == MapWrapperScreen.MapState.Event.SwapTheme) {
                mapTheme.value = if (mapTheme.value == MapStyle.DAY) MapStyle.NIGHT else MapStyle.DAY
            }
        }
    }
}