package dev.helw.playground.map.feature.mapscreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.helw.playground.map.core.di.ApplicationContext
import dev.helw.playground.map.core.ui.MapStyle
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject

@CircuitInject(MapWrapperScreen::class, AppScope::class)
class MapWrapperPresenter @Inject constructor(
    @param:ApplicationContext private val appContext: Context
) : Presenter<MapWrapperScreen.MapState> {

    @Composable
    override fun present(): MapWrapperScreen.MapState {
        val mapStyle = remember { mutableStateOf(MapStyle.DAY) }
        val mapUrl = remember { mutableStateOf<String?>(null) }

        LaunchedEffect(mapStyle.value) {
            val resource = when (mapStyle.value) {
                MapStyle.DAY -> R.string.map_style_day
                MapStyle.NIGHT -> R.string.map_style_night
            }
            mapUrl.value = appContext.getString(resource)
        }
        return MapWrapperScreen.MapState(mapUrl.value) { }
    }
}