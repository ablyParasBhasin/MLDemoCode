package com.app.recycler.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.interfaces.ListingItemDataClick
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.step1.CommonData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setp2_expand.*
import org.json.JSONObject
import retrofit2.Response


/* getallcategory {"code":200,"data":[{"id":"3","category_name":"Change Agents and
Advocates"},{"id":"5","category_name":"Community"},{"id":"1","category_name":"External Stakeholder
Consultaion"},{"id":"2","category_name":"Management and Staff"},{"id":"4","category_name":"Workers"}]}
*/

/* getactivity {"code":200,"data":[{"id":"6","activity_name":"Engaging staff to plan awareness and regular activities to prevent VAW in
the workplace","category_name":"Change Agents and Advocates"},{"id":"4","activity_name":"Training on Gender
Sensitisation","category_name":"Change Agents and Advocates"},{"id":"5","activity_name":"Training on POSH, DV, POCSO,
legal support and justice system","category_name":"Change Agents and Advocates"}]}
*/
/* step2submission     "user_id": "1*****",
    "activity_id": "123***",
    "step2_ans": ‘{"question_6":"1,2,3","question_7":"4,5"}’,  question 6 is categories ids and question 7 is activities ids with comma separated
    "step2_status": NULL   Pass step2_status value 1 if user click on submit button
*/

class Step2Activity : BaseActivity(), ListingItemClick,ListingItemDataClick, ResponseHandler
     {
    var categoryList = ArrayList<CommonData>()
    var mainActivityList = ArrayList<CommonData>()
    var activityList = ArrayList<CommonData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setp2_expand)
        getAllCategories()
        btnSave.setOnClickListener {
            val categories = ArrayList<String>()
            val activities = ArrayList<String>()

            for(item in categoryList){
                if(item.isCatChecked){
                    categories.add(item.id)
                }
            }
            for(item in mainActivityList){
                if(item.isSubCatChecked){
                    activities.add(item.id)
                }
            }
            categoriesTobeSent=categories.toString().replace("[", "")
                .replace("]", "").replace(" ", "")
            activitiesTobeSent=activities.toString().replace("[", "")
                .replace("]", "").replace(" ", "")
           saveStep2Data()
        }
    }
         var categoriesTobeSent=""
         var activitiesTobeSent=""

    var adapter:ParentAdapter?=null
    private fun setCategoryView() {
        rvCat.layoutManager = LinearLayoutManager(this)
        adapter = ParentAdapter(this,categoryList,this)
        rvCat.adapter = adapter
        rvCat.setHasFixedSize(true)
    }
    fun getAllCategories() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            DataManager.instance.getAllCategories(API_TAG.GET_CATEGORIES, jsonObject, this)
        }catch (ex:Exception){

        }

    }
    fun getActivity(categoryId: String) {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("category_id", categoryId)
            DataManager.instance.getActivity(API_TAG.GET_ACTIVITY, jsonObject, this)
        }catch (ex:Exception){

        }

    }
    fun saveStep2Data() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            var jsonObject2= JSONObject()
            jsonObject2.put("question_6",categoriesTobeSent)
            jsonObject2.put("question_7",activitiesTobeSent)
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("user_id", DataManager.instance.userData?.id)
            jsonObject.put("step2_ans", jsonObject2)
            jsonObject.put("step2_status", "1")
            jsonObject.put("activity_id", DataManager.instance.commonData?.activity_id)
            DataManager.instance.saveStep2Data(API_TAG.SAVE_STEP_2_DATA, jsonObject, this)
        }catch (ex:Exception){

        }

    }


    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_CATEGORIES -> {
                var count = response?.body() as BaseResponseArray<CommonData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                    categoryList= count.data as ArrayList<CommonData>
                    setCategoryView()
                } else
                    showDialog(count.msg, true)
            }
            API_TAG.GET_ACTIVITY -> {
                var count = response?.body() as BaseResponseArray<CommonData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                     activityList= count.data as ArrayList<CommonData>
                     mainActivityList.addAll(activityList)
                     adapter?.setChildAdapter(this,activityList,this,categoryList[catPosition].categoryName)
                } else
                    showDialog(count.msg, true)
            }
            API_TAG.SAVE_STEP_2_DATA -> {
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

    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }
    var catPosition=-1
    override fun clickItem(pos: Int) {
        catPosition=pos
        getActivity(categoryList[pos].id)
    }

    override fun clickChildItem(pos: Int) {

    }

    override fun clickChildItem(pos:Int, value: String,boolean: Boolean,id:String) {
       /*for(item in mainActivityList){
           if(id==item.id){
               showToast("Activity Id is"+item.activityName)
               break
           }
       }*/
    }
}