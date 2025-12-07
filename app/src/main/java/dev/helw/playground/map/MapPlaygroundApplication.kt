package dev.helw.playground.map

import android.app.Application
import org.maplibre.android.MapLibre

class MapPlaygroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MapLibre.getInstance(this)
    }
}