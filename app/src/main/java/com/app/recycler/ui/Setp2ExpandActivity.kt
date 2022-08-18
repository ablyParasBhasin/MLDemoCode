package com.app.recycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.Modell
import kotlinx.android.synthetic.main.activity_setp2_expand.*


/* getallcategory {"code":200,"data":[{"id":"3","category_name":"Change Agents and
Advocates"},{"id":"5","category_name":"Community"},{"id":"1","category_name":"External Stakeholder
Consultaion"},{"id":"2","category_name":"Management and Staff"},{"id":"4","category_name":"Workers"}]}
*/

/* getactivity {"code":200,"data":[{"id":"6","activity_name":"Engaging staff to plan awareness and regular activities to prevent VAW in
the workplace","category_name":"Change Agents and Advocates"},{"id":"4","activity_name":"Training on Gender
Sensitisation","category_name":"Change Agents and Advocates"},{"id":"5","activity_name":"Training on POSH, DV, POCSO,
legal support and justice system","category_name":"Change Agents and Advocates"}]}
*/
/* step2submission     "user_id": "1*****",
    "activity_id": "123***",
    "step2_ans": ‘{"question_6":"1,2,3","question_7":"4,5"}’,  question 6 is categories ids and question 7 is activities ids with comma separated
    "step2_status": NULL   Pass step2_status value 1 if user click on submit button
*/

class Setp2ExpandActivity : AppCompatActivity(), ListingItemClick {
    val ModellList = ArrayList<Modell>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setp2_expand)

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recycler_view?.layoutManager = LinearLayoutManager(this)

        val adapter = ExpandRecyclerAdapter(this,ModellList,this)
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)
    }

    private fun initData() {
        ModellList.add(
            Modell(
            "Test1",
            "Child1",
        )
        )

        ModellList.add(Modell(
            "Test2",
            "Child2",

        ))

        ModellList.add(Modell(
            "Test3",
            "Child3",

        ))

        ModellList.add(Modell(
            "Test4",
            "Child4",

        ))

        ModellList.add(Modell(
            "Test5",
            "Child5",

        ))
        ModellList.add(Modell(
            "Test6",
            "Child6",

        ))
    }

    override fun clickIten(pos: Int) {

        println("pos = [${pos}]")
    }
}