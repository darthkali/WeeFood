import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(KotlinPlugins.multiplatform)
    kotlin(KotlinPlugins.cocoapods)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.androidLibrary)
    id(Plugins.sqlDelight)
}

android {
    compileSdkVersion(Application.compileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosWeeFood/Podfile")
    }

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }


        val commonMain by getting {
            dependencies {
                implementation(Ktor.core)
                implementation(Ktor.clientSerialization)
                implementation(Kotlin.annotations)
                implementation(Kotlinx.datetime)
                implementation(Kotlinx.common)
                implementation(Kotlinx.serialization)
                implementation(SQLDelight.runtime)
                implementation(Koin.core)
                implementation(Koin.test)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(Kotlin.commonTest)
                implementation(Kotlin.annotations)
                implementation(Koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.android)
                implementation(SQLDelight.androidDriver)
                implementation(Kotlinx.android)
                implementation(Koin.test)
                implementation(Koin.testJunit4)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(Kotlin.jvm)
                implementation(Kotlin.junit)
                implementation(Ktor.android)
                implementation(SQLDelight.androidDriver)
                implementation(AndroidXTest.core)
                implementation(AndroidXTest.junit)
                implementation(AndroidXTest.runner)
                implementation(AndroidXTest.rules)
                implementation(Kotlinx.test)
                implementation(Robolectric.robolectric)
                implementation(Koin.android)
            }
        }


        val iosMain by getting {
            dependencies {
                implementation(Ktor.ios)
                implementation(SQLDelight.nativeDriver)
                implementation(Kotlinx.common) {
                    version {
                        strictly(Kotlinx.coroutines)
                    }
                }
            }
        }
    }
}

sqldelight {
    database("WeeFoodDatabase") {
        packageName = "de.darthkali.weefood.datasource.database"
        sourceFolders = listOf("sqldelight")
    }
}


