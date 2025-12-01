package dev.helw.playground.map.feature.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.core.ui.screen.SettingsScreen
import dev.helw.playground.map.feature.settings.Event
import dev.helw.playground.map.feature.settings.R
import dev.helw.playground.map.feature.settings.SettingsState
import dev.zacsweers.metro.AppScope

@CircuitInject(SettingsScreen::class, AppScope::class)
@Composable
fun Settings(settingsState: SettingsState, modifier: Modifier = Modifier) {
    Box(
       contentAlignment = Alignment.Center,
       modifier = modifier.fillMaxSize().background(Color.White)
    ) {
        TextButton( { settingsState.eventSink(Event.CloseSettings) }) {
            Text(stringResource(R.string.close))
        }
    }
}