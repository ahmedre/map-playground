package dev.helw.playground.map

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
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
                    val backStack = rememberSaveableBackStack(root = MapWrapperScreen)
                    val navigator = rememberCircuitNavigator(backStack)
                    NavigableCircuitContent(navigator, backStack)
                }
            }
        }
    }
}