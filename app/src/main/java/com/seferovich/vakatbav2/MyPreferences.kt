package com.seferovich.vakatbav2

import android.content.Context

class MyPreferences(context: Context) {

    private val PREFS_NAME = "my_prefs"
    private val MY_VALUE_KEY = "city"
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var myValue: Int
        get() = prefs.getInt(MY_VALUE_KEY, 77) // 0 is the default value
        set(value) = prefs.edit().putInt(MY_VALUE_KEY, value).apply()
}