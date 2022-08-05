package com.app.recycler.ui


/*import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response*/

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*This is for singleListing*/
    /*     singleListing()*/

        /*The is for gridView*/
        gridViewLsting()

     //ApiCall()

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
        recyclerview.layoutManager = GridLayoutManager(this, 2)
        val spanCount = 1 // 3 columns
        val spacing = 5 // 50px
        val includeEdge = true
        recyclerview.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount,
                spacing,
                includeEdge
            )
        )


        // This will pass the ArrayList to our Adapter
        val adapter1 = GridViewListingAdapter(this, getGridList(), this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter1
    }

    private fun singleListing() {
         /* if Display singleListing View.*/

        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        val adapter1 = SingleListingAdapter(this, getSingleList(), this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter1

    }



    fun getGridList(): ArrayList<DummyData> = arrayListOf(
        DummyData("Test1", R.mipmap.png1,"This is a view  1"),
        DummyData("Test2", R.mipmap.png1,"This is a view  2"),
        DummyData("Test3", R.mipmap.nature,"This is a view  3"),
        DummyData("Test4", R.mipmap.nature,"This is a view  4"),
        DummyData("Test5", R.mipmap.nature,"This is a view  5"),
        DummyData("Test6", R.mipmap.nature,"This is a view  6"),
        DummyData("Test7", R.mipmap.nature,"This is a view  7"),
        DummyData("Test8", R.mipmap.nature,"This is a view  8"),
        DummyData("Teste9", R.mipmap.nature,"This is a view  9"),
        DummyData("Test10", R.mipmap.nature,"This is a view  10"))


    fun getSingleList(): ArrayList<DummyData> = arrayListOf(
        DummyData("Test1", 0,"This is a view  1"),
        DummyData("Test2", 0,"This is a view  2"),
        DummyData("Test3", 0,"This is a view  3"),
        DummyData("Test4", 0,"This is a view  4"),
        DummyData("Test5", 0,"This is a view  5"),
        DummyData("Test6", 0,"This is a view  6"),
        DummyData("Test7", 0,"This is a view  7"),
        DummyData("Test8", 0,"This is a view  8"),
        DummyData("Test9", 0,"This is a view  9"),
        DummyData("Test10", 0,"This is a view  10"),
        DummyData("Test11", 0,"This is a view  11"),

        )


    override fun clickIten(pos: Int) {
        println("pos = [${getSingleList().get(pos).discription}]")
        
        val intent = Intent(this@MainAcivity, DescritionActivity::class.java)
        intent.putExtra("description", getSingleList().get(pos).discription);
        intent.putExtra("title", getSingleList().get(pos).title);
        startActivity(intent)
    }


}