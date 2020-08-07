package com.example.passauth.common

import android.app.Activity
import android.content.Context
import android.util.Log

object PersistencyManager {
    fun persistPassword(activity: Activity, key: String, password: String) {
        val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        with (sharedPreferences.edit()) {
            putString(key, password)
            commit()
        }
    }

    fun getPasswords(activity: Activity): Map<String, Any?> {
        val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.all.toMap()
    }

    fun clearPersistence(activity: Activity) {
        val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}