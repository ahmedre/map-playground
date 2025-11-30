plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "dev.helw.playground.map.feature.mapscreen"

    compileSdk = 36
}

ksp {
    arg("circuit.codegen.mode", "metro")
}

metro {
    enabled = true
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":core:ui"))

    implementation(libs.circuit.foundation)
    implementation(libs.circuit.runtime)
    api(libs.circuit.codegen.annotations)
    ksp(libs.circuit.codegen)

    implementation(libs.maplibre)
}
