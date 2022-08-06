package com.app.recycler.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.recycler.R
import com.app.recycler.ui.fragments.ReportingFormFragment
import com.app.recycler.ui.fragments.ReportingFormFragment2


class AcknowledgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acknowledge)

       // loadFragment(ReportingFormFragment2())
        loadFragment(ReportingFormFragment())

    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}