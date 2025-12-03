plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "dev.helw.playground.map.feature.bottomsheet"

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
    implementation(project(":core:location"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.circuit.foundation)
    implementation(libs.circuit.runtime)
    api(libs.circuit.codegen.annotations)
    ksp(libs.circuit.codegen)

    implementation(libs.maplibre)
}
