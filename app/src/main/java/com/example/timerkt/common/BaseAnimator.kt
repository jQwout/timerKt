package com.example.timerkt.common

import android.animation.Animator

/**
 * @author ivangolovacev
 */
abstract class BaseAnimator : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {

    }

    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
        super.onAnimationEnd(animation, isReverse)
    }

    override fun onAnimationEnd(animation: Animator?) {

    }

    override fun onAnimationCancel(animation: Animator?) {

    }

    override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
        super.onAnimationStart(animation, isReverse)
    }

    override fun onAnimationStart(animation: Animator?) {

    }
}