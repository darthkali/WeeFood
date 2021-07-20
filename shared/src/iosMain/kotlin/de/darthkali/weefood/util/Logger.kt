package de.darthkali.weefood.util

actual class Logger actual constructor(
    private val className: String
) {

    actual fun log(msg: String) {
        if(!BuildConfig().isDebug()){
            // Crashlytics or whatever
        }
        else{
            println("className: $msg")
        }
    }
}