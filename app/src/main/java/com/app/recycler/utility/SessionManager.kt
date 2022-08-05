package com.app.recycler.utility;

import android.content.Context
import android.content.SharedPreferences

class SessionManager(_context: Context) {

    // Shared Preferences
    internal var pref: SharedPreferences

    // Editor for Shared preferences
    internal var editor: SharedPreferences.Editor

    // Shared pref mode
    internal var PRIVATE_MODE = 0


    // Get Login State
    val isLoggedIn: Boolean get() = pref.getBoolean(IS_LOGIN, false)

    fun getUserName():String?{
       return pref.getString(KEY_NAME, null)
    }

    fun getUserId():String?{
        return pref.getString(KEY_ID, "0")
    }

    fun getToken():String?{
        return pref.getString(KEY_TOKEN, null)
    }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
        editor.apply()
    }

    fun setDriverStatus(name: Int){
        editor.putInt(DRIVER_STATUS, name)
        editor.commit()
    }

    fun getDriverStatus():Int{
        return pref.getInt(DRIVER_STATUS, 0)
    }

    fun saveUserName(name: String){
        editor.putString(KEY_NAME, name)
        editor.commit()
    }

    fun isFCMToken():Boolean{
        return pref.getBoolean(FCM_TOKEN, false)
    }

    fun setFCMToken(flag: Boolean){
        editor.putBoolean(FCM_TOKEN, flag)
        editor.commit()
    }

    fun getCurrencySymbol():String?{
        return pref.getString(CURRENCY, "")
    }

    fun setCurrencySymbol(symbol: String){
        editor.putString(CURRENCY, symbol)
        editor.commit()
    }


    /**
     * Create login session
     */
    fun createSession(
        name: String,
        email: String,
        token: String,
        isLogin:Boolean,
        device_type: String,
        profileImg: String,
        id: String,
        status: String,
        password: String,
        phone: String,
        notification: Int) {

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, isLogin)
        editor.putString(PASSWORD, password)
        // Storing name in pref
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_PROFILEIMG, profileImg)
        // Storing email in pref
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_ID, id)
        editor.putString(KEY_STATUS, status)
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_DEVICE_TYPE, device_type)
        editor.putString(KEY_PHONE, phone)
        editor.putInt(KEY_NOTIFICATION, notification)

        // commit changes
        editor.commit()
    }

    /**
     * Clear session details
     */
    fun logoutUser() {
        editor.clear()
        editor.commit()

    }

    companion object {

        private val PREF_NAME = "LOGIN_INFO"

        // All Shared Preferences Keys
        private val IS_LOGIN = "IsLoggedIn"
        private val KEY_NAME = "NAME"
        private val KEY_EMAIL = "EMAIL"
        private val KEY_ID = "user_id"
        private val KEY_STATUS = "status"
        private val KEY_PROFILEIMG = "profileImg"
        private val KEY_TOKEN = "TOKEN"
        private val KEY_DEVICE_TYPE = "device_type"
        private val KEY_PHONE = "phone"
        private  val KEY_NOTIFICATION = "notification"
        private val PASSWORD = "Password"
         val DRIVER_STATUS = "driver_status"
        private val FCM_TOKEN = "fcm_token"
        private val CURRENCY = "currency"

    }
}
