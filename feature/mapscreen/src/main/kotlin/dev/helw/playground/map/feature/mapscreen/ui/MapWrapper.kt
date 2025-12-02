package dev.helw.playground.map.feature.mapscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
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
            val isChecked = remember { mutableStateOf(false) }

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

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 4.dp)
                    .background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))
            ) {
                TextButton(
                    onClick = { state.eventSink(MapWrapperScreen.MapState.Event.SwapTheme) }
                ) {
                    Text(stringResource(R.string.swap_theme))
                }

                TextButton(
                    onClick = { state.eventSink(MapWrapperScreen.MapState.Event.Settings) }
                ) {
                    Text(stringResource(R.string.settings))
                }

                Checkbox(isChecked.value, onCheckedChange =  { isChecked.value = it })
            }

            LaunchedEffect(mapLibreMap.value, state.styleUrl) {
                mapLibreMap.value?.setStyle(state.styleUrl)
            }

        }
    }
}