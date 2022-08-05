package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.recycler.utility.ConstantMethod
import com.app.recycler.R
import com.app.recycler.ui.signing.SigningActivity


class SplashActivty : AppCompatActivity() {

    var array: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        array = ArrayList()
        if (ConstantMethod.isConnected(this@SplashActivty) != 0) {
            Handler().postDelayed({

                val intent = Intent(this@SplashActivty, SigningActivity::class.java)
                startActivity(intent)
                 finish()

            }, 3000) // 3000 is the delayed time in milliseconds.


        } else {
            Toast.makeText(this@SplashActivty, getString(R.string.app_no_internet), Toast.LENGTH_SHORT).show()
        }
    }


}