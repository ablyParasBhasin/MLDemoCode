package com.app.recycler.ui

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.DummyData
import kotlinx.android.synthetic.main.activity_step2.*


class Step2Activity : AppCompatActivity(), ListingItemClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step2)

        recycler_checkbox?.layoutManager = LinearLayoutManager(this)

        val adapter1 = CheckboxListingAdapter(this, getGridList(), this)
        // Setting the Adapter with the recyclerview
        recycler_checkbox?.adapter = adapter1
        //recycler_checkbox

        daily_weekly_button_view.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            /*if (checkedId==1){
                radio_yes
            }*/
            when (radio) {
                radio_yes -> {
                    // some code
                    radio_yes.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_enable_bg))
                    radio_yes.setTextColor(resources.getColor(R.color.white))

                    radio_no.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_white_bg))
                    radio_no.setTextColor(resources.getColor(R.color.black))


                    lay_chb.visibility=View.VISIBLE
                    layedt.visibility=View.VISIBLE
                }
                radio_no -> {
                    // some code
                    radio_no.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_enable_bg))
                    radio_no.setTextColor(resources.getColor(R.color.white))

                    radio_yes.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_white_bg))
                    radio_yes.setTextColor(resources.getColor(R.color.black))

                    layedt.visibility=View.GONE
                    lay_chb.visibility=View.VISIBLE
                }
            }
        }
    }



    fun getGridList(): ArrayList<DummyData> = arrayListOf(
        DummyData(getString(R.string.txt_1), R.drawable.total_survey,""),
        DummyData(getString(R.string.txt_2), R.drawable.total_survey,""),
        DummyData(getString(R.string.txt_3), R.drawable.total_survey,""),
        DummyData(getString(R.string.txt_4), R.drawable.total_survey,""),
        DummyData(getString(R.string.txt_5), R.drawable.in_progress,""),
        DummyData(getString(R.string.txt_6), R.drawable.completed,""),
        DummyData(getString(R.string.txt_7), R.drawable.delayed,""),
        DummyData(getString(R.string.txt_8), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_9), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_10), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_11), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_12), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_13), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_14), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_15), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_16), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_17), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_18), R.drawable.all_risk,""),
    )

    override fun clickIten(pos: Int) {
        println("pos = [${pos}]")
    }
}