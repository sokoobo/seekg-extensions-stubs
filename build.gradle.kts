plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.dokkatoo) apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}