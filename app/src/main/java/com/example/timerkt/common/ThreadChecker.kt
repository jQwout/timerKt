package com.example.timerkt.common

import android.os.Looper

/**
 * @author ivangolovacev
 */
interface ThreadChecker {
    val isMainThread: Boolean
    val isWorkerThread: Boolean
}

class AndroidThreadChecker : ThreadChecker {

    override val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    override val isWorkerThread: Boolean
        get() = !isMainThread
}