package com.example.timerkt

import com.example.timerkt.common.ThreadChecker
import com.example.timerkt.timer.Timer
import com.example.timerkt.timer.TimerImpl
import org.junit.Test
import kotlin.concurrent.thread

/**
 * @author ivangolovacev
 */
class TimerImplTest {

    val threadChecker = object : ThreadChecker {
        override val isMainThread: Boolean
            get() = true
        override val isWorkerThread: Boolean
            get() = false
    }

    @Test
    fun test() {
        thread {
            val timerImpl = TimerImpl(30, threadChecker)
            timerImpl.observe(object : Timer.Observer {
                override fun timeLost(fullTime: Long, timeLost: Long) {
                    println("fullTime: $fullTime")
                    println("timeLost: $timeLost")
                }
            })
            timerImpl.play()
            Thread.sleep(1000)
            timerImpl.stop()
            Thread.sleep(1000)
            timerImpl.play()
        }
        Thread.sleep(5000)
    }
}