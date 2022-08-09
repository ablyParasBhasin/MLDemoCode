package com.app.recycler.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.step1.CommonData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_spinner.*
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
        btnSaveDraft.setOnClickListener {

        }
        btnSave.setOnClickListener {
            saveStep1Data()
        }
    }

    fun getEstates() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject = JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.getEstates(API_TAG.GET_ESTATES, jsonObject, this)
        } catch (ex: Exception) {

        }

    }

    fun getEstateDistrict() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject = JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("estate_id", DataManager.instance.token)
            DataManager.instance.getEstateDistrict(API_TAG.GET_ESTATES_DISTRICT, jsonObject, this)
        } catch (ex: Exception) {

        }

    }

    fun saveStep1Data() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject = JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.saveStep1Data(API_TAG.SAVE_STEP_1_DATA, jsonObject, this)
        } catch (ex: Exception) {

        }

    }

    var estateResponseData = BaseResponseArray<CommonData>()
    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_ESTATES -> {
                estateResponseData = response?.body() as BaseResponseArray<CommonData>
                if (estateResponseData.status.equals(Constants.API_SUCCESS)) {
                    var commonData = CommonData()
                    commonData.id = "0"
                    commonData.estateName = getString(R.string.spinner_estate_selection)
                    estateResponseData.data.set(0, commonData)
                    setEstateSpinner(estateResponseData.data)
                    getEstateDistrict()
                } else
                    showDialog(estateResponseData.msg, true)
            }
            API_TAG.GET_ESTATES_DISTRICT -> {
                var reposneData = response?.body() as BaseResponseArray<CommonData>
                if (reposneData.status.equals(Constants.API_SUCCESS)) {

                } else
                    showDialog(reposneData.msg, true)
            }
            API_TAG.SAVE_STEP_1_DATA -> {
                var reposneData = response?.body() as BaseResponse<*>
                if (reposneData.status.equals(Constants.API_SUCCESS)) {
                    showToast(reposneData.toString())
                    setResult(RESULT_OK)
                    finish()
                } else
                    showDialog(reposneData.msg, true)
            }
        }
    }


    fun setEstateSpinner(data: List<CommonData>) {
        val arrayAdapter =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, data)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstate.adapter = arrayAdapter as SpinnerAdapter?
        spinnerEstate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tutorialsName: String = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }


    fun setEstateDistrictSpinner() {
        /*val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstateDistrict.setAdapter(arrayAdapter)
        spinnerEstateDistrict.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tutorialsName: string = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })*/
    }

    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }
}