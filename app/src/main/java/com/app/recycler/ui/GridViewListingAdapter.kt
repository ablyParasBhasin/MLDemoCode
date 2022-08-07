package com.app.recycler.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.DummyData
import com.bumptech.glide.Glide
import java.util.*


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
        holder.txt_count.text = arrayList.get(position).discription




//        val rnd = Random()
//        val currentColor: Int =
//            Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        val index: Int = position % backgroundColors.size
        val color = ContextCompat.getColor(context, backgroundColors[index])
        holder.lay_item.setBackgroundColor(color)

        Glide.with(context).load(arrayList.get(position).pic)
            .into(holder.img_grid)

        holder.itemView.setOnClickListener {
            //we can then create an intent here and start a new activity
            //with our data
            listener.clickIten(position)
        }
    }

    // first define colors
    private val backgroundColors = intArrayOf(
        R.color.item1,
        R.color.item2,
        R.color.item3,
        R.color.item4,
        R.color.item5,
        R.color.item6,
    )
    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val txt_grid: TextView = itemView!!.findViewById(R.id.txt_grid)
        val txt_count: TextView = itemView!!.findViewById(R.id.txt_count)
        val img_grid: ImageView = itemView!!.findViewById(R.id.img_grid)
        val lay_item: RelativeLayout = itemView!!.findViewById(R.id.lay_item)
        val cardlay: CardView = itemView!!.findViewById(R.id.cardlay)



    }

}