package com.example.kotlin_rxjava.utils

import android.content.res.Resources

fun Float.toDp() : Float = (this / Resources.getSystem().displayMetrics.density)
fun Float.toPx() : Float = (this * Resources.getSystem().displayMetrics.density)