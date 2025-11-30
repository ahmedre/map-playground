package dev.helw.playground.map.feature.mapscreen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.feature.mapscreen.MapWrapperScreen
import dev.zacsweers.metro.AppScope
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView

@CircuitInject(MapWrapperScreen::class, AppScope::class)
@Composable
fun MapWrapper(state: MapWrapperScreen.MapState, modifier: Modifier) {
    val mapLibreMap = remember { mutableStateOf<MapLibreMap?>(null) }

    AndroidView(
        factory = { context ->
            MapView(context).also { mapView ->
                mapView.getMapAsync { map ->
                    mapLibreMap.value = map
                }
            }
        },
        modifier = modifier
    )

    LaunchedEffect(mapLibreMap.value, state.styleUrl) {
        val url = state.styleUrl
        if (url != null) {
            mapLibreMap.value?.setStyle(url)
        }
    }
}