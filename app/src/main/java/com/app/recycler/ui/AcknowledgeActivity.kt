package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.dashboard.DashboardData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_reporting_form.*
import org.json.JSONObject
import retrofit2.Response


class AcknowledgeActivity : BaseActivity(), ResponseHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_reporting_form)
        btn_acknowledge.setOnClickListener{
            setAcknowledge()
        }
        btnStart_Activity.setOnClickListener {
            setAcknowledge()
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }
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
            API_TAG.SET_ACKNOWLEDGE -> {
                var count = response?.body() as BaseResponse<DashboardData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                    startActivity(Intent(this@AcknowledgeActivity, FormListingActivity::class.java))
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

}