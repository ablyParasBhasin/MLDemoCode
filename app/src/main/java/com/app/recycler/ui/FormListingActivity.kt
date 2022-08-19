package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.app.recycler.R
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_reporting_form2.*


class FormListingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_reporting_form2)
        setData()
        ivBack.setOnClickListener {
            onBackPressed()
        }
        lay_item1.setOnClickListener {
            startActivityForResult(
                Intent(this@FormListingActivity, Step1Activity::class.java),1000)
        }
        lay_item2.setOnClickListener {
            startActivityForResult(Intent(this@FormListingActivity, Step2Activity::class.java),2000)
        }
        lay_item3.setOnClickListener {
            startActivityForResult(Intent(this@FormListingActivity, Step2Activity::class.java),2000)
        }
    }

    fun setData() {
        title1.text = getString(R.string.generalInfoCat)
        title2.text = getString(R.string.stackInfoCat)
        title3.text = getString(R.string.KpiInfoCat)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("requestCode = [${requestCode}], resultCode = [${resultCode}], data = [${data}]")
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            item1Forward.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_check_circle_24))
        }else if (requestCode == 2000 && resultCode == RESULT_OK) {
            item2Forward.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_check_circle_24))
        }else if (requestCode == 3000 && resultCode == RESULT_OK) {
            item3Forward.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_check_circle_24))
        }
    }

}