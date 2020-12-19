package com.example.timerkt

import android.app.Application
import com.example.timerkt.common.di.appModule
import com.example.timerkt.rounds.di.roundsModule
import org.koin.core.context.startKoin

/**
 * @author ivangolovacev
 */
class TimerKtApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, roundsModule)
        }
    }
}