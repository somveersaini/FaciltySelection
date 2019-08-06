package com.sam.faciltyselection.binding

import com.sam.faciltyselection.R
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter


object BindingAdapters {


    @JvmStatic
    @BindingAdapter("goneUnless", "slideUp", requireAll = false)
    fun goneUnlessWithSlideUp(view: View, visible: Boolean, slideUp: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
        if (slideUp) {
            val bottomUp = AnimationUtils.loadAnimation(view.context, R.anim.slide_in_up)
            view.startAnimation(bottomUp)
        }
    }

    @JvmStatic
    @BindingAdapter("enableRotation")
    fun enableRotation(view: ImageView, enable: Boolean) {
        if (enable) {
            val objectAnimator = ObjectAnimator.ofFloat(view, View.ROTATION, 0.0f, 360.0f)
            objectAnimator.duration = 2000
            objectAnimator.repeatCount = Animation.INFINITE
            objectAnimator.start()
        }
    }

}