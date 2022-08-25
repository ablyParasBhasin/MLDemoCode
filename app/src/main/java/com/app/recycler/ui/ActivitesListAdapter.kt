package com.app.recycler.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick

class ActivitesListAdapter(
    val contxt: Context,
//    val activityList: ArrayList<String>,
    val activityList: LinkedHashMap<String,String>,
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

        val keys: ArrayList<String> = ArrayList(activityList.values)
        for (item in 0 until keys.size) {
            if(keys[item]==keys[position]){
                println("ID " + keys[item])
                holder.txt_activityName.text = keys[item]
            }
        /*for (item in activityList.values) {
            holder.txt_activityName.text = item*/
        }
        //holder.txt_activityName.text = cat.name

        holder.itemView.setOnClickListener {
            val keys: ArrayList<String> = ArrayList(activityList.keys)
            for (item in 0 until keys.size) {
                if(keys[item]==keys[position]){
                println("ID " + keys[item])
                    listener.clickChildItem(keys[item].toInt())
            }
            }

//            listener.clickChildItem(activityList)
        }
    }

    override fun getItemCount(): Int {
        return activityList.size
    }
}