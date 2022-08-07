package com.app.recycler.applications

import android.app.Application
import com.app.recycler.apinetworks.Constants
import com.app.recycler.models.dashboard.DashboardData
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.models.login.LoginData
import com.app.recycler.ui.PrefConstants

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initUserInfo()
    }

    fun initUserInfo() {
        var serilizableUserData = DataManager.instance.getSharedPrefs(this).getString(
            Constants.USER_DATA)
        if (serilizableUserData?.isNotEmpty()!!)
            DataManager.instance.userData = LoginData.create(serilizableUserData)
        DataManager.instance.token =
            DataManager.instance.getSharedPrefs(this).getString(PrefConstants.TOKEN).toString()

    }

}
