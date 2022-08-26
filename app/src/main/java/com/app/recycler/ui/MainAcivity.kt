package com.app.recycler.ui


/*import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response*/

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.recyclerview.widget.GridLayoutManager
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.applications.MyApp
import com.app.recycler.databinding.ActivityMainBinding
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.DummyData
import com.app.recycler.models.dashboard.DashboardData
import com.app.recycler.models.step1.CommonData
import com.app.recycler.utility.GridSpacingItemDecoration
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import retrofit2.Response


class MainAcivity : BaseActivity(), ListingItemClick, ResponseHandler {

    var arrayList: ArrayList<DummyData>? = null
    private val client = OkHttpClient()
    val databinding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApp).initUserInfo()
       /* SingleShotLocationProvider.requestSingleUpdate(
            this
        ) { location -> Log.d("Location", "my location is $location") }*/

        try {
            val str_name= DataManager.instance.getSharedPrefs(this)?.getString(PrefConstants.USER_NAME).toString()
            tvUserName.setText("Hello, "+str_name)

            getCounts()

            btnStartActivity.setOnClickListener {
               /* val intent = Intent(this@MainAcivity, AcknowledgeActivity::class.java)
                startActivity(intent)*/
                setAcknowledge()
            }
            btnStart_Activity.setOnClickListener {
                /*val intent = Intent(this@MainAcivity, AcknowledgeActivity::class.java)
                startActivity(intent)*/
                setAcknowledge()
            }
         /*   if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
                return;
            }
            */
        }catch (ex:Exception){

        }

        txt_goDashBoard.setOnClickListener {

            val intent = Intent(this@MainAcivity, DashBoardListActivity::class.java)
            startActivity(intent)

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

    private fun gridViewLsting() {

        /*  if Display GridViewListing View.*/
        recyclerview?.layoutManager = GridLayoutManager(this, 3)

        //val spanCount = 1 // 3 columns
        val spacing = 5 // 50px
        val includeEdge = false
        recyclerview?.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                spacing,
                includeEdge
            )
        )


        // This will pass the ArrayList to our Adapter
        val adapter1 = GridViewListingAdapter(this, getGridList(), this)
        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter1
    }


    fun getGridList(): ArrayList<DummyData> =
        arrayListOf(DummyData("Total Reports Approved", R.drawable.total_survey,count.data.approved),
            DummyData("Reports In Progress", R.drawable.in_progress,count.data.inProgress),
            DummyData("Reports Completed", R.drawable.completed,count.data.completed),
//        DummyData("To be Sync", R.drawable.to_be_synced,count.data.rejected),
            DummyData("Pending Modification", R.drawable.delayed,count.data.rejectForModification),
            DummyData("Rejected", R.drawable.all_risk,count.data.rejected))




    override fun clickItem(pos: Int) {
      /*  println("pos = [${getSingleList().get(pos).discription}]")
        
        val intent = Intent(this@MainAcivity, DescritionActivity::class.java)
        intent.putExtra("description", getSingleList().get(pos).discription);
        intent.putExtra("title", getSingleList().get(pos).title);
        startActivity(intent)*/
    }

    override fun clickChildItem(pos: Int) {

    }

    fun getCounts() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token",DataManager.instance.token)
            DataManager.instance.dashboardCounts(API_TAG.DASHBOARD_COUNT, jsonObject, this)
        }catch (ex:Exception){

        }

    }
    var count=BaseResponse<DashboardData>()

    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.DASHBOARD_COUNT -> {
                 count = response?.body() as BaseResponse<DashboardData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                    gridViewLsting()
                } else
                    showDialog(count.msg, true)
            }
            API_TAG.SET_ACKNOWLEDGE -> {
                var count = response?.body() as BaseResponse<CommonData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                    DataManager.instance.commonData=count.data
                    startActivity(Intent(this, FormListingActivity::class.java))
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