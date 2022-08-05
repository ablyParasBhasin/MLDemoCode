package com.app.recycler.ui.signing

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.app.recycler.R
import com.app.recycler.ui.MainAcivity
import kotlinx.android.synthetic.main.activity_signing.*


class SigningActivity : AppCompatActivity() {

   /* private val userViewModel: UserViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var session : SessionManager
    var dialog: Dialog? = null*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signing)

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
           val intent = Intent(this@SigningActivity, MainAcivity::class.java)
           startActivity(intent)
           finish()
       }

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
}