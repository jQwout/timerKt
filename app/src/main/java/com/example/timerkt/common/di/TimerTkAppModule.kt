package com.example.timerkt.common.di

import com.example.timerkt.common.AndroidThreadChecker
import com.example.timerkt.common.ThreadChecker
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @author ivangolovacev
 */
val appModule = module {
    single { AndroidThreadChecker() } bind ThreadChecker::class
}