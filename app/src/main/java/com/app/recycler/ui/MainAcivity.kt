package com.app.recycler.ui


/*import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response*/

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.recycler.R
import com.app.recycler.databinding.ActivityMainBinding
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.DummyData
import com.app.recycler.utility.ConstantMethod
import com.app.recycler.utility.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainAcivity : AppCompatActivity(), ListingItemClick {

    var arrayList: ArrayList<DummyData>? = null
    private val client = OkHttpClient()
    val databinding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // databinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        /*The is for gridView*/
        gridViewLsting()

        //ApiCall()


        btnStartActivity.setOnClickListener {
            val intent = Intent(this@MainAcivity, AcknowledgeActivity::class.java)
            startActivity(intent)
        }


    }

    fun ApiCall() {
        val request = Request.Builder()
            .url(ConstantMethod.APiurl)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {



            }

        })
    }

    private fun gridViewLsting() {

        /*  if Display GridViewListing View.*/
        recyclerview?.layoutManager = GridLayoutManager(this, 3)

        //val spanCount = 1 // 3 columns
        val spacing = 5 // 50px
        val includeEdge = false
        recyclerview?.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                spacing,
                includeEdge
            )
        )


        // This will pass the ArrayList to our Adapter
        val adapter1 = GridViewListingAdapter(this, getGridList(), this)
        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter1
    }


    fun getGridList(): ArrayList<DummyData> = arrayListOf(
        DummyData("Total Survey", R.drawable.total_survey,"20"),
        DummyData("In Progress", R.drawable.in_progress,"20"),
        DummyData("Completed", R.drawable.completed,"20"),
        DummyData("To be Sync", R.drawable.to_be_synced,"20"),
        DummyData("Delayed", R.drawable.delayed,"20"),
        DummyData("At Risk", R.drawable.all_risk,"20"))



    override fun clickIten(pos: Int) {
      /*  println("pos = [${getSingleList().get(pos).discription}]")
        
        val intent = Intent(this@MainAcivity, DescritionActivity::class.java)
        intent.putExtra("description", getSingleList().get(pos).discription);
        intent.putExtra("title", getSingleList().get(pos).title);
        startActivity(intent)*/
    }


}