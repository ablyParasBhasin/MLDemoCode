package com.app.recycler.apinetworks

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.app.recycler.applications.MyApp
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.dashboard.DashboardData
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.login.LoginData
import com.app.recycler.models.step1.CommonData
import com.app.recycler.models.step3.KPIData
import com.app.recycler.ui.PrefConstants
import com.uni.retailer.ui.base.BaseActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataManager private constructor() : BaseActivity() {
    var networkCalls: APIService
    private var deviceId: String? = null
    var token = ""
    var userName: String? = null
    var companyName: String? = null
    var userImage: String? = null
    var deviceLocation: String? = null
    var cartCount = 0
    var favCount = 0
    var userType = 0
    var userData: LoginData? = null
    var commonData: CommonData? = null
    var activitiesTobeSent=""
    var jsonObject = JSONObject()
    private var prefs: SharedPref? = null
    private var dataManager: DataManager? = null

    private var context: MyApp? = null

    fun setDeviceId(deviceId: String?) {
        this.deviceId = deviceId
    }

    @SuppressLint("HardwareIds")
    fun initializeDeviceId(ctx: Context): String {
        var device_id: String = getSharedPrefs(ctx).getString(PrefConstants.DEVICE_ID)
        if (device_id.isEmpty()) {
            device_id = Settings.Secure.getString(ctx.contentResolver, Settings.Secure.ANDROID_ID)
            getSharedPrefs(ctx).saveString(PrefConstants.DEVICE_ID, device_id)
        }
        return device_id
    }

    companion object {
        private var holder: DataManager? = null

        val instance: DataManager
            get() {
                if (holder == null)
                    holder = DataManager()
                return holder!!
            }
    }
    fun getSharedPrefs(ctx: Context?): SharedPref {
        if (prefs == null) prefs = SharedPref(ctx)
        return prefs as SharedPref
    }

    fun setContext(context: MyApp?) {
        this.context = context
    }
//    var jsonObject= JSONObject()
    fun login(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.login(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<LoginData>> {
            override fun onResponse(
                call: Call<BaseResponse<LoginData>>,
                response: Response<BaseResponse<LoginData>>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<LoginData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun getEstates(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.getEstates(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponseArray<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponseArray<CommonData>>,
                response: Response<BaseResponseArray<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponseArray<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun getEstateDistrict(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.getEstateDistrict(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponseArray<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponseArray<CommonData>>,
                response: Response<BaseResponseArray<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponseArray<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun getSelectedActivityList(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.getSelectedActivityList(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponseArray<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponseArray<CommonData>>,
                response: Response<BaseResponseArray<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponseArray<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }

    fun saveStep1Data(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.save_step1_data(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponse<CommonData>>,
                response: Response<BaseResponse<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun getAllCategories(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.getAllCategories(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponseArray<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponseArray<CommonData>>,
                response: Response<BaseResponseArray<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponseArray<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun saveStep2Data(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.saveStep2Data(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponse<CommonData>>,
                response: Response<BaseResponse<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun saveStep3Data(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.saveStep3Data(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponse<CommonData>>,
                response: Response<BaseResponse<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun getActivityQuestions(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.getActivityQuestions(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<KPIData>> {
            override fun onResponse(
                call: Call<BaseResponse<KPIData>>,
                response: Response<BaseResponse<KPIData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<KPIData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun getActivity(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.getActivity(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponseArray<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponseArray<CommonData>>,
                response: Response<BaseResponseArray<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponseArray<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
    fun dashboardCounts(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.dashboardCounts(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<DashboardData>> {
            override fun onResponse(
                call: Call<BaseResponse<DashboardData>>,
                response: Response<BaseResponse<DashboardData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<DashboardData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }
fun setAcknwoledge(tag: API_TAG?,jsonObject:JSONObject, listener: ResponseHandler) {
     networkCalls.acknowledge(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())).
        enqueue(object : Callback<BaseResponse<CommonData>> {
            override fun onResponse(
                call: Call<BaseResponse<CommonData>>,
                response: Response<BaseResponse<CommonData>?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }
                    listener.onSuccess(tag,response)

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse<CommonData>>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }


    init {
        networkCalls = APIClient.getClient().create(APIService::class.java)
    }
}