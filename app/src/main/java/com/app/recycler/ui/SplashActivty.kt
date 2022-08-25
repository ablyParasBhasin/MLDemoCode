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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SplashActivty : AppCompatActivity() {

    var array: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        array = ArrayList()
        compareApiTimeCurrentTime()
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

    fun compareApiTimeCurrentTime(){

        // var apiDateTime="2022-08-23 12:35:34"
        // var apiDateTime="2022-08-21 12:35:34"

        var apiDateTime=DataManager.instance.getSharedPrefs(this@SplashActivty).getString(PrefConstants.Login_Token_Expire_Datetime)

        if(!apiDateTime.isNullOrEmpty()){
            val sdf = SimpleDateFormat("yyyy-M-dd hh:mm:ss")
            val currentDate = sdf.format(Date())
            System.out.println("C DATE is  "+currentDate)

            val loginDateTime = sdf.parse(apiDateTime)
            val currentDateTime = sdf.parse(currentDate)


            //comparing dates
            if(loginDateTime.compareTo(currentDateTime) > 0) {
                System.out.println("loginDateTime  comes after currentDateTime");
            }
            else if(loginDateTime.compareTo(currentDateTime) < 0) {

                System.out.println("loginDateTime  comes before currentDateTime");
                DataManager.instance.getSharedPrefs(this).clearPreferrence()

                startActivity(Intent(this@SplashActivty, SigningActivity::class.java))

            }else if(loginDateTime.compareTo(currentDateTime) == 0){
                System.out.println("Both dates are equal");
            }
        }
    }
}