package com.example.timerkt.rounds.viewmodel

import androidx.lifecycle.ViewModel
import com.example.timerkt.rounds.model.RoundsTimersFactory
import com.example.timerkt.timer.Timer
import java.util.*

/**
 * @author ivangolovacev
 */
class RoundsViewModel(private val roundsTimersFactory: RoundsTimersFactory) : ViewModel() {

    val _times: MutableList<Timer> = mutableListOf()

    fun add(time: Long) {
        _times.add(roundsTimersFactory.create(time))
    }

    fun remove(index: Int) {
        _times.removeAt(index)
    }

    fun replace(from: Int, to: Int) {
        Collections.swap(_times, from, to)
    }

    fun start(position: Int) {

    }

    fun stop(position: Int) {

    }

    fun refresh(position: Int) {

    }
}