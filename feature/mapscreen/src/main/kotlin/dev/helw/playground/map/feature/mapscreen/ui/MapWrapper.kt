package dev.helw.playground.map.feature.mapscreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.feature.mapscreen.MapWrapperScreen
import dev.zacsweers.metro.AppScope
import kotlinx.coroutines.launch
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(MapWrapperScreen::class, AppScope::class)
@Composable
fun MapWrapper(state: MapWrapperScreen.MapState, modifier: Modifier) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetContent = {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.fillMaxWidth().height(128.dp), contentAlignment = Alignment.Center) {
                    Text("Swipe up to expand sheet")
                }
                Text("Sheet content")
                Button(
                    modifier =
                        Modifier.padding(bottom = 64.dp).focusProperties {
                            // Make sure the button is not keyboard focusable when it's offscreen.
                            canFocus =
                                scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded
                        },
                    onClick = { scope.launch { scaffoldState.bottomSheetState.partialExpand() } },
                ) {
                    Text("Click to collapse sheet")
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
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
    }
}