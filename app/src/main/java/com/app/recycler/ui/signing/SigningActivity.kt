package com.app.recycler.ui.signing

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.models.BaseResponse
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.applications.MyApp
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.login.LoginData
import com.app.recycler.ui.MainAcivity
import com.app.recycler.ui.PrefConstants
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_signing.*
import org.json.JSONObject
import retrofit2.Response


class SigningActivity : BaseActivity(), ResponseHandler {

   /* private val userViewModel: UserViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var session : SessionManager
    var dialog: Dialog? = null*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signing)
       edt_username.setText("digantgupta@nirwana.in")
       edt_password.setText("12345678")
       edt_username.addTextChangedListener(mWatcher);
       edt_password.addTextChangedListener(mWatcher);

/*        session = SessionManager(applicationContext)
        dialog = ProgressDialog.progressDialog(this)

        txt_sign_in.setOnClickListener(this)
        forgot_password.setOnClickListener(this)

        userViewModel.errorResponse.observe(this, androidx.lifecycle.Observer {
            if(isFinishing)return@Observer
            dialog?.cancel()
            Utility.showErrorDialog(this)
        })

        homeViewModel.getFailedResult().observe(this, androidx.lifecycle.Observer {
            if(isFinishing)return@Observer
            dialog?.cancel()
            goToHome()
        })


        userViewModel.getLoginResponse().observe(this, androidx.lifecycle.Observer {
            if(isFinishing)return@Observer

            dialog?.cancel()

            if (it.status == Config.SUCCESS) {

                // Utility.showShortToast(this@SignInActivity, it.msg, true)

                try {
                    session.createSession(it.data.user_name,
                        "",
                        it.data.token,
                        true,
                        "",
                        "",
                        it.data.user_id,
                        "",
                        "",
                        "", 0)

                    homeViewModel.getDashboardData(it.data.token)

                } catch (e: Exception) {
                    dialog?.cancel()
                    e.printStackTrace()
                    Utility.showErrorDialog(this@SignInActivity)
                }

            } else {
                dialog?.cancel()
                Utility.showDialog(this@SignInActivity, it.msg)
            }
        })
    }



    override fun onClick(view: View) {
        when (view.id) {
            R.id.txt_sign_in -> {

                if (edt_username.text.toString().trim().equals("")) {
                    edt_username.requestFocus()
                    edt_username.error = getString(R.string.error_email_id)
                    return
                } else {
                    edt_username.error = null
                }

                if (edt_password.text.toString().trim().equals("")) {
                    edt_password.requestFocus()
                    password_lay.errorIconDrawable = null
                    edt_password.error = getString(R.string.error_password)

                    return
                } else {
                    edt_password.error = null
                }

                if(!edt_username.text.toString().trim().equals("") && !edt_password.text.toString().trim().equals("")){
                    dialog?.show()
                    userViewModel.login(edt_username.text.toString().trim(), edt_password.text.toString().trim())
                }
            }

            //forgot_password
            R.id.forgot_password -> {
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun goToHome(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }*/

       btn_login.setOnClickListener {
           login()

         /*  val intent = Intent(this@SigningActivity, MainAcivity::class.java)
           startActivity(intent)*/
           //finish()
       }

    }
    fun login() {
        if (!isNetworkConnected) {
            showDialog(getString(R.string.app_no_internet), true)
            return
        }
        showProgress(true)
        var jsonObject=JSONObject()
        jsonObject.put("email_id",edt_username.text.toString())
        jsonObject.put("password",edt_password.text.toString())
        DataManager.instance.login(API_TAG.LOGIN_API, jsonObject, this)
    }
    val mWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            val nameNotEmpty: Boolean = edt_username.getText().toString().length > 0
            val pwNotEmpty: Boolean = edt_password.getText().toString().length > 0

            println("Userrr = [${nameNotEmpty}]"+"PAssss"+pwNotEmpty)
            if(nameNotEmpty && pwNotEmpty){
                btn_login.isEnabled=true
                btn_login.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_enable_bg))
            }else{
                btn_login.isEnabled=false
                btn_login.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_disable_bg))
            }

        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    }

    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.LOGIN_API -> {
                val login = response?.body() as BaseResponse<LoginData>
                if (login.status.equals(Constants.API_SUCCESS)) {
                    saveLoginInfo(login.data)
                } else
                    showDialog(login.msg, true)
            }
        }
    }
    private fun saveLoginInfo(login: LoginData) {
        val serializeData = login?.serialize()
        DataManager.instance.getSharedPrefs(this)
            ?.saveString(PrefConstants.TOKEN, login.login_token)
        DataManager.instance.getSharedPrefs(this)?.saveString(Constants.USER_DATA, serializeData)
        (application as MyApp).initUserInfo()
        startActivity(Intent(this@SigningActivity, MainAcivity::class.java))
        finish()
    }
    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }
}