package com.app.recycler.ui


import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.step1.CommonData
import com.fatbit.yoyumm.delivery.activity.common.ModleClassResponse

class ActivitesListAdapter(
    val contxt: Context,
    val activityList: ArrayList<String>,
    var listener: ListingItemClick
) :
    RecyclerView.Adapter<ActivitesListAdapter.AdapterVH>() {
    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_activityName: TextView = itemView.findViewById(R.id.txt_activityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_activites_list, parent, false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
       // val cat: ModleClassResponse = activityList[position]

        holder.txt_activityName.text = activityList[position]
        //holder.txt_activityName.text = cat.name

        holder.itemView.setOnClickListener {
            listener.clickChildItem(position)
        }
    }

    override fun getItemCount(): Int {
        return activityList.size
    }
}