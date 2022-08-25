package com.app.recycler.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.step1.CommonData

class SelectedCatListAdapter(
    val contxt: Context,
    val categoryList: ArrayList<CommonData>,val mainCatList: ArrayList<CommonData>,
    var listener: ListingItemClick
) :
    RecyclerView.Adapter<SelectedCatListAdapter.AdapterVH>() {
    var adapter: ChildAdapter? = null
    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        var tvCatName: TextView = itemView.findViewById(R.id.tvCatName)
//        var tvActivityName: TextView = itemView.findViewById(R.id.tvActivityName)
        var r_activity: RecyclerView = itemView.findViewById(R.id.r_activity)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat_list, parent, false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val cat: CommonData = categoryList[position]

        holder.tvCatName.text = cat.categoryName
        holder.r_activity.layoutManager = LinearLayoutManager(contxt)

        val adapter = ActivitesListAdapter(contxt,cat.arrayListActivitiesMap,object : ListingItemClick{

            override fun clickItem(pos: Int) {

//
            }

            override fun clickChildItem(pos: Int) {
                listener.clickItem(pos)
            }

        })
        holder.r_activity.adapter = adapter
        holder.r_activity.setHasFixedSize(true)
      /*  holder.itemView.setOnClickListener {
            listener.clickItem(position)
        }*/
    }
  /*   {"code":200,"data":[{"activity_id":"6","activity_name":"Engaging  staff to plan awareness and  regular activities to prevent VAW in the workplace","category_name":"Change Agents and Advocates"}
  ,{"activity_id":"1","activity_name":"External Stakeholder Consultaion","category_name":"External Stakeholder Consultaion"},
  {"activity_id":"4","activity_name":"Training on Gender Sensitisation","category_name":"Change Agents and Advocates"},
  {"activity_id":"2","activity_name":"Training on Gender Sensitisation","category_name":"Management and Staff"},
  {"activity_id":"3","activity_name":"Training on POSH, DV, POCSO, legal support and justice system","category_name":"Management and Staff"}]}*/
    override fun getItemCount(): Int {
        return categoryList.size
    }


}