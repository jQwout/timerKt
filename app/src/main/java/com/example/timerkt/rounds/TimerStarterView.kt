package com.example.timerkt.rounds

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.timerkt.R
import com.example.timerkt.common.BaseAnimator
import com.example.timerkt.common.dpToPx
import com.example.timerkt.common.toMinutesAndSeconds
import com.example.timerkt.common.toStringFormat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.timer_item.view.*

/**
 * @author ivangolovacev
 */
class TimerStarterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), LayoutContainer {

    private var currentType = _LABEL_TYPE

    override val containerView: View

    val pickerView get() = containerView.rlTime

    val labelView get() = containerView.rlTimeView

    val commandView  get() = containerView.rlCommand

    var time: Long
        get() = containerView.rlTime.times
        set(value) {
            containerView.rlTime.setTimes(value)
        }

    var onTimeApplyClicker = {}

    init {
        LayoutInflater.from(context).inflate(R.layout.timer_item, this)

        containerView = findViewById(R.id.rlContainer)

        setTextToLabel()

        labelView.setOnClickListener {
            changeType()
        }
    }

    fun changeType() {
        currentType = if (currentType == _LABEL_TYPE) {
            animateLabelToPicker()
            commandView.setImageResource(R.drawable.ic_baseline_check_24)
            commandView.setOnClickListener {
                setTextToLabel()
                changeType()
            }
            _PICKER_TYPE
        } else {
            animatePickerToLabel()
            commandView.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            _LABEL_TYPE
        }
    }

    fun animateLabelToPicker() {
        val path = Path().apply {
            moveTo(labelView.x, labelView.y)
            lineTo(
                (containerView.right.toFloat() / 2 - (labelView.width / 2)),
                labelView.y
            )
        }
        ObjectAnimator.ofFloat(rlTimeView, View.X, View.Y, path).apply {
            duration = 300
            start()
            addListener(object : BaseAnimator() {
                override fun onAnimationEnd(animation: Animator?) {
                    labelView.visibility = View.GONE
                    pickerView.visibility = View.VISIBLE
                }
            })
        }
    }

    fun animatePickerToLabel() {
        labelView.visibility = View.VISIBLE
        pickerView.visibility = View.GONE
        val path = Path().apply {
            moveTo(
                (containerView.right.toFloat() / 2 - (labelView.width / 2)),
                labelView.y
            )
            lineTo(context.dpToPx(32), labelView.y)
        }
        ObjectAnimator.ofFloat(rlTimeView, View.X, View.Y, path).apply {
            duration = 300
            start()
            addListener(object : BaseAnimator() {
                override fun onAnimationStart(animation: Animator?) {
                    labelView.visibility = View.VISIBLE
                    pickerView.visibility = View.GONE
                }
            })
        }
    }

    fun setTextToLabel() {
        labelView.text = time.toMinutesAndSeconds().toStringFormat()
    }
}

private const val _LABEL_TYPE = 0
private const val _PICKER_TYPE = 1