package dev.helw.playground.map.feature.mapscreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import dev.helw.playground.map.core.ui.screen.BottomSheetScreen
import dev.helw.playground.map.feature.mapscreen.MapWrapperScreen
import dev.helw.playground.map.feature.mapscreen.R
import dev.zacsweers.metro.AppScope
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.camera.CameraUpdate
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(MapWrapperScreen::class, AppScope::class)
@Composable
fun MapWrapper(state: MapWrapperScreen.MapState, modifier: Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 400.dp,
        sheetContainerColor = Color.White,
        sheetContent = {
            CircuitContent(BottomSheetScreen, Modifier.fillMaxWidth())
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            val mapLibreMap = remember { mutableStateOf<MapLibreMap?>(null) }
            val isStyleLoaded = remember { mutableStateOf(false) }

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

            ElevatedCard(
                modifier = Modifier
                    .padding(innerPadding)
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 8.dp)
                    .widthIn(min = 100.dp, max = 150.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.quick_controls_title),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    MapControlButton(
                        label = stringResource(R.string.swap_theme),
                        iconRes = R.drawable.ic_swap_theme,
                        onClick = { state.eventSink(MapWrapperScreen.MapState.Event.SwapTheme) }
                    )

                    MapControlButton(
                        label = stringResource(R.string.settings),
                        iconRes = R.drawable.ic_settings_tune,
                        onClick = { state.eventSink(MapWrapperScreen.MapState.Event.Settings) },
                    )

                }
            }

            LaunchedEffect(mapLibreMap.value, state.selectedCity) {
                val mapLibreMap = mapLibreMap.value

                if (mapLibreMap != null) {
                    // wait until the active style finishes loading before changing the camera.
                    snapshotFlow { isStyleLoaded.value }
                        .first { it }

                    val cameraUpdate: CameraUpdate = state.selectedCity?.let { selectedCity ->
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                latitude = selectedCity.latitude,
                                longitude = selectedCity.longitude
                            ),
                            10.0
                        )
                    } ?: CameraUpdateFactory.newCameraPosition(
                        CameraPosition.Builder()
                            .target(LatLng(0.0, 0.0))
                            .zoom(1.3)
                            .tilt(0.0)
                            .build()
                    )

                    mapLibreMap.easeCamera(cameraUpdate)
                }
            }

            LaunchedEffect(mapLibreMap.value, state.styleUrl) {
                val mapLibreMap = mapLibreMap.value
                if (mapLibreMap != null) {
                    isStyleLoaded.value = false
                    mapLibreMap.setStyle(state.styleUrl) {
                        isStyleLoaded.value = true
                    }
                }
            }

        }
    }
}