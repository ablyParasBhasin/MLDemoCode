package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.app.recycler.R
import com.app.recycler.apinetworks.DataManager
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_reporting_form2.*

/*
{"login_token":"YXB6ZG1panVreXNy","activity_id":"6,4,12"}
{"code":200,"data":
    {"activity_id":"4","activity_name":"Training on Gender Sensitisation","category_name":"Change Agents and Advocates","questions":
        {"activity_12":{"activity_12_start_date":"activity_12_start_date","activity_12_end_date":"activity_12_end_date","activity_12_file":"activity_12_file","activity_212_file":"activity_212_file","activity_12_comments_observations":"activity_12_comments_observations","activity_12_curr_status":
            {"question":"activity_12_curr_status","options":"Completed,In progress,Delayed,At risk"},"question":
            {"activity_12_quest_1":"How Many Trainings Were Conducted By The Identified Change Agents?","activity_12_quest_1_male":"How Many Community Members Reached?","activity_12_quest_1_female":"How Many Community Members Reached?","activity_12_quest_2":"How Many Trainings Were Conducted By The Identified Agents Of Change- Adolescent?","activity_12_quest_2_male":"How Many Community Members Reached?","activity_12_quest_2_female":"How Many Community Members Reached?"}},"activity_6":{"activity_6_start_date":"activity_6_start_date","activity_6_end_date":"activity_6_end_date","activity_6_file":"activity_6_file","activity_26_file":"activity_26_file","activity_6_comments_observations":"activity_6_comments_observations","activity_6_curr_status":{"question":"activity_6_curr_status","options":"Completed,In progress,Delayed,At risk"},"question":{"activity_6_quest_1":"How Many Trainings Were Conducted For The Estate Staff?","activity_6_quest_1_male":"How Many Estate Staff Attended The Session?","activity_6_quest_1_female":"How Many Estate Staff Attended The Session?"}},"activity_4":{"activity_4_start_date":"activity_4_start_date","activity_4_end_date":"activity_4_end_date","activity_4_file":"activity_4_file","activity_24_file":"activity_24_file","activity_4_comments_observations":"activity_4_comments_observations","activity_4_curr_status":{"question":"activity_4_curr_status","options":"Completed,In progress,Delayed,At risk"},"question":{"activity_4_quest_1":"How Many Trainings Were Conducted For The Identified Agents Of Change- Women Leaders?","activity_4_quest_1_woman":"How Many Women Leaders Attended The Training?","activity_4_quest_2":"How Many Trainings Were Conducted For The Identified Agents Of Change- Adolescent?","activity_4_quest_2_boys":"How Many Estate Staff Attended The Training?","activity_4_quest_2_girls":"How Many Estate Staff Attended The Training?","activity_4_quest_3":"How Many Trainings Were Conducted For The Identified Agents Of Change- VDP And Other Government Representatives?","activity_3_quest_3":"How Many Trainings Were Conducted For The Estate IC?"}}}}}
*/
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
                Intent(this@FormListingActivity, Step1Activity::class.java), 1000
            )
        }
        lay_item2.setOnClickListener {
            startActivityForResult(
                Intent(this@FormListingActivity, Step2Activity::class.java),
                2000
            )
        }
        lay_item3.setOnClickListener {
            if (DataManager.instance.activitiesTobeSent.isEmpty()) {
                showDialog(getString(R.string.alert),getString(R.string.stackholder_error),true,false)
                return@setOnClickListener
            }
            startActivityForResult(
                Intent(
                    this@FormListingActivity,
                    Step3CatListingActivity::class.java
                ), 3000
            )
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
            item1Forward.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_check_circle_24
                )
            )
        } else if (requestCode == 2000 && resultCode == RESULT_OK) {
            item2Forward.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_check_circle_24
                )
            )
        } else if (requestCode == 3000 && resultCode == RESULT_OK) {
            item3Forward.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_check_circle_24
                )
            )
        }
    }

}