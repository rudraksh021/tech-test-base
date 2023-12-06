package com.cleanarch.clean.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Created by Aanal Shah on 05/12/2023
 */
class ResourceProvider(context: Context) {

    private val appContext = context.applicationContext

    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(appContext, resId)
    }
}