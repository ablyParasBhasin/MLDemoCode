package com.app.recycler.ui

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.interfaces.ImageCrossButtonClick
import com.app.recycler.utility.RealStoragePathLibrary
import com.app.recycler.utility.Utils
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_setp2_expand.*
import kotlinx.android.synthetic.main.activity_step3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class Step3Activity : AppCompatActivity(),ImageCrossButtonClick {
    private val GALLERY = 1
    private val CAMERA = 2

    private var cameraClickFile: File? = null

    var imageList:ArrayList<String>?=null
    var adapter:ImagesAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3)

        imageList=ArrayList()

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

    }

    private fun showPictureDialog() {
      //  var pictureDialogItems = arrayOf(getString(R.string.gallery), getString(R.string.camera), getString(R.string.remove_profile_photo))
        var pictureDialogItems = arrayOf(getString(R.string.gallery), getString(R.string.camera))


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
            val photoURI = FileProvider.getUriForFile(
                this,
                this.applicationContext.packageName + ".provider", cameraClickFile!!)
            if (photoURI != null) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
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
            path = if (inbuiltStoragePath != null && inbuiltStoragePath.isNotEmpty()) {
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
                if (mimeTypes.isNotEmpty()) {
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                }
            } else {
                var mimeTypesStr = ""
                for (mimeType in mimeTypes) {
                    mimeTypesStr += "$mimeType|"
                }
                intent.type = mimeTypesStr.substring(0, mimeTypesStr.length - 1)
            }
            try {
                val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(i, GALLERY)

            } catch (e: ActivityNotFoundException) {
                Utils.showDialog(this, getString(R.string.intent_exception_message))
            } catch (e: Exception) {
                Utils.showDialog(this, "Gallery Error")
            }
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

            Toast.makeText(this,"You can upload maximum three images.",Toast.LENGTH_SHORT).show()
        }else{

            rvImages.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
            adapter = ImagesAdapter(this,imageList!!,this)
            rvImages.adapter = adapter
            rvImages.setHasFixedSize(true)
        }

    }


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

}