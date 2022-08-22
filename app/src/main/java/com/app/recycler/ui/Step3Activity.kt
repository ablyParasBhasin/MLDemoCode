package com.app.recycler.ui

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.step3.ActivityCurrStatus
import com.app.recycler.models.step3.KPIData
import com.app.recycler.utility.RealStoragePathLibrary
import com.app.recycler.utility.Utils
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_step3.*
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class Step3Activity : BaseActivity(), ResponseHandler,
    View.OnClickListener {
    private val GALLERY = 1
    private val CAMERA = 2

    lateinit var file: File
    private var cameraClickFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3)
        txt_start_date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    txt_start_date.text = "" + dayOfMonth + "-" + monthOfYear + "-" + year

                },
                year,
                month,
                day
            )
            dpd.datePicker.maxDate=System.currentTimeMillis()-1000
            dpd.show()

        }

        txt_end_date.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    txt_end_date.text = "" + dayOfMonth + "-" + monthOfYear + "-" + year

                },
                year,
                month,
                day
            )
            dpd.datePicker.minDate=System.currentTimeMillis()-1000
            dpd.show()


        }

        txt_choose_pic.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    showPictureDialog()
                } else {
                    //showStorageDialog()
                }
            } else {
                showPictureDialog()
            }
        }
        getActivityQuestions()
    }

    private fun showPictureDialog() {

        var pictureDialogItems = arrayOf(
            getString(R.string.gallery), getString(R.string.camera), getString(
                R.string.remove_profile_photo
            )
        )


        val mBuilder = AlertDialog.Builder(this@Step3Activity)
        mBuilder.setTitle(getString(R.string.please_select))
        mBuilder.setItems(pictureDialogItems) { dialogInterface, i ->
            when (i) {
                0 -> {
                    fileChooserIntent
                }
                1 -> {
                    takePicture()
                }
            }
            dialogInterface.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraClickFile = File(sdCardPath + "/" + System.currentTimeMillis() + ".jpg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val apkURI = FileProvider.getUriForFile(
                this,
                this.applicationContext.packageName + ".provider", cameraClickFile!!
            )
            if (apkURI != null) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, apkURI)
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraClickFile))
            }
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraClickFile))
        }
        try {
            startActivityForResult(intent, CAMERA)
        } catch (e: ActivityNotFoundException) {
            Utils.showDialog(this, getString(R.string.intent_exception_message))
        } catch (e: Exception) {
            Utils.showDialog(this, getString(R.string.camera_intent_error))
        }
    }


    private val sdCardPath: String?
        get() {
            var path: String?
            val realStoragePath = RealStoragePathLibrary(this)
            val inbuiltStoragePath = realStoragePath.inbuiltStorageAppSpecificDirectoryPath
            val microSDStoragePath = realStoragePath.microSDStorageAppSpecificDirectoryPath
            path = if (inbuiltStoragePath != null && inbuiltStoragePath != null) {
                inbuiltStoragePath
            } else {
                microSDStoragePath
            }
            return path
        }


    private val fileChooserIntent: Unit
        get() {
            val mimeTypes = arrayOf("image/jpg", "image/jpeg", "image/png")
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                intent.type = if (mimeTypes.size == 1) mimeTypes[0] else "*/*"
                if (mimeTypes != null) {
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                }
            } else {
                var mimeTypesStr = ""
                for (mimeType in mimeTypes) {
                    mimeTypesStr += "$mimeType|"
                }
                // intent.type = mimeTypesStr.substring(0, mimeTypesStr.length - 1)
            }
            try {
                startActivityForResult(intent, GALLERY)
            } catch (e: ActivityNotFoundException) {
                Utils.showDialog(this, getString(R.string.intent_exception_message))
            } catch (e: Exception) {
                Utils.showDialog(this, "Gallery Error")
            }
        }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        try {
            var oriFile: File?
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == CAMERA) {
                    oriFile = cameraClickFile
                    if (oriFile == null) {
                        Utils.showDialog(this, "Error message")
                        return
                    }
                    // CropImage.activity(Uri.fromFile(oriFile)).setCropShape(CropImageView.CropShape.RECTANGLE).start(this)

                } else if (requestCode == GALLERY) {
                    if (intentData == null) return
                    val selectedImage = intentData.data
                    if (selectedImage != null) {


                    } else {
                        //Utility.showDialog(this, getString(R.string.error_msg_selected_image))
                    }

                }
            }
        } catch (e: Exception) {
            //u.logE("Exception : $e")
        }
    }

    fun getActivityQuestions() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject = JSONObject()
            jsonObject.put("login_token", DataManager.instance.token)
//            jsonObject.put("activity_id", intent.getStringExtra("activity_id"))
            jsonObject.put("activity_id", "1")
//            jsonObject.put("activity_id","1,2,3,4,5,6,7,8,9,10,11,12,13,14,15")
            DataManager.instance.getActivityQuestions(API_TAG.GET_ACTIVITY_QUE, jsonObject, this)
        } catch (ex: Exception) {

        }

    }
/*

{
  "code": 200,
  "data": {
    "activity_data": {
      "activity_id": "3",
      "activity_name": "Training on POSH, DV, POCSO, legal support and justice system",
      "category_name": "Management and Staff",
      "questions": {
        "activity_11_start_date": "activity_11_start_date",
        "activity_11_end_date": "activity_11_end_date",
        "activity_11_file": "activity_11_file",
        "activity_211_file": "activity_211_file",
        "activity_11_comments_observations": "activity_11_comments_observations",
        "activity_11_curr_status": {
          "question": "activity_11_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "question": {
          "activity_3_quest_1": "How Many Trainings Were Conducted For The Estate Management?",
          "activity_3_quest_1_male": "How Many Estate Management Members Attended The Training?",
          "activity_3_quest_1_female": "How Many Estate Management Members Attended The Training?",
          "activity_3_quest_2": "How Many Trainings Were Conducted For The Estate Staff?",
          "activity_3_quest_2_male": "How Many Estate Staff Attended The Training?",
          "activity_3_quest_2_female": "How Many Estate Staff Attended The Training?",
          "activity_3_quest_3": "How Many Trainings Were Conducted For The Estate IC?",
          "activity_3_quest_3_male": "How Many IC Members Attended The Training?",
          "activity_3_quest_3_female": "How Many IC Members Attended The Training?"
        },
        "activity_9_start_date": "activity_9_start_date",
        "activity_9_end_date": "activity_9_end_date",
        "activity_9_file": "activity_9_file",
        "activity_29_file": "activity_29_file",
        "activity_9_comments_observations": "activity_9_comments_observations",
        "activity_9_curr_status": {
          "question": "activity_9_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_12_start_date": "activity_12_start_date",
        "activity_12_end_date": "activity_12_end_date",
        "activity_12_file": "activity_12_file",
        "activity_212_file": "activity_212_file",
        "activity_12_comments_observations": "activity_12_comments_observations",
        "activity_12_curr_status": {
          "question": "activity_12_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_13_start_date": "activity_13_start_date",
        "activity_13_end_date": "activity_13_end_date",
        "activity_13_file": "activity_13_file",
        "activity_213_file": "activity_213_file",
        "activity_13_comments_observations": "activity_13_comments_observations",
        "activity_13_curr_status": {
          "question": "activity_13_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_6_start_date": "activity_6_start_date",
        "activity_6_end_date": "activity_6_end_date",
        "activity_6_file": "activity_6_file",
        "activity_26_file": "activity_26_file",
        "activity_6_comments_observations": "activity_6_comments_observations",
        "activity_6_curr_status": {
          "question": "activity_6_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_1_start_date": "activity_1_start_date",
        "activity_1_end_date": "activity_1_end_date",
        "activity_1_file": "activity_1_file",
        "activity_21_file": "activity_21_file",
        "activity_1_comments_observations": "activity_1_comments_observations",
        "activity_1_curr_status": {
          "question": "activity_1_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_10_start_date": "activity_10_start_date",
        "activity_10_end_date": "activity_10_end_date",
        "activity_10_file": "activity_10_file",
        "activity_210_file": "activity_210_file",
        "activity_10_comments_observations": "activity_10_comments_observations",
        "activity_10_curr_status": {
          "question": "activity_10_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_7_start_date": "activity_7_start_date",
        "activity_7_end_date": "activity_7_end_date",
        "activity_7_file": "activity_7_file",
        "activity_27_file": "activity_27_file",
        "activity_7_comments_observations": "activity_7_comments_observations",
        "activity_7_curr_status": {
          "question": "activity_7_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_4_start_date": "activity_4_start_date",
        "activity_4_end_date": "activity_4_end_date",
        "activity_4_file": "activity_4_file",
        "activity_24_file": "activity_24_file",
        "activity_4_comments_observations": "activity_4_comments_observations",
        "activity_4_curr_status": {
          "question": "activity_4_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_2_start_date": "activity_2_start_date",
        "activity_2_end_date": "activity_2_end_date",
        "activity_2_file": "activity_2_file",
        "activity_22_file": "activity_22_file",
        "activity_2_comments_observations": "activity_2_comments_observations",
        "activity_2_curr_status": {
          "question": "activity_2_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_8_start_date": "activity_8_start_date",
        "activity_8_end_date": "activity_8_end_date",
        "activity_8_file": "activity_8_file",
        "activity_28_file": "activity_28_file",
        "activity_8_comments_observations": "activity_8_comments_observations",
        "activity_8_curr_status": {
          "question": "activity_8_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_5_start_date": "activity_5_start_date",
        "activity_5_end_date": "activity_5_end_date",
        "activity_5_file": "activity_5_file",
        "activity_25_file": "activity_25_file",
        "activity_5_comments_observations": "activity_5_comments_observations",
        "activity_5_curr_status": {
          "question": "activity_5_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        },
        "activity_3_start_date": "activity_3_start_date",
        "activity_3_end_date": "activity_3_end_date",
        "activity_3_file": "activity_3_file",
        "activity_23_file": "activity_23_file",
        "activity_3_comments_observations": "activity_3_comments_observations",
        "activity_3_curr_status": {
          "question": "activity_3_curr_status",
          "options": [
            "Completed,In progress,Delayed,At risk"
          ]
        }
      }
    }
  }
}

 */
    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_ACTIVITY_QUE -> {
                var response = response?.body() as BaseResponse<KPIData>
                if (response.status.equals(Constants.API_SUCCESS)) {
                    catName.text = response.data.activityData.categoryName
                    actName.text = response.data.activityData.activityName
                    try {
                        var parts=response.data.activityData.questions.activityCurrStatus.options.toString().replace("[","").replace("]","").split(",")
                        var tempOptions=ArrayList<String>()
                        for (i in parts){
                            tempOptions.add(i)
                        }
                        addRadioButtons(tempOptions)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                    tvQuestion1.text=response.data.activityData.questions.question.activityQuest1
                } else
                    showDialog(response.msg, true)
            }
        }
    }

    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }
    fun addRadioButtons(list: ArrayList<String>) {
        val rgp = findViewById<View>(R.id.radio_group) as RadioGroup
        rgp.orientation = LinearLayout.HORIZONTAL
        for (i in list.indices) {
            val rbn = RadioButton(this)
            rbn.id = View.generateViewId()
            rbn.text = list[i]
            rgp.addView(rbn)
        }
    }

    override fun onClick(v: View?) {
        val radioId = radio_group.checkedRadioButtonId
        val rb = findViewById<RadioButton>(radioId)
        rb.isChecked = true
    }


}