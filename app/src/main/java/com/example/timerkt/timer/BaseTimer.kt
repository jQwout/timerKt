package com.example.timerkt.timer

import androidx.annotation.MainThread
import com.example.timerkt.common.ThreadChecker
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.fixedRateTimer

/**
 * @author ivangolovacev
 */
interface Timer {

    fun play()

    fun stop()

    fun observe(observer: Observer)

    fun removeObserver(observer: Observer)

    interface Observer {

        fun timeLost(fullTime: Long, timeLost: Long)
    }
}

class TimerImpl(
    private val long: Long,
    private val threadChecker: ThreadChecker
) : Timer {

    private val countDownLatch = CountDownLatch(long.toInt())

    private val observers: MutableList<Timer.Observer> = mutableListOf()

    private var timer: java.util.Timer? = null

    @MainThread
    override fun play() {
        checkMainOrError()
        timer = fixedRateTimer(daemon = true, period = 1000) {
            if (countDownLatch.count == 0L) {
                stopCurrentTimer()
            } else {
                countDownLatch.countDown()
                observers.forEach {
                    it.timeLost(long, countDownLatch.count)
                }
            }
        }
    }

    @MainThread
    override fun stop() {
        checkMainOrError()
        stopCurrentTimer()
    }

    override fun observe(observer: Timer.Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Timer.Observer) {
        observers.remove(observer)
    }

    private fun stopCurrentTimer() {
        timer?.purge()
        timer?.cancel()
    }

    private fun checkMainOrError() {
        if (threadChecker.isWorkerThread) {
            throw IllegalStateException()
        }
    }
}