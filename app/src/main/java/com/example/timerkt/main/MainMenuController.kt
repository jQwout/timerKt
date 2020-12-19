package com.example.timerkt.main

import com.example.timerkt.R

/**
 * @author ivangolovacev
 */
interface MainMenuListener {
    fun changeType(type: MainMenuType)
}

class MainMenuController(val activity: MainActivity) {

    private var type : MainMenuType = MainMenuType.Save

    val menuResource get() = type.menuResource

    fun changeMenuType(type: MainMenuType) {
        this.type = type
    }
}

sealed class MainMenuType(val menuResource: Int) {
    object Edit : MainMenuType(R.id.action_edit)
    object Save : MainMenuType(R.id.action_save)
}