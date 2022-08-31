package com.app.recycler.utility

import android.os.AsyncTask
import com.app.recycler.apinetworks.DataManager
import java.io.IOException
import java.net.URL
import java.util.*

class GetPublicIP : AsyncTask<String?, String?, String>() {

        override fun onPostExecute(publicIp: String) {
            super.onPostExecute(publicIp)
            println("publicIp = [${publicIp}]")
            DataManager.instance.publicIP=publicIp

            //Here 'publicIp' is your desire public IP
        }

    override fun doInBackground(vararg params: String?): String {
        var publicIP = ""
        try {
            val s = Scanner(
                URL(
                    "https://api.ipify.org"
                )
                    .openStream(), "UTF-8"
            )
                .useDelimiter("\\A")
            publicIP = s.next()
            println("My current IP address is $publicIP")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return publicIP
    }

}