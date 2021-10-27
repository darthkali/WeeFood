package de.darthkali.weefood.util

import de.darthkali.weefood.BuildConfig

actual class BuildConfig {
    actual fun isDebug() = BuildConfig.DEBUG
    actual fun isAndroid() = true
}
