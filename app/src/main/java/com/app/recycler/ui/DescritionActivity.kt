package com.app.recycler.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.recycler.R
import kotlinx.android.synthetic.main.activity_descrition.*


class DescritionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descrition)



        val i = intent
        val descrition = i.getStringExtra("description")
        val title = i.getStringExtra("title")
        txt_information.setText(descrition)

        txt_title.setText(title)

        btn_ok.setOnClickListener {
            finish()
        }

        img_back.setOnClickListener {
            finish()
        }


    }
}