package dev.helw.playground.map.feature.bottomsheet.city

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.feature.bottomsheet.R
import dev.helw.playground.map.feature.bottomsheet.common.City
import dev.zacsweers.metro.AppScope

@CircuitInject(CityScreen::class, AppScope::class)
@Composable
fun CityDetails(state: CityScreen.CityState, modifier: Modifier = Modifier) {
    Column(modifier) {
        TextButton(onClick = { state.eventSink(CityScreen.Event.Close) }) {
            Text(stringResource(R.string.back))
        }
        City(state.city)
    }
}