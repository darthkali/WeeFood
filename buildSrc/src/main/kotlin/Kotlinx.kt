object Kotlinx {
    private const val kotlinxDatetimeVersion = "0.1.1"
    val coroutines = "1.5.0-native-mt"

    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${kotlinxDatetimeVersion}"
    val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}"
    val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${coroutines}"
}