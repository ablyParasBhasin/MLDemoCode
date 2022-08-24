package com.app.recycler.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.interfaces.ListingItemDataClick
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.step1.CommonData
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setp2_cat_list.*
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

class Step3CatListingActivity : BaseActivity(), ListingItemClick,ListingItemDataClick, ResponseHandler
{
    var categoryList = ArrayList<CommonData>()
    var mainActivityList = ArrayList<CommonData>()
    var activityList = ArrayList<CommonData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setp2_cat_list)
        getStep2SelectedCategories()

    }

    var adapter:SelectedCatListAdapter?=null

    fun getStep2SelectedCategories() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject= JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
            jsonObject.put("activity_id", DataManager.instance.activitiesTobeSent)
            DataManager.instance.getSelectedActivityList(API_TAG.GET_Step2_SELECTED_CATEGORIES, jsonObject, this)
        }catch (ex:Exception){

        }

    }
    var tempList=ArrayList<String>()
    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_Step2_SELECTED_CATEGORIES -> {
                var count = response?.body() as BaseResponseArray<CommonData>
                if (count.status.equals(Constants.API_SUCCESS)) {
                    categoryList= count.data as ArrayList<CommonData>
                    for(item in categoryList){
                       if(!tempList.contains(item.categoryName)){
                           tempList.add(item.categoryName)
                       }
                    }
                   setCategoryView()
                } else
                    showDialog(count.msg, true)
            }
        }
    }
    private fun setCategoryView() {
        rvCat.layoutManager = LinearLayoutManager(this)
        adapter = SelectedCatListAdapter(this,categoryList,tempList,this)
        rvCat.adapter = adapter
        rvCat.setHasFixedSize(true)
    }
    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }

    override fun clickItem(pos: Int) {
        startActivityForResult(Intent(this,Step3Activity::class.java).putExtra("activity_id",categoryList[pos].activity_id),4000)
    }

    override fun clickChildItem(pos: Int) {

    }

    override fun clickChildItem(pos:Int, value: String,boolean: Boolean,id:String) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==4000 && resultCode== RESULT_OK){
            setResult(RESULT_OK)
            finish()
        }
    }
}