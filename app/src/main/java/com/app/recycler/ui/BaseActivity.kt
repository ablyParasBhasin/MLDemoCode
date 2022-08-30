package com.uni.retailer.ui.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.net.ConnectivityManager
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.recycler.R
import com.app.recycler.apinetworks.DataManager
import com.app.recycler.utility.SingleShotLocationProvider
import com.google.android.material.snackbar.Snackbar
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.*


/**
 * Created by Paras on 26/4/19.
 */
open class BaseActivity : AppCompatActivity() {

    var currentOpen = 1
    // public static CommonCallback listener;
    var loadingDialog: AlertDialog? = null

    companion object

    var textCartItemCount: TextView? = null
    /*   override fun onCreateOptionsMenu(menu: Menu): Boolean {
           menuInflater.inflate(R.menu.search_menu, menu)
           val actionView = MenuItemCompat.getActionView(menu.findItem(R.id.itemCart))
           textCartItemCount = actionView.findViewById(R.id.cart_badge)
           actionView.setOnClickListener { onOptionsItemSelected(menu.findItem(R.id.itemCart)) }


           return true
       }*/

    /*fun showMenuOptions(menu: Menu, showMenu: Int) {
        when (showMenu) {
            Constants.SHOW_CART_MENU -> menu.findItem(R.id.itemCart).isVisible = true
            Constants.SHOW_SEARCH_MENU -> menu.findItem(R.id.itemSearch).isVisible = true
            Constants.SHOW_SHARE_MENU -> menu.findItem(R.id.itemShare).isVisible = true
            Constants.SHOW_COMPARE_MENU -> menu.findItem(R.id.itemCompare).isVisible = true
        }
    }*/

    var txtBottomCartCount: TextView? = null
    /*fun setupBadge() {
        if (tablayout != null)
            txtBottomCartCount =
                tablayout.getTabAt(MainActivity.CART)?.customView?.findViewById(R.id.cart_badge)
        println("setupBadge " + DataHolder.instance.cartCount)
        if (DataHolder.instance.cartCount.equals("0")) {
            textCartItemCount?.visibility = View.GONE
            txtBottomCartCount?.visibility = View.GONE
        } else {
            textCartItemCount?.text = Math.min(DataHolder.instance.cartCount.toInt(), 99).toString()
            textCartItemCount?.visibility = View.VISIBLE
            txtBottomCartCount?.visibility = View.VISIBLE
            txtBottomCartCount?.text =
                Math.min(DataHolder.instance.cartCount.toInt(), 99).toString()

            textCartItemCount?.let { setTextViewColor(it) }
            txtBottomCartCount?.let { setTextViewColor(it) }

        }

    }*/

    /*fun showBackBtn(show: Boolean, toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        logoUrl?.let { Utility.loadImage(this, it, false, toolbar.companyLogo) }
        if (show) supportActionBar?.setDisplayHomeAsUpEnabled(true)
        else supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }*/

    /* override fun onOptionsItemSelected(item: MenuItem): Boolean {

         when (item.itemId) {
             android.R.id.home -> {
                 finish()
             }
             R.id.itemCart -> {
                 startActivity(Intent(this, CartActivity::class.java))
             }

             R.id.itemSearch -> {
                 val sendIntent: Intent = Intent().apply {
                     action = Intent.ACTION_SEND
                     putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                     type = "text/plain"
                 }

                 val shareIntent = Intent.createChooser(sendIntent, null)
                 startActivity(shareIntent)

             }
             R.id.itemCompare -> {
                 startActivity(Intent(this, CompareDetailsActivity::class.java))
             }
         }
         return super.onOptionsItemSelected(item)
     }*/

    /*  fun onCreateOptionsMenu(menu: Menu): Boolean {
          val tb = findViewById(R.id.base_activity_toolbar)
          tb.inflateMenu(R.menu.ridedetailmenu)
          tb.setOnMenuItemClickListener(
              object : Toolbar.OnMenuItemClickListener() {
                  fun onMenuItemClick(item: MenuItem): Boolean {
                      return onOptionsItemSelected(item)
                  }
              })
          return true
      }
  */
    val isNetworkConnected: Boolean
        get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return Objects.requireNonNull(cm).activeNetworkInfo != null
        }

    fun showToast(msg: String?) {
        val toast = Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    fun getIpv4HostAddress(): String {
        NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
            networkInterface.inetAddresses?.toList()?.find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let { return it.hostAddress }
        }
        return ""
    }
    fun getLatlng(){
        SingleShotLocationProvider.requestSingleUpdate(this,object :SingleShotLocationProvider.LocationCallback{
            override fun onNewLocationAvailable(location: SingleShotLocationProvider.GPSCoordinates?) {
                DataManager.instance.jsonObjectDeviceDetails.put("latitude",location?.latitude)
                DataManager.instance.jsonObjectDeviceDetails.put("longitude",location?.longitude)
                println("Location ${location?.latitude}")
            }
        })
    }

    fun setLangRecreate(activity: Activity, langval: String) {

        val config = activity.resources.configuration
        val locale = Locale(langval)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
            config.setLayoutDirection(locale)
        } else
            config.locale = locale
        activity.baseContext.resources.updateConfiguration(
            config,
            activity.baseContext.resources.displayMetrics
        )

    }

    fun clearTasks(target: Class<*>) {
        val intent = Intent(this, target)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun lightColor(color: Int): Int {
        val hsv = FloatArray(3)
        var c = color
        Color.colorToHSV(c, hsv)
        hsv[2] *= 2.5f
        c = Color.HSVToColor(hsv)
        return c
    }

    fun DarkColor(color: Int): Int {
        var c = color
        val hsv = FloatArray(3)
        Color.colorToHSV(c, hsv)
        hsv[2] = 0.80f
        c = Color.HSVToColor(hsv)
        return c
    }

    fun showSnackBar(msg: String) {
        if (isFinishing) return
        val parentLayout = window.decorView.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT)
        snackbar.setAction(R.string.ok) { view -> snackbar.dismiss() }
            .setActionTextColor(resources.getColor(R.color.colorAccent))
    }

    fun showProgress(isCancelable: Boolean) {
        if (loadingDialog != null)
            loadingDialog?.dismiss()
        val builder = AlertDialog.Builder(this, R.style.MaterialThemeDialog)
        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.layout_progress, null)
        val progresBar: ProgressBar = promptsView.findViewById(R.id.progress_bar)

        builder.setView(promptsView)
        loadingDialog = builder.create()
        loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED)
        loadingDialog?.setCancelable(isCancelable)
        loadingDialog?.setCanceledOnTouchOutside(false)
        loadingDialog?.show()
    }

    fun hideProgress() {
        try {
            if (loadingDialog != null) {
                loadingDialog!!.dismiss()
            }
        } catch (ex: Exception) {
            println("BaseActivity.hideProgress " + ex.message)
        }
    }

    fun hideSoftKeyboard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager != null && currentFocus != null)
            inputMethodManager.hideSoftInputFromWindow(
                Objects.requireNonNull<View>(currentFocus).windowToken,
                0
            )
    }

    fun showDialog(msg: String?, isCancelable: Boolean) {
        val builder = AlertDialog.Builder(this, R.style.MaterialThemeDialog)
        builder.setTitle(getString(R.string.message))
        builder.setMessage(Html.fromHtml(msg))
        builder.setCancelable(isCancelable)
        builder.setPositiveButton(getString(R.string.ok)) { dialog, which -> dialog.cancel() }
        val alert11 = builder.create()
        alert11.show()
    }

    fun showDialog(title: String?, msg: String?, isCancelable: Boolean, isExit: Boolean) {
        val builder = AlertDialog.Builder(this, R.style.MaterialThemeDialog)
        builder.setTitle(title)
        builder.setMessage(Html.fromHtml(msg))
        builder.setCancelable(isCancelable)
        builder.setPositiveButton(getString(R.string.ok)) { dialog, which -> if (isExit) this.finish() else dialog.cancel() }
        val alert11 = builder.create()
        alert11.show()
    }

    fun showKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

}