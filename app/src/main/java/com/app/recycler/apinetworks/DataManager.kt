package com.app.recycler.apinetworks

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.app.recycler.applications.MyApp
import com.app.recycler.interfaces.ResponseHandler
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
        enqueue(object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()?.status== Constants.INVALID_TOKEN){

                    }

                } else listener.onFailure(tag, Throwable(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                call.cancel()
                listener.onFailure(tag, t)
            }
        })
    }


    init {
        networkCalls = APIClient.getClient().create(APIService::class.java)
    }
}