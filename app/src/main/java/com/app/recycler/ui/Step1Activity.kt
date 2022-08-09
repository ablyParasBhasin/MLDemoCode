package com.app.recycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.dashboard.DashboardData
import com.app.recycler.models.step1.CommonData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_reporting_form2.*
import org.json.JSONObject
import retrofit2.Response

class Step1Activity : BaseActivity(), ResponseHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        ivBack.setOnClickListener {
            onBackPressed()
        }
        getEstates()
    }  fun getEstates() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.getEstates(API_TAG.GET_ESTATES, jsonObject, this)
        }catch (ex:Exception){

        }

    }
    fun getEstateDistrict() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
//            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.getEstateDistrict(API_TAG.GET_ESTATES_DISTRICT, jsonObject, this)
        }catch (ex:Exception){

        }

    }
    fun save_step1_data() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.saveStep1Data(API_TAG.SAVE_STEP_1_DATA, jsonObject, this)
        }catch (ex:Exception){

        }

    }



    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_ESTATES -> {
               var count = response?.body() as BaseResponseArray<CommonData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                    getEstateDistrict()
                } else
                    showDialog(count.msg, true)
            }
            API_TAG.GET_ESTATES_DISTRICT -> {
               var count = response?.body() as BaseResponseArray<CommonData>
                if (count.status.equals(Constants.API_SUCCESS)) {

                } else
                    showDialog(count.msg, true)
            }
            API_TAG.SAVE_STEP_1_DATA -> {
               var count = response?.body() as BaseResponse<*>
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
}