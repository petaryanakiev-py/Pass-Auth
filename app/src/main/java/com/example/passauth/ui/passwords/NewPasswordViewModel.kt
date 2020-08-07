package com.example.passauth.ui.passwords

import android.util.Log
import androidx.lifecycle.ViewModel

class NewPasswordViewModel : ViewModel() {
    private var appName: String? = null
    private var password: String? = null

    fun persistPassword(appName: String, password: String): Boolean {
        if (appName == "" || password == "") {
            return false
        }

        this.appName = appName
        this.password = password

        Log.i("persistPassword", appName + "; " + password)

        return true
    }
}