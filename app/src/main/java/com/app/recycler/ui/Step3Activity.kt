package com.app.recycler.ui

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ImageCrossButtonClick
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.step1.CommonData
import com.app.recycler.models.step3.KPIData
import com.app.recycler.utility.RealStoragePathLibrary
import com.app.recycler.utility.Utils
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_step3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.util.*


class Step3Activity : BaseActivity(), ResponseHandler,
    View.OnClickListener, ImageCrossButtonClick {
    private val GALLERY = 1
    private val CAMERA = 2

    lateinit var file: File
    private var cameraClickFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3)
        imageList=ArrayList()
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
            dpd.datePicker.maxDate = System.currentTimeMillis() - 1000
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
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000
            dpd.show()


        }
        txt_upload_pic.setOnClickListener {

            //takePhoto();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    showPictureDialog()
                } else {
                    requestPermission()
                    //showStorageDialog()
                }
            } else {
                showPictureDialog()
            }
        }
        btnSave.setOnClickListener {
            sendData()
        }
        btnSubmit.setOnClickListener {
            saveStep3Data()
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
        getActivityQuestions()
    }
    private fun requestPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,  Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        showPictureDialog()
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        Toast.makeText(this@Step3Activity,"Permissions Error",Toast.LENGTH_SHORT).show()
                        // Utility.showShortToast(this, getString(R.string.error_permissions), true)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>, token: PermissionToken
                ) { token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(this@Step3Activity,"Permissions Error",Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()
    }

    fun sendData() {
        try {
            DataManager.instance.filledActivities.add(response.data.activityData.activityId)
            if (response.data.activityData.activityId == "1") {
                try {
                  var jsonChild=JSONObject()
                    jsonChild.put("activity_1_curr_status", selectedStatus)
                    jsonChild.put("activity_1_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_1_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_1_quest_1", edt1.text.toString())
                    jsonChild.put("activity_1_quest_2", edt2.text.toString())
                    jsonChild.put("activity_1_quest_3", edt3.text.toString())
                    jsonChild.put("activity_1_quest_4", edt4.text.toString())
                    jsonChild.put("activity_1_quest_5", edt5.text.toString())
                    jsonChild.put("activity_1_quest_6", edt6.text.toString())
                    jsonChild.put("activity_1_quest_7", edt7.text.toString())
                    jsonChild.put("activity_21_file", "")
                    jsonChild.put("activity_1_file", getPartImage(cameraClickFile!!))
                    jsonChild.put("activity_1_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_1", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            } else if (response.data.activityData.activityId == "2") {
                try {
                  var jsonChild=JSONObject()
                    jsonChild.put("activity_2_curr_status", selectedStatus)
                    jsonChild.put("activity_2_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_2_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_2_quest_1", edt1.text.toString())
                    jsonChild.put("activity_2_quest_2", edt2.text.toString())
                    jsonChild.put("activity_2_quest_3", edt3.text.toString())
                    jsonChild.put("activity_21_file", "")
                    jsonChild.put("activity_2_file", "")
                    jsonChild.put("activity_2_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_2", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }
            else if (response.data.activityData.activityId == "3") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_3_curr_status", selectedStatus)
                    jsonChild.put("activity_3_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_3_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_3_quest_1", edt1.text.toString())
                    jsonChild.put("activity_3_quest_2", edt2.text.toString())
                    jsonChild.put("activity_3_quest_3", edt3.text.toString())
                    jsonChild.put("activity_3_file", "")
                    jsonChild.put("activity_23_file", "")
                    jsonChild.put("activity_3_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_3", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            } else if (response.data.activityData.activityId == "4") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_4_curr_status", selectedStatus)
                    jsonChild.put("activity_4_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_4_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_4_quest_1", edt1.text.toString())
                    jsonChild.put("activity_4_quest_2", edt2.text.toString())
                    jsonChild.put("activity_4_quest_3", edt3.text.toString())
                    jsonChild.put("activity_4_file", "")
                    jsonChild.put("activity_24_file", "")
                    jsonChild.put("activity_4_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_4", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }else if (response.data.activityData.activityId == "5") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_5_curr_status", selectedStatus)
                    jsonChild.put("activity_5_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_5_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_5_file", "")
                    jsonChild.put("activity_25_file", "")
                    jsonChild.put("activity_5_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_5", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }
            else if (response.data.activityData.activityId == "6") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_6_curr_status", selectedStatus)
                    jsonChild.put("activity_6_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_6_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_6_file", "")
                    jsonChild.put("activity_26_file", "")
                    jsonChild.put("activity_6_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_6", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            } else if (response.data.activityData.activityId == "7") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_7_curr_status", selectedStatus)
                    jsonChild.put("activity_7_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_7_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_7_quest_1", edt1.text.toString())
                    jsonChild.put("activity_7_file", "")
                    jsonChild.put("activity_27_file", "")
                    jsonChild.put("activity_7_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_7", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            } else if (response.data.activityData.activityId == "8") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_8_curr_status", selectedStatus)
                    jsonChild.put("activity_8_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_8_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_8_file", "")
                    jsonChild.put("activity_28_file", "")
                    jsonChild.put("activity_8_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_8", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            } else if (response.data.activityData.activityId == "9") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_9_curr_status", selectedStatus)
                    jsonChild.put("activity_9_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_9_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_9_file", "")
                    jsonChild.put("activity_29_file", "")
                    jsonChild.put("activity_9_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_9", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }else if (response.data.activityData.activityId == "10") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_10_curr_status", selectedStatus)
                    jsonChild.put("activity_10_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_10_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_10_file", "")
                    jsonChild.put("activity_210_file", "")
                    jsonChild.put("activity_10_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_10", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }else if (response.data.activityData.activityId == "11") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_11_curr_status", selectedStatus)
                    jsonChild.put("activity_11_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_11_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_11_file", "")
                    jsonChild.put("activity_211_file", "")
                    jsonChild.put("activity_11_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_11", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }else if (response.data.activityData.activityId == "12") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_12_curr_status", selectedStatus)
                    jsonChild.put("activity_12_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_12_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_12_quest_1", edt1.text.toString())
                    jsonChild.put("activity_12_quest_2", edt2.text.toString())
                    jsonChild.put("activity_12_file", "")
                    jsonChild.put("activity_212_file", "")
                    jsonChild.put("activity_12_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_12", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }else if (response.data.activityData.activityId == "13") {
                try {
                    var jsonChild=JSONObject()
                    jsonChild.put("activity_13_curr_status", selectedStatus)
                    jsonChild.put("activity_13_start_date", txt_start_date.text.toString())
                    jsonChild.put("activity_13_end_date", txt_end_date.text.toString())
                    jsonChild.put("activity_13_file", "")
                    jsonChild.put("activity_213_file", "")
                    jsonChild.put("activity_13_comments_observations", edtComments.text.toString())
                    DataManager.instance.jsonObject.put("activity_13", jsonChild)
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }

            if (DataManager.instance.activitiesTobeSent == DataManager.instance.filledActivities.toString()
                    .replace("[", "").replace("]", "")
            ) {
                btnSubmit.isEnabled = true
                btnSubmit.alpha = 1f

            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun showPictureDialog() {

        var pictureDialogItems = arrayOf(
            getString(R.string.gallery), getString(R.string.camera)
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
                        Utils.showDialog(this, "Camera error message")
                        return
                    }


                    loadImage(cameraClickFile?.absolutePath)
                    println("requestCode = [${requestCode}], resultCode = [${resultCode}], intentData = [${intentData}]")

                } else if (requestCode == GALLERY) {
                    if (intentData == null) return
                    val selectedImage = intentData.data


                    if (selectedImage != null) {

                        val filePathColumn =
                            arrayOf(MediaStore.Images.Media.DATA)
                        val cursor: Cursor? = selectedImage.let {
                            contentResolver.query(it, filePathColumn, null, null, null)
                        }
                        cursor?.moveToFirst()
                        val columnIndex: Int? = cursor?.getColumnIndex(filePathColumn[0])
                        val picturePath: String? = columnIndex?.let { cursor.getString(it) }
                        cursor?.close()

                        //val file = File(picturePath ?: "")
                        loadImage(picturePath)

                    } else {
                        Utils.showDialog(this, "Gallery error message")
                    }

                }
            }
        } catch (e: Exception) {
            //u.logE("Exception : $e")
        }
    }
    private fun loadImage(picturePath: String?) {

        imageList!!.add(picturePath!!)
        if(imageList?.size!!>3){

            Toast.makeText(this,"You can upload maximum three images.", Toast.LENGTH_SHORT).show()
        }else{

            rvImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapter = ImagesAdapter(this,imageList!!,this)
            rvImages.adapter = adapter
            rvImages.setHasFixedSize(true)
        }

    }
    var imageList:ArrayList<String>?=null
    var adapter:ImagesAdapter?=null
    private fun getPartImage(file: File): MultipartBody.Part {
        val requestFile: RequestBody =
            RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                file
            )
        return MultipartBody.Part.createFormData(
            "image",
            file.name,
            requestFile
        )
    }

    override fun clickCross(pos: Int) {
        if(!imageList.isNullOrEmpty()){

            imageList?.removeAt(pos)
            adapter?.notifyDataSetChanged()
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
            jsonObject.put("activity_id", intent.getStringExtra("activity_id"))
            DataManager.instance.getActivityQuestions(API_TAG.GET_ACTIVITY_QUE, jsonObject, this)
        } catch (ex: Exception) {

        }

    }

    fun saveStep3Data() {
        try {
            if (!isNetworkConnected) {
                showDialog(getString(R.string.app_no_internet), true)
                return
            }
            showProgress(true)
            var jsonObject=JSONObject()
            jsonObject.put("login_token",DataManager.instance.token)
            jsonObject.put("activity_id",DataManager.instance.commonData?.activity_id)
            jsonObject.put("user_id", DataManager.instance.userData?.id)
            jsonObject.put("step3_ans", DataManager.instance.jsonObject)
            jsonObject.put("step3_status", "1")
            println("jsonObject = [${DataManager.instance.jsonObject}]")
            DataManager.instance.saveStep3Data(
                API_TAG.SAVE_STEP_3_DATA,
                jsonObject,
                this
            )
        } catch (ex: Exception) {

        }

    }

    var response = BaseResponse<KPIData>()
    override fun onSuccess(tag: API_TAG?, apiResponse: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_ACTIVITY_QUE -> {
                response = apiResponse?.body() as BaseResponse<KPIData>
                if (response.status.equals(Constants.API_SUCCESS)) {
                    catName.text = response.data.activityData.categoryName
                    actName.text = response.data.activityData.activityName
                    try {
                        var parts =
                            response.data.activityData.questions.activity_1_curr_status.options.toString()
                                .replace("[", "").replace("]", "").split(",")
                        var tempOptions = ArrayList<String>()
                        for (i in parts) {
                            tempOptions.add(i)
                        }
                        addRadioButtons(tempOptions)

                        if (response.data.activityData.activityId == "1") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity1Quest1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity1Quest2
                            tvQuestion3.text =
                                response.data.activityData.questions.question.activity1Quest3
                            tvQuestion4.text =
                                response.data.activityData.questions.question.activity1Quest4
                            tvQuestion5.text =
                                response.data.activityData.questions.question.activity1Quest5
                            tvQuestion6.text =
                                response.data.activityData.questions.question.activity1Quest6
                            tvQuestion7.text =
                                response.data.activityData.questions.question.activity1Quest7
                        } else if (response.data.activityData.activityId == "2") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_2_quest_1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity2Quest2
                            tvQuestion3.text =
                                response.data.activityData.questions.question.activity2Quest3
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE

                        } else if (response.data.activityData.activityId == "3") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity3Quest1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity3Quest2
                            tvQuestion3.text =
                                response.data.activityData.questions.question.activity3Quest3
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "4") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_4_quest_1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity_4_quest_2
                            tvQuestion3.text =
                                response.data.activityData.questions.question.activity_4_quest_3
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "5") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity5Quest1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity5Quest2
                            tvQuestion3.text =
                                response.data.activityData.questions.question.activity5Quest3
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "6") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity6Quest1
                            question2Lt.visibility = View.GONE
                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "7") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_7_quest_1
                            question2Lt.visibility = View.GONE
                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "8") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_8_quest_1
                            question2Lt.visibility = View.GONE
                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "9") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_9_quest_1
                            question2Lt.visibility = View.GONE
                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "10") {
                            question0Lt.visibility = View.VISIBLE

                            rgQuestion0.setOnCheckedChangeListener { group, checkedId ->
                                if (checkedId == R.id.radio_yes) {
                                    question2Lt.visibility = View.VISIBLE
                                    question3Lt.visibility = View.VISIBLE
                                    question4Lt.visibility = View.VISIBLE
                                    question5Lt.visibility = View.VISIBLE
                                } else {
                                    question2Lt.visibility = View.GONE
                                    question3Lt.visibility = View.GONE
                                    question4Lt.visibility = View.GONE
                                    question5Lt.visibility = View.GONE
                                }
                            }
                            radio_no.isChecked=true
                            tvQuestion0.text =
                                response.data.activityData.questions.question.activity_10_quest_1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity_10_quest_2
                            tvQuestion3.text =
                                response.data.activityData.questions.question.activity_10_quest_3
                            tvQuestion4.text =
                                response.data.activityData.questions.question.activity_10_quest_4
                            tvQuestion5.text =
                                response.data.activityData.questions.question.activity_10_quest_5

                            question1Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE

                        } else if (response.data.activityData.activityId == "11") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_11_quest_1
                            question2Lt.visibility = View.GONE
                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "12") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_12_quest_1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity_12_quest_2

                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        } else if (response.data.activityData.activityId == "13") {
                            tvQuestion1.text =
                                response.data.activityData.questions.question.activity_13_quest_1
                            tvQuestion2.text =
                                response.data.activityData.questions.question.activity_13_quest_2

                            question3Lt.visibility = View.GONE
                            question4Lt.visibility = View.GONE
                            question5Lt.visibility = View.GONE
                            question6Lt.visibility = View.GONE
                            question7Lt.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                } else
                    showDialog(response.msg, true)
            }
            API_TAG.SAVE_STEP_2_DATA -> {
                var reposneData = apiResponse?.body() as BaseResponse<CommonData>
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

    fun addRadioButtons(list: ArrayList<String>) {
        val rgp = findViewById<View>(R.id.radio_group) as RadioGroup
        rgp.orientation = LinearLayout.HORIZONTAL
        for (i in list.indices) {
            val rbn = RadioButton(this)
            rbn.setOnClickListener(this)
            rbn.id = View.generateViewId()
            rbn.text = list[i]
            rgp.addView(rbn)
        }
    }
    var selectedStatus=""

    override fun onClick(v: View?) {
        val radioId = radio_group.checkedRadioButtonId
        val rb = findViewById<RadioButton>(radioId)
        selectedStatus=rb.text.toString()
        rb.isChecked = true
    }


}