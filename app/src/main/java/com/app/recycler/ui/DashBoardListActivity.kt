package com.app.recycler.ui


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.step1.CommonData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_dash_board_list.*
import org.json.JSONObject
import retrofit2.Response

class DashBoardListActivity : BaseActivity(), ResponseHandler {

    companion object{
        var InProgress=1
        var Rejected=2
        var Approved=3
        var Completed=4
        var ReportsPendingModification=5

        var status=1
    }
    var adapter:DashBoardListAdapter?=null
    var values: ArrayList<String>?=null

    var dataList = ArrayList<CommonData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_list)
        values= ArrayList()
        dataList=ArrayList()

        values?.add("InProgress")
        values?.add("Rejected")
        values?.add("Approved")
        values?.add("Completed")
        values?.add("ReportsPendingModification")

        setStatusSpinner(values!!)
        getAllDashBoardList()
    }


    fun getAllDashBoardList() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            val jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("user_id", DataManager.instance.userData?.id)
            jsonObject.put("status", status)
            jsonObject.put("start_limit", 0)
            jsonObject.put("end_limit", 15)
            DataManager.instance.getDashboardActivtiyList(API_TAG.GET_DASHBOARDACTIVITY, jsonObject, this)
        }catch (ex:Exception){

            println("Exception"+ex.printStackTrace())
        }

    }

    private fun setAdapter() {
        rv_dashBoard.layoutManager = LinearLayoutManager(this)
        adapter = DashBoardListAdapter(this,dataList)
        rv_dashBoard.adapter = adapter
        rv_dashBoard.setHasFixedSize(true)
    }

    fun setStatusSpinner(values: ArrayList<String>) {
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = arrayAdapter
        spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

                if(values[position].equals("InProgress" ,ignoreCase = true)){
                    status=InProgress
                }

                if(values[position].equals("Rejected",ignoreCase = true)){
                    status=Rejected
                }

                if(values[position].equals("Approved",ignoreCase = true)){
                    status=Approved
                }

                if(values[position].equals("Completed",ignoreCase = true)){
                    status=Completed
                }

                if(values[position].equals("ReportsPendingModification",ignoreCase = true)){
                    status=ReportsPendingModification
                }

                getAllDashBoardList()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_DASHBOARDACTIVITY -> {
                var count = response?.body() as BaseResponseArray<CommonData>

                if (count.status.equals(Constants.API_SUCCESS)) {

                    println("Ressssssspns = [${tag}], response = [${response}]")
                    dataList=count.data  as ArrayList<CommonData>
                    setAdapter()
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