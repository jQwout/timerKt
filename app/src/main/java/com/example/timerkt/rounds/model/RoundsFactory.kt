package com.example.timerkt.rounds.model

import com.example.timerkt.common.ThreadChecker
import com.example.timerkt.timer.Timer
import com.example.timerkt.timer.TimerImpl

/**
 * @author ivangolovacev
 */
class RoundsTimersFactory(private val threadChecker: ThreadChecker) {

    fun create(times: List<Long>): List<Timer> = times.map {
        create(it)
    }

    fun create(time: Long): Timer = TimerImpl(time, threadChecker)
}