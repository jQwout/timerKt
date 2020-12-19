package com.example.timerkt.rounds

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.timerkt.R
import com.shawnlin.numberpicker.NumberPicker

/**
 * @author ivangolovacev
 */
class TimePickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val minutesPicker: NumberPicker
    private val secondsPicker: NumberPicker

    val times : Long get() = ((minutesPicker.value * 60) + (secondsPicker.value)).toLong()

    init {
        LayoutInflater.from(context).inflate(R.layout.time_picker, this)

        val attributes = context.obtainStyledAttributes(
            attrs, R.styleable.TimePickerView, defStyleAttr, 0
        )

        val wheelItemCount = attributes.getInt(
            R.styleable.TimePickerView_wheelCount, 3
        )

        val enable = attributes.getBoolean(
            R.styleable.TimePickerView_enable, true
        )

        attributes.recycle()

        minutesPicker = findViewById(R.id.tpMinutes)
        secondsPicker = findViewById(R.id.tpSeconds)

        minutesPicker.wheelItemCount = wheelItemCount
        secondsPicker.wheelItemCount = wheelItemCount

        minutesPicker.isEnabled = enable
        secondsPicker.isEnabled = enable

        minutesPicker.formatter = NumberPicker.getTwoDigitFormatter()
        secondsPicker.formatter = NumberPicker.getTwoDigitFormatter()
    }

    fun setTimes(timeSeconds: Long) {
        val minutes = timeSeconds / 60
        val seconds = timeSeconds.rem(60)

        minutesPicker.value = minutes.toInt()
        secondsPicker.value = seconds.toInt()
    }

}