package com.example.timerkt

import com.example.timerkt.common.toMinutesAndSeconds
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun times_test(){
        val seconds = 193L
        val minAndSec = seconds.toMinutesAndSeconds()

        println("minutes :${minAndSec.first}")
        println("seconds :${minAndSec.second}")
    }
}