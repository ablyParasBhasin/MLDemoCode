package com.app.recycler.ui

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.app.recycler.R
import com.app.recycler.apinetworks.API_TAG
import com.app.recycler.apinetworks.Constants
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.interfaces.ResponseHandler
import com.app.recycler.models.BaseResponse
import com.app.recycler.models.step1.CommonData
import com.app.recycler.models.step3.KPIData
import com.app.recycler.models.step3.KPIResponse
import com.app.recycler.utility.RealStoragePathLibrary
import com.app.recycler.utility.Utils
import com.uni.retailer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_step3.*
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Step3Activity : BaseActivity(), ResponseHandler {
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


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                txt_start_date.setText("" + dayOfMonth + " " + monthOfYear + ", " + year)

            }, year, month, day)

            dpd.show()

        }

        txt_end_date.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                txt_end_date.setText("" + dayOfMonth + " " + monthOfYear + ", " + year)

            }, year, month, day)

            dpd.show()


        }

        txt_choose_pic.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
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

        var pictureDialogItems = arrayOf(getString(R.string.gallery), getString(R.string.camera), getString(
            R.string.remove_profile_photo))


        val mBuilder = AlertDialog.Builder(this@Step3Activity)
        mBuilder.setTitle(getString(R.string.please_select))
        mBuilder.setItems(pictureDialogItems){ dialogInterface, i ->
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
                this.applicationContext.packageName + ".provider", cameraClickFile!!)
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
            path = if (inbuiltStoragePath != null && inbuiltStoragePath!=null) {
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
                if (mimeTypes!=null) {
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
            jsonObject.put("activity_id","1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17")
            DataManager.instance.getActivityQuestions(API_TAG.GET_ACTIVITY_QUE, jsonObject, this)
        } catch (ex: Exception) {

        }

    }
    override fun onSuccess(tag: API_TAG?, response: Response<*>?) {
        hideProgress()
        when (tag) {
            API_TAG.GET_ACTIVITY_QUE -> {
                var count = response?.body() as BaseResponse<KPIData>
                if (count.status.equals(Constants.API_SUCCESS)) {

                } else
                    showDialog(count.msg, true)
            }
        }
    }

    override fun onFailure(tag: API_TAG?, t: Throwable?) {
        hideProgress()
        println("t = [" + t.toString() + "]")
        showDialog(getString(R.string.error_something_wrong), true)
    }

}