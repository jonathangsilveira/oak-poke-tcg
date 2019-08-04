package br.edu.jgsilveira.portfolio.oakpokedex.ext

import android.animation.Animator
import android.view.View

fun View.rotate(value: Float) {
    animate().apply {
        duration = context.integer(android.R.integer.config_shortAnimTime).toLong()
    }.rotation(value)
}

fun View.showIn(animator: Animator.AnimatorListener? = null) {
    visibility = View.VISIBLE
    alpha = 0f
    translationY = height.toFloat()
    animate().apply {
        duration = context.integer(android.R.integer.config_shortAnimTime).toLong()
        translationY(0f)
        alpha = 1f
        setListener(animator)
    }.start()
}

fun View.showOut(animator: Animator.AnimatorListener? = null) {
    visibility = View.VISIBLE
    alpha = 1f
    translationY = 0f
    animate().apply {
        duration = context.integer(android.R.integer.config_shortAnimTime).toLong()
        translationY = height.toFloat()
        alpha = 0f
        setListener(animator)
    }.start()
}