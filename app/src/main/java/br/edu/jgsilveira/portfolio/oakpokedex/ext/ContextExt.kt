package br.edu.jgsilveira.portfolio.oakpokedex.ext

import android.content.Context
import androidx.annotation.IntegerRes

fun Context.integer(@IntegerRes res: Int): Int = resources.getInteger(res)