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
            when (radio) {
                radio_yes -> {
                    // some code
                    lay_chb.visibility=View.VISIBLE
                    layedt.visibility=View.VISIBLE
                }
                radio_no -> {
                    // some code
                    layedt.visibility=View.GONE
                    lay_chb.visibility=View.VISIBLE
                }
            }
        }
    }



    fun getGridList(): ArrayList<DummyData> = arrayListOf(
        DummyData(getString(R.string.txtchb_external), R.drawable.total_survey,""),
        DummyData(getString(R.string.txtchb_training), R.drawable.total_survey,""),
        DummyData(getString(R.string.txtchb_workshop), R.drawable.total_survey,""),
        DummyData(getString(R.string.txtchb01), R.drawable.total_survey,""),
        DummyData(getString(R.string.txtchb02), R.drawable.in_progress,""),
        DummyData(getString(R.string.txtchb03), R.drawable.completed,""),
        DummyData(getString(R.string.txtchb04), R.drawable.delayed,""),
        DummyData(getString(R.string.txtchb05), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb06), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb07), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb08), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb09), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb10), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb11), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb12), R.drawable.all_risk,""),
        DummyData(getString(R.string.txtchb13), R.drawable.all_risk,""),
        DummyData(getString(R.string.txt_chekbox17), R.drawable.all_risk,""),
        DummyData(getString(R.string.unmute), R.drawable.all_risk,""),
    )

    override fun clickIten(pos: Int) {
        println("pos = [${pos}]")
    }
}