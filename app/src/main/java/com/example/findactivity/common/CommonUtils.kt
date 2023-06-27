package com.example.findactivity.common

import android.os.Handler
import android.os.Looper

fun safeRunOnUiThread(r: () -> Unit) {
    runOnUiThread(r = {
        safeRun(r)
    })
}

fun safeRun(run: () -> Unit) {
    try {
        run()
    }catch (e: Exception){
        LoggerUtil.printStackTraceOnlyInDebug(e)
    }
}

fun runOnUiThread(r: () -> Unit) {
    Handler(Looper.getMainLooper()).post(r)
}
