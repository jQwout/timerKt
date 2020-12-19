package com.example.timerkt.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.timerkt.R
import com.example.timerkt.rounds.RoundsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainFabListener, MainMenuListener {

    private val mainMenuController: MainMenuController by lazy {
        MainMenuController(this)
    }

    private val mainFabController: MainFabController by lazy {
        MainFabController(fab)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, RoundsFragment())
            .commit()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(mainMenuController.menuResource).isVisible = true
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.findItem(mainMenuController.menuResource).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun setListener(block: () -> Unit) {
        mainFabController.setListener(block)
    }

    override fun changeType(type: MainMenuType) {
        invalidateOptionsMenu()
        mainMenuController.changeMenuType(type)
    }
}