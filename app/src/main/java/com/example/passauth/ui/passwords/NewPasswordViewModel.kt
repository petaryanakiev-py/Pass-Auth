package com.example.passauth.ui.passwords

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.passauth.common.PersistencyManager

class NewPasswordViewModel: ViewModel() {
    private var appName: String? = null
    private var password: String? = null

    private var activity: Activity? = null

    fun setActivity(activity: Activity) {
        this.activity = activity
    }

    fun persistPassword(appName: String, password: String): Boolean {
        if (appName == "" || password == "") {
            return false
        }

        this.appName = appName
        this.password = password

        Log.i("persistPassword", appName + "; " + password)
        PersistencyManager.persistPassword(activity!!, appName, password)
        return true
    }
}