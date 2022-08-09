package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.DummyData
import com.app.recycler.models.dashboard.DashboardData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_reporting_form2.*
import org.json.JSONObject
import retrofit2.Response


class FormListingActivity : BaseActivity(), ResponseHandler, ListingItemClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_reporting_form2)
        singleListing()
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }
    private fun singleListing() {
        /* if Display singleListing View. Then Uncomment below code*/

        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        val adapter1 = FormCategories(this, getSingleList(), this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter1

    }
    fun getSingleList(): ArrayList<DummyData> = arrayListOf(
        DummyData(getString(R.string.generalInfoCat), 0,"This is a mountain  1"),
        DummyData(getString(R.string.activityInfoCat), 0,"This is a mountain  2"),
        DummyData(getString(R.string.KpiInfoCat), 0,"This is a mountain  3"),
        )

    fun setAcknowledge() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.setAcknwoledge(API_TAG.SET_ACKNOWLEDGE, jsonObject, this)
        }catch (ex:Exception){

        }

    }


    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.DASHBOARD_COUNT -> {
                var count = response?.body() as BaseResponse<DashboardData>
                if (count.status.equals(Constants.API_SUCCESS)) {

                } else
                    showDialog(count.msg, true)
            }
        }
    }

    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }

    override fun clickIten(pos: Int) {
        if(pos==0)
        startActivity(Intent(this@FormListingActivity, Step1Activity::class.java))
       //showToast("Coming soon.... ")
    }

}