plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
}

android {
    namespace = "dev.helw.playground.map.core.di"

    compileSdk = 36
}

ksp {
    arg("circuit.codegen.mode", "metro")
}

metro {
    enabled = true
}

dependencies {
}


