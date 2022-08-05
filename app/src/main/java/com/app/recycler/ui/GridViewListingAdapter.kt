package com.app.recycler.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.DummyData
import com.bumptech.glide.Glide


class GridViewListingAdapter(val context: Context, var arrayList: ArrayList<DummyData>, var listener:ListingItemClick) : RecyclerView.Adapter<GridViewListingAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.lay_grid,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txt_grid.text = arrayList.get(position).title

        Glide.with(context).load(arrayList.get(position).pic)
            .into(holder.img_grid)

        holder.itemView.setOnClickListener {
            //we can then create an intent here and start a new activity
            //with our data
            listener.clickIten(position)
        }
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val txt_grid: TextView = itemView!!.findViewById(R.id.txt_grid)
        val img_grid: ImageView = itemView!!.findViewById(R.id.img_grid)



    }

}