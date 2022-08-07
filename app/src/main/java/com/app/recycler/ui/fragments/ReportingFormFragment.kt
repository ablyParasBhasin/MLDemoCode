package com.app.recycler.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.recycler.R
import kotlinx.android.synthetic.main.fragment_reporting_form.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReportingFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportingFormFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reporting_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        btn_acknowledge.setOnClickListener {

            loadFragment(ReportingFormFragment2())

        }

    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.containerchild, fragment)
        transaction.commit()
    }
}