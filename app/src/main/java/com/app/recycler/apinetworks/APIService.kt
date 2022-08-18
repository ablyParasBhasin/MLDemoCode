package com.app.recycler.apinetworks


import com.app.recycler.models.BaseResponse
import com.app.recycler.models.BaseResponseArray
import com.app.recycler.models.dashboard.DashboardData
import com.app.recycler.models.login.LoginData
import com.app.recycler.models.step1.CommonData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST


interface APIService {

    //login
    @POST("auth-login")
    fun login(@Body requestBody: RequestBody): Call<BaseResponse<LoginData>>
 //dashboard-counts
    @POST("dashboard-counts")
    fun dashboardCounts(@Body requestBody: RequestBody): Call<BaseResponse<DashboardData>>
    @POST("acknowledge")
    fun acknowledge(@Body requestBody: RequestBody): Call<BaseResponse<CommonData>>
  @POST("get-estates")
    fun getEstates(@Body requestBody: RequestBody): Call<BaseResponseArray<CommonData>>
    @POST("get-estate-district")
    fun getEstateDistrict(@Body requestBody: RequestBody): Call<BaseResponseArray<CommonData>>
    @POST("save-step1-data")
    fun save_step1_data(@Body requestBody: RequestBody): Call<BaseResponse<CommonData>>
    @POST("get-all-categories")
    fun getAllCategories(@Body requestBody: RequestBody): Call<BaseResponse<CommonData>>
    @POST("getActivity")
    fun getActivity(@Body requestBody: RequestBody): Call<BaseResponse<CommonData>>
    @POST("save-step2-data")
    fun saveStep2Data(@Body requestBody: RequestBody): Call<BaseResponse<CommonData>>

   /* // forgot
    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("forgotPasswordAction")
    fun forgotPassword(@Field("user_email") user_email:String): Call<ForgotPasswordResponse>

    //account
    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("account")
    fun accountDeatil(@Field("token") token:String): Call<AccountResponse>

    //update_account
    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("updateUser")
    fun updateProfile(@Field("token") token:String,
                      @Field("udetails_first_name") udetails_first_name:String,
                      @Field("udetails_last_name") udetails_last_name:String,
                      @Field("udetails_phone") udetails_phone:String,
                      @Field("udetails_phone_code") udetails_phone_code:String): Call<UpdateProfileResponse>

    //update_profile_image
    @Headers("User-Agent: Android")
    @Multipart
    @POST("updatePhoto")
    fun updateProfileImage(@Part postImage: MultipartBody.Part, @Part("token") full: RequestBody): Call<UpdateProfileImage>

    //removeMemberImage
    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("removeMemberImage")
    fun removeProfile(@Field("token") token:String): Call<RemoveProfileImageResponse>

    //Change password
    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("passwordAction")
    fun changePassword(@Field("token") token:String,
                       @Field("current_password") current_password:String,
                       @Field("user_password") user_password:String): Call<PasswordUpdateResponse>

    //dashboard
    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("dashboard")
    fun dashboard(@Field("token") token:String, @Field("page") page:String): Call<DashboardResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("allOrderListing")
    fun allOrderListing(@Field("token") token:String, @Field("page") page:String): Call<OrderResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("markOffline")
    fun markDriverOffline(@Field("token") token:String): Call<DriverStatusResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("markOnline")
    fun markDriverOnline(@Field("token") token:String): Call<DriverStatusResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("viewOrder")
    fun viewOrder(@Field("token") token:String,
                  @Field("order_id") order_id:String): Call<OrderDetailResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("changeOrderDeliveryStatus")
    fun changeOrderDeliveryStatus(@Field("token") token:String,
                                  @Field("order_id") order_id:String,
                                  @Field("order_status") order_status:String): Call<OrderStatusResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("changeOrderDeliveryStatusCancel")
    fun cancelOrder(@Field("token") token:String,
                    @Field("order_id") order_id:String,
                    @Field("order_status") order_status:String,
                    @Field("order_cancel_reason") order_cancel_reason:String): Call<OrderStatusResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("logout")
    fun logout(@Field("token") token:String): Call<CommonResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("saveUserToken")
    fun saveFcmToken(@Field("token") token:String,
                     @Field("api_token") api_token:String,
                     @Field("device_type") device_type:String): Call<CommonResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("updateGeoLocation")
    fun updateGeoLocation(@Field("token") token:String,
                          @Field("udetails_latitude") udetails_latitude:String,
                          @Field("udetails_longitude") udetails_longitude:String): Call<CommonResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("queuedOrders")
    fun allQueuedOrders(@Field("token") token:String): Call<NewOrderResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("acceptOrder")
    fun acceptOrder(@Field("token") token:String,
                    @Field("order_id") order_id:String): Call<CommonResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("rejectOrder")
    fun rejectOrder(@Field("token") token:String,
                    @Field("order_id") order_id:String): Call<CommonResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("getOrderDetailsForPush")
    fun getOrderDetailsForPush(@Field("token") token:String,
                               @Field("order_id") order_id:String): Call<OrderDetailResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("listNotificationsForApp")
    fun notificationList(@Field("token") token: String?, @Field("page") page: String?): Call<NotificationResponse>

    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("markReadPushNotificationForApp")
    fun markReadPushNotification(@Field("token") token: String?, @Field("notification_id") notificationId: String?): Call<CommonResponse>



    @Headers("User-Agent: Android")
    @FormUrlEncoded
    @POST("deleteNotifications")
    fun deletePushNotification(@Field("token") token: String?, @Field("checked_notification[]") checked_notification: Array<String>?): Call<CommonResponse>
*//*    deliveryStaffApp/deleteNotifications"}
    params["checked_notification"]*//*
*//*    @Field("brand[]") brand: Array<String>*//*

    @GET("/maps/api/directions/json")
    fun getDirections(@Query("origin") origin: String?,
                      @Query("destination") destination: String?,
                      @Query("sensor") sensor: String?,
                      @Query("mode") mode: String?,
                      @Query("key") key: String?): Call<ResponseBody?>?

    @POST("/info/getSiteLanguages")
    fun getLanguages(): Call<LanguageResponse>

    @FormUrlEncoded
    @POST("walletHistory")
    fun getWalletTransactions(@Field("token") token: String?, @Field("page") page: String?): Call<Wallet>

    @FormUrlEncoded
    @POST("withdrawRequestAction")
    fun withdrawBankTransferRequest(@Field("token") token: String?,
                                    @Field("walletaccount_amount") walletaccount_amount: String?,
                                    @Field("walletaccount_phone") walletaccount_phone: String?,
                                    @Field("walletaccount_mode") walletaccount_mode: String?,
                                    @Field("walletaccount_number") walletaccount_number: String?,
                                    @Field("walletaccount_name") walletaccount_name: String?,
                                    @Field("walletaccount_bank_name") walletaccount_bank_name: String?,
                                    @Field("walletaccount_address") walletaccount_address: String?,
                                    @Field("walletaccount_branch") walletaccount_branch: String?,
                                    @Field("walletaccount_ifsc_code") walletaccount_ifsc_code: String?): Call<CommonResponse>


    @FormUrlEncoded
    @POST("withdrawRequestAction")
    fun withdrawRequest(@Field("token") token: String?,
                        @Field("walletaccount_amount") walletaccount_amount: String?,
                        @Field("walletaccount_phone") walletaccount_phone: String?,
                        @Field("walletaccount_mode") walletaccount_mode: String?,
                        @Field("walletaccount_number") walletaccount_number: String?): Call<CommonResponse>*/

}


