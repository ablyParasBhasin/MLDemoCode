package com.app.recycler.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.DummyData


class SingleListingAdapter(val context: Context, var arrayList: ArrayList<DummyData>, var listener:ListingItemClick) : RecyclerView.Adapter<SingleListingAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.lay_single_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.title.text = arrayList.get(position).title


        holder.itemView.setOnClickListener {
            //we can then create an intent here and start a new activity
            //with our data
            listener.clickIten(position)
        }
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val title: TextView = itemView!!.findViewById(R.id.title)
    }

}