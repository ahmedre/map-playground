plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "dev.helw.playground.map.core.location"

    compileSdk = 36
}

metro {
    enabled = true
}

dependencies {
    implementation(libs.androidx.annotation)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}


