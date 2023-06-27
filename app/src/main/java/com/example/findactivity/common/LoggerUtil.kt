package com.example.findactivity.common

import com.example.findactivity.BuildConfig

//TODO - ADD CRASHALYTICS TO REGISTER ANY CRASHS ON THE APP
object LoggerUtil {
    fun printStackTraceOnlyInDebug(throwable: Throwable, canLog: Boolean = true){
        if(canLog)
//            SafeCrashlyticsUtil.logException(throwable)
        if(BuildConfig.DEBUG)
            throwable.printStackTrace()
    }

    fun printlnOnlyInDebug(message: String){
        if(BuildConfig.DEBUG){
            println(message)
        }
    }
}