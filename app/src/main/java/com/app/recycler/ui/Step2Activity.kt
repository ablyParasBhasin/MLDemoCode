package com.app.recycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    fun getGridList(): ArrayList<DummyData> = arrayListOf(
        DummyData("Total Reports Approved", R.drawable.total_survey,""),
        DummyData("Reports In Progress", R.drawable.in_progress,""),
        DummyData("Reports Completed", R.drawable.completed,""),
//        DummyData("To be Sync", R.drawable.to_be_synced,count.data.rejected),
        DummyData("Pending Modification", R.drawable.delayed,""),
        DummyData("Rejected", R.drawable.all_risk,"")
    )

    override fun clickIten(pos: Int) {
        println("pos = [${pos}]")
    }
}