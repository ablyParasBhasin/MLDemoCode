package com.app.recycler.ui


import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.step1.CommonData
import kotlinx.android.synthetic.main.activity_setp2_cat_list.*

class SelectedCatListAdapter(val contxt: Context, val categoryList: ArrayList<CommonData>, val tempList: ArrayList<String>, var listener: ListingItemClick
) :
    RecyclerView.Adapter<SelectedCatListAdapter.AdapterVH>(),ListingItemClick {

    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        var tvCatName: TextView = itemView.findViewById(R.id.tvCatName)
       // var txActivityName: TextView = itemView.findViewById(R.id.txActivityName)
        var r_activity: RecyclerView = itemView.findViewById(R.id.r_activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat_list, parent, false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val cat: CommonData = categoryList[position]

       // holder.txActivityName.text = cat.activityName
     /*   holder.txActivityName.text = cat.activityName*/
        holder.tvCatName.text = cat.categoryName


        holder.r_activity.layoutManager = LinearLayoutManager(contxt)
        val adapter = ActivitesListAdapter(contxt,categoryList[position].arrayListActivities,this)
        holder.r_activity.adapter = adapter
        holder.r_activity.setHasFixedSize(true)


        holder.itemView.setOnClickListener {
            listener.clickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun clickItem(pos: Int) {
    }

    override fun clickChildItem(pos: Int) {

        println("pos = [${tempList[pos]}]")
    }
}