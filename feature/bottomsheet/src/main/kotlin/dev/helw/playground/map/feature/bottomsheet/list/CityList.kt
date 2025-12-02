package dev.helw.playground.map.feature.bottomsheet.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.feature.bottomsheet.R
import dev.helw.playground.map.feature.bottomsheet.common.City
import dev.zacsweers.metro.AppScope

@CircuitInject(CityListScreen::class, AppScope::class)
@Composable
fun CityList(state: CityListScreen.CityListState, modifier: Modifier = Modifier) {
    Column(modifier) {
        Row(modifier = Modifier.align(Alignment.End)) {
            Text(stringResource(R.string.reverse_sort))
            Checkbox(
                state.isReverseSort,
                onCheckedChange = { state.eventSink(CityListScreen.Event.ToggleSorting) }
            )
        }

        LazyColumn(Modifier.fillMaxWidth()) {
            items(items = state.cities, key = { it.id }) {
                City(
                    it,
                    modifier = Modifier
                        .clickable { state.eventSink(CityListScreen.Event.SelectCity(it)) }
                )
            }
        }
    }
}