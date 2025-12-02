@file:OptIn(ExperimentalMaterial3Api::class)

package dev.helw.playground.map.feature.bottomsheet.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.helw.playground.map.feature.bottomsheet.R
import dev.helw.playground.map.feature.bottomsheet.common.City
import dev.zacsweers.metro.AppScope

@CircuitInject(CityListScreen::class, AppScope::class)
@Composable
fun CityList(state: CityListScreen.CityListState, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()

    LaunchedEffect(state.isReverseSort) {
        listState.animateScrollToItem(0)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(R.string.city_list_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(R.string.city_list_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SortRow(
            isReversed = state.isReverseSort,
            onToggle = { state.eventSink(CityListScreen.Event.ToggleSorting) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            state = listState,
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            items(items = state.cities, key = { it.id }) { city ->
                City(
                    city,
                    modifier = Modifier.clickable {
                        state.eventSink(CityListScreen.Event.SelectCity(city))
                    }
                )
            }
        }
    }
}

@Composable
private fun SortRow(
    isReversed: Boolean,
    onToggle: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 1.dp,
        shape = BottomSheetDefaults.ExpandedShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = stringResource(R.string.city_list_sort_label),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = stringResource(
                        if (isReversed) {
                            R.string.city_list_sorting_desc_reversed
                        } else {
                            R.string.city_list_sorting_desc_default
                        }
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Switch(
                checked = isReversed,
                onCheckedChange = { onToggle() }
            )
        }
    }
}
