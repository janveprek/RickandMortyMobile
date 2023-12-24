plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    id("dev.icerock.mobile.multiplatform-resources") version "0.23.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.0.3"
}
