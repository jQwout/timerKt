package com.example.timerkt.main

import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * @author ivangolovacev
 */
interface MainFabListener {

    fun setListener(block: () -> Unit)
}

class MainFabController(private val floatingActionButton: FloatingActionButton) {

    fun setListener(block: () -> Unit) {
        floatingActionButton.setOnClickListener {
            block()
        }
    }

}