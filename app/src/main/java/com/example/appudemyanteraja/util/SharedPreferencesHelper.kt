package com.example.appudemyanteraja.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {

    companion object {
        private const val PREF_TIME = "pref_time"
        private var pref: SharedPreferences? = null
        private val LOCK = Any()

        @Volatile private var instance: SharedPreferencesHelper? = null
        operator fun invoke (context: Context): SharedPreferencesHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPreferencesHelper {
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    fun saveUpdateTime(time: Long) {
        pref?.edit(commit = true) {putLong(PREF_TIME, time)}
    }

}