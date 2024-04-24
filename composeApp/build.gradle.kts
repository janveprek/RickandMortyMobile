import com.android.build.gradle.internal.lint.AndroidLintAnalysisTask
import com.android.build.gradle.internal.lint.LintModelWriterTask
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kover)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)

            dependencies {
                implementation(libs.androidx.ui.test.junit4.android)
                debugImplementation(libs.androidx.ui.test.manifest)
            }
        }
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

//    targets.withType<KotlinNativeTarget> {
//        binaries.withType<Framework> {
//            linkerOpts.add("-lsqlite3")
//            isStatic = false
//            transitiveExport = false
//        }
//        binaries.withType<TestExecutable> {
//            linkerOpts.add("-lsqlite3")
//        }
//    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.mockk.android)
                implementation(libs.mockk.agent)
                implementation(libs.coroutines.test)
                implementation(libs.junit)
                implementation(libs.kotest.assertions.core)
                implementation(libs.turbine)
            }
        }

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.koin.android)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.animation)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kamel.image)
            // Ktor
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.coil)

            // Koin
            implementation(libs.koin.core)
//            implementation(libs.koin.compose)

            // Precompose
            api(libs.precompose)
            api(libs.precompose)
            api(libs.precompose.koin)
            api(libs.precompose.viewmodel)

            implementation(libs.napier)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
    }
}

tasks.withType<AndroidLintAnalysisTask>{
    dependsOn("copyFontsToAndroidAssets")
}

tasks.withType<LintModelWriterTask>{
    dependsOn("copyFontsToAndroidAssets")
}

koverReport {
    filters {
        excludes {
            classes("com.veprek.honza.rickandmorty.MainActivity*")
            classes("com.veprek.honza.rickandmorty.ComposableSingletons*")
            classes("com.veprek.honza.rickandmorty.app.*")
            classes("com.veprek.honza.rickandmorty.*.di.*")
            classes("com.veprek.honza.rickandmorty.character.data.*")
            classes("com.veprek.honza.rickandmorty.data.*")
            classes("com.veprek.honza.rickandmorty.design.*")
            classes("com.veprek.honza.rickandmorty.navigation.system*")
            classes("comveprekhonzarickandmorty.character.data.*")
            classes("rickandmortymobile.composeapp.generated.resources.*")
            annotatedBy("androidx.compose.runtime.Composable")
        }
    }
    verify {
        rule {
            isEnabled = true
            bound {
                minValue = 75
            }
        }
    }
}

android {
    namespace = "com.veprek.honza.rickandmorty"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
        applicationId = "com.veprek.honza"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName = "com.veprek.honza.rickandmorty.data"
        }
    }
}
