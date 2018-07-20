package com.privalia.test

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.os.Build
import android.support.v7.app.AppCompatActivity
import com.andrognito.flashbar.Flashbar
import com.andrognito.flashbar.anim.FlashAnim
import org.reactivestreams.Publisher

/**
 * @author ZIaDo on 7/19/18.
 */
fun Any.hasLollipop(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}

fun Any.hasM(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun AppCompatActivity.showErrorFlashBar(message: String, duration: Long = 500): Flashbar.Builder {
    return Flashbar.Builder(this)
            .gravity(Flashbar.Gravity.BOTTOM)
            .duration(duration)
            .title("Error!")
            .message(message)
            .showOverlay()
            .enterAnimation(FlashAnim.with(this)
                    .animateBar()
                    .duration(750)
                    .alpha()
                    .overshoot())
            .exitAnimation(FlashAnim.with(this)
                    .animateBar()
                    .duration(400)
                    .accelerateDecelerate())
            .icon(R.drawable.ic_error)
            .iconColorFilterRes(R.color.colorAccent)
            .iconAnimation(FlashAnim.with(this)
                    .animateIcon()
                    .pulse()
                    .alpha()
                    .duration(750)
                    .accelerate())
            .enableSwipeToDismiss()
}

fun String.getYear() = this.substringBefore("-")