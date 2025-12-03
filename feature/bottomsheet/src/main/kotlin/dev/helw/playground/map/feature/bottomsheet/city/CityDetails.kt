@file:OptIn(ExperimentalMaterial3Api::class)

package dev.helw.playground.map.feature.bottomsheet.city

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.feature.bottomsheet.R
import dev.zacsweers.metro.AppScope

@CircuitInject(CityScreen::class, AppScope::class)
@Composable
fun CityDetails(state: CityScreen.CityState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = stringResource(R.string.city_details_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )

        City(state.city)

        FilledTonalButton(
            onClick = { state.eventSink(CityScreen.Event.Close) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.city_details_back_to_list))
        }
    }
}
