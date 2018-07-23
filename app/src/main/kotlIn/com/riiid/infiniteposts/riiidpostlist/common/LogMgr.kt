package com.riiid.infiniteposts.riiidpostlist.common

import android.util.Log

object LogMgr {
    fun getFunction(): String {
        val stringBuilder = StringBuilder("InfinitePosts/")
        var stackTrace: StackTraceElement? = null
        var simpleClassName = ""

        for (i in 5..7) {
            stackTrace = Thread.currentThread().stackTrace[i]
            simpleClassName = stackTrace.className.substringAfterLast(".").substringBefore("$")
            if (simpleClassName != this::class.java.simpleName) break
        }

        stringBuilder.append("$simpleClassName > " +
                "${stackTrace?.methodName}:${stackTrace?.lineNumber}")


        return stringBuilder.toString()
    }

    fun d(message: String = "called") {
        Log.d(getFunction(), message)
    }

    fun e(message: String = "called") {
        Log.e(getFunction(), message)
    }
}