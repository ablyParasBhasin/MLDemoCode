package com.app.recycler.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.DummyData
import com.app.recycler.ui.SingleListingAdapter
import kotlinx.android.synthetic.main.fragment_reporting_form2.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReportingFormFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportingFormFragment2 : Fragment(), ListingItemClick {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reporting_form2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        singleListing()

    }


    private fun singleListing() {
        /* if Display singleListing View. Then Uncomment below code*/

        recyclerview.layoutManager = LinearLayoutManager(activity)

        // This will pass the ArrayList to our Adapter
        val adapter1 = SingleListingAdapter(requireActivity(), getSingleList(), this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter1

    }

    fun getSingleList(): ArrayList<DummyData> = arrayListOf(
        DummyData("Title1", 0,"This is a mountain  1"),
        DummyData("Title2", 0,"This is a mountain  2"),
        DummyData("Title3", 0,"This is a mountain  3"),
        DummyData("Title4", 0,"This is a mountain  4"),
        DummyData("Title5", 0,"This is a mountain  5"),
        DummyData("Title6", 0,"This is a mountain  6"),
        DummyData("Title7", 0,"This is a mountain  7"),
        DummyData("Title8", 0,"This is a mountain  8"),
        DummyData("Title9", 0,"This is a mountain  9"),
        DummyData("Title10", 0,"This is a mountain  10"),
        DummyData("Title11", 0,"This is a mountain  11"),

        )


    override fun clickIten(pos: Int) {

    }
}