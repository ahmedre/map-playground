package dev.helw.playground.map.feature.bottomsheet.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.helw.playground.map.feature.bottomsheet.model.City

@Composable
fun City(city: City, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Text(city.name)
        Text(city.country)
    }
}