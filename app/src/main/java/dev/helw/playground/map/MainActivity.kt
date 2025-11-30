package dev.helw.playground.map

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import dev.helw.playground.map.core.ui.theme.MapPlaygroundTheme
import dev.helw.playground.map.di.AppGraph
import dev.helw.playground.map.feature.mapscreen.MapWrapperScreen
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.createGraphFactory

class MainActivity : ComponentActivity() {
    @Inject
    private lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val graph = createGraphFactory<AppGraph.Factory>().create(application)
        graph.inject(this)

        enableEdgeToEdge()
        setContent {
            MapPlaygroundTheme {
                CircuitCompositionLocals(circuit) {
                    CircuitContent(MapWrapperScreen, Modifier.fillMaxSize())
                }
            }
        }
    }
}