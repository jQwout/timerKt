package com.example.timerkt.rounds.di

import com.example.timerkt.rounds.model.RoundsTimersFactory
import com.example.timerkt.rounds.viewmodel.RoundsViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

/**
 * @author ivangolovacev
 */
val roundsModule = module {
    single { RoundsTimersFactory(get()) }
    viewModel { RoundsViewModel(get()) }
}