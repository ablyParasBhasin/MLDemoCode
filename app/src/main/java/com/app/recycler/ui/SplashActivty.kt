package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.recycler.utility.ConstantMethod
import com.app.recycler.R
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.ui.signing.SigningActivity


class SplashActivty : AppCompatActivity() {

    var array: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        array = ArrayList()
        val timerThread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (ignored: InterruptedException) {
                } finally {
                    if (DataManager.instance.getSharedPrefs(this@SplashActivty)?.getString(PrefConstants.TOKEN).isNullOrEmpty())
                        startActivity(Intent(this@SplashActivty, SigningActivity::class.java))
                    else
                        startActivity(Intent(this@SplashActivty, MainAcivity::class.java))
                    finish()
                }
            }
        }
        timerThread.start()
    }


}