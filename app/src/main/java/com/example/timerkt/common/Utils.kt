package com.example.timerkt.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat

/**
 * @author ivangolovacev
 */
// TIME
fun Long.toMinutesAndSeconds(): Pair<Int, Int> {
    val min: Int = (this / 60).toInt()
    val sec: Int = this.rem(60).toInt()
    return min to sec
}

fun Int.toTwoDigitShow(): String {
    val str = this.toString()
    return when (str.length) {
        1 -> {
            "0$str"
        }
        2 -> {
            str
        }
        else -> {
            throw IllegalStateException("invalid format")
        }
    }
}

fun Pair<Int, Int>.toStringFormat(): String {
    return "${first.toTwoDigitShow()} : ${second.toTwoDigitShow()}"
}

// UI
fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Context.dpToPx(dp: Int): Float {
    val displayMetrics = getResources().getDisplayMetrics();
    return ((dp * displayMetrics.density) + 0.5).toFloat()
}

fun Context.requireDrawable(@DrawableRes res: Int): Drawable {
    return ContextCompat.getDrawable(this, res) as Drawable
}