package com.app.recycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.recycler.R
import kotlinx.android.synthetic.main.fragment_reporting_form2.*

class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}