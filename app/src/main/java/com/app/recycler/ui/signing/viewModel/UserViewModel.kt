package com.app.recycler.ui.signing.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fatbit.yoyumm.delivery.activity.common.ErrorResponse
import com.fatbit.yoyumm.delivery.activity.ui.login.model.LoginResponse
import com.app.recycler.apinetworks.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val LOGIN = 1
    val FORGOT_PASSWORD = 2

    private val mAPIService: APIService? = APIService.ApiUtils.apiService
    private val loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val errorResponse: MutableLiveData<ErrorResponse> = MutableLiveData()

    fun getLoginResponse(): LiveData<LoginResponse> {
        return loginResponse
    }

    fun login(email: String, password:String) {
        mAPIService?.login(email, password)?.enqueue(object :
            Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body();
                } else {
                    errorResponse.value = ErrorResponse("", LOGIN, null )
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
                errorResponse.value = ErrorResponse("", LOGIN, t)
            }
        })
    }


}