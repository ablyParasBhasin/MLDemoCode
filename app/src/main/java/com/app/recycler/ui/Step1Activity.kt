package com.app.recycler.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.step1.CommonData
import com.app.recycler.utility.SingleShotLocationProvider
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_spinner.*
import org.json.JSONObject
import retrofit2.Response
import java.lang.reflect.Field


class Step1Activity : BaseActivity(), ResponseHandler {
    var loc:Location?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        getLocation()
        ivBack.setOnClickListener {
            onBackPressed()

        }
        getEstates()
        btnSaveDraft.setOnClickListener {

            startActivity(Intent(this@Step1Activity, Step2Activity::class.java))
        }
        btnSave.setOnClickListener {
            saveStep1Data()
        }
    }
    fun getLocation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {
                getLatlng()
            } else {
                requestPermission()
            }
        } else {
            getLatlng()
        }
    }
    private fun requestPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        getLatlng()
                        //   showPictureDialog()  this method will open the camera and gallery with popup
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        Toast.makeText(this@Step1Activity, "Permissions Error", Toast.LENGTH_SHORT)
                            .show()
                        // Utility.showShortToast(this, getString(R.string.error_permissions), true)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>, token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(this@Step1Activity, "Permissions Error", Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()
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

    fun getEstateDistrict(estateDistrictId: String) {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject = JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("estate_id", estateDistrictId)
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
            getLocation()
            // {“ip_address”:”10.246.21.897”,”latitude”:”2424234.44”,”longitude”:”43444334.34”,”ime_no”:”iyiuy877”,”model”:”note 5 pro”,”os_name”:”android”,”os_version”:”10.2”}’

            var jsonObject = JSONObject()
            val fields = VERSION_CODES::class.java.fields
            var codeName = "UNKNOWN"
            fields.filter { it.getInt(VERSION_CODES::class) == Build.VERSION.SDK_INT }
                .forEach { codeName = it.name }
            DataManager.instance.jsonObjectDeviceDetails.put("ip_address",getIpv4HostAddress())
            DataManager.instance.jsonObjectDeviceDetails.put("ime_no", Settings.Secure.getString(contentResolver,Settings.Secure.ANDROID_ID))
            DataManager.instance.jsonObjectDeviceDetails.put("model",Build.MODEL)
            DataManager.instance.jsonObjectDeviceDetails.put("os_name",codeName)
            DataManager.instance.jsonObjectDeviceDetails.put("os_version",Build.VERSION.SDK_INT)
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("user_id", DataManager.instance.userData?.id)
            jsonObject.put("activity_id", DataManager.instance.commonData?.activity_id)
            jsonObject.put("form_start_date_time", DataManager.instance.commonData?.form_start_date_time)
            jsonObject.put("tea_estate", selectedEstate)
            jsonObject.put("tea_estate_district", selectedEstateDist)
            jsonObject.put("step1_status", "1")
            jsonObject.put("app_device_details",DataManager.instance.jsonObjectDeviceDetails)
            DataManager.instance.saveStep1Data(API_TAG.SAVE_STEP_1_DATA, jsonObject, this)
        } catch (ex: Exception) {
ex.printStackTrace()
        }

    }

    var estateResponseData = BaseResponseArray<CommonData>()
    var estateDistResponseData = BaseResponseArray<CommonData>()
    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_ESTATES -> {
                estateResponseData = response?.body() as BaseResponseArray<CommonData>
                if (estateResponseData.status.equals(Constants.API_SUCCESS)) {
                    if(estateResponseData.data!=null) {
                        var values = ArrayList<String>()
                        values.add(getString(R.string.spinner_estate_selection))
                        for (item in estateResponseData.data.indices) {
                            values.add(estateResponseData.data[item].estateName)
                        }
                        setEstateSpinner(values)
                    }
                } else
                    showDialog(estateResponseData.msg, true)
            }
            API_TAG.GET_ESTATES_DISTRICT -> {
                 estateDistResponseData = response?.body() as BaseResponseArray<CommonData>
                if (estateDistResponseData.status.equals(Constants.API_SUCCESS)) {
                    var values = ArrayList<String>()
                    values.add(getString(R.string.location_of_the_tea_estate))
                    for (item in estateDistResponseData.data.indices) {
                        values.add(estateDistResponseData.data[item].estate_district_name)
                    }
                    setEstateDistrictSpinner(values)
                } else
                    showDialog(estateDistResponseData.msg, true)
            }
            API_TAG.SAVE_STEP_1_DATA -> {
                var reposneData = response?.body() as BaseResponse<CommonData>
                if (reposneData.status.equals(Constants.API_SUCCESS)) {
                    showToast(reposneData.data.msg)
                    setResult(RESULT_OK)
                    finish()
                } else
                    showDialog(reposneData.msg, true)
            }
        }
    }


    fun setEstateSpinner(values: ArrayList<String>) {
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstate.adapter = arrayAdapter
        spinnerEstate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {

                    for (item in estateResponseData.data) {
                        if (values[position] == item.estateName) {
                            getEstateDistrict(item.id)
                            selectedEstate=item.id
                            break
                        }
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
var selectedEstate=""
var selectedEstateDist=""

    fun setEstateDistrictSpinner(values: ArrayList<String>) {
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstateDistrict.adapter = arrayAdapter
        spinnerEstateDistrict.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    for (item in estateDistResponseData.data) {
                        if (values[position] == item.estate_district_name) {
                            selectedEstateDist=item.id
                            break
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }
}