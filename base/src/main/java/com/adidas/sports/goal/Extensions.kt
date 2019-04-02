package com.adidas.sports.goal

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.view.MotionEvent
import android.view.View

@Suppress("UNCHECKED_CAST")
fun  <T : View> T.setTouchWithAnim(listener: (T) -> Unit){
    setOnTouchListener { v, event ->
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                AnimatorSet().apply {
                    duration = 300L
                    playTogether(
                            ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.2f),
                            ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.2f))
                    start()
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                AnimatorSet().apply {
                    duration = 300L
                    playTogether(
                            ObjectAnimator.ofFloat(v, "scaleX", 1.2f, 1.0f),
                            ObjectAnimator.ofFloat(v, "scaleY", 1.2f, 1.0f))
                    start()
                }
                if (clickEnable()) {
                    listener(v as T)
                }
            }
        }
        return@setOnTouchListener true
    }
}

fun <T : View> T.withTrigger(delay: Long = 600): T {
    triggerDelay = delay
    return this
}

@Suppress("UNCHECKED_CAST")
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener {

    if (clickEnable()) {
        block(it as T)
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : View> T.clickWithTrigger(time: Long = 600, block: (T) -> Unit){
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it as T)
        }
    }
}

private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else 0
    set(value) {
        setTag(1123460103, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else -1
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}


fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Double.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.sp2px(): Int = (this * Resources.getSystem().displayMetrics.scaledDensity + 0.5F).toInt()


val Int.dp: Int
    get() = this

val Int.px: Int
    get() = this

val Int.sp2px: Int
    get() = this

fun String.isNumber(): Boolean = Regex("^[0-9]*$").matches(this)

fun Boolean.visibleOrGone(): Int = if (this) View.VISIBLE else View.GONE

fun Boolean?.visibleOrGone(): Int = if (this == true) View.VISIBLE else View.GONE

fun Boolean.visibleOrGone(action: () -> Unit): Int =
        if (this) {
            action()
            View.VISIBLE
        } else View.GONE

fun Boolean?.visibleOrGone(action: () -> Unit): Int =
        if (this == true) {
            action()
            View.VISIBLE
        } else View.GONE

fun Boolean.visibleOrNot(): Int = if (this) View.VISIBLE else View.GONE

