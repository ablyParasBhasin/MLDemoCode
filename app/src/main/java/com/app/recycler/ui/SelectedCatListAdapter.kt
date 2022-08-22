package com.app.recycler.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.step1.CommonData

class SelectedCatListAdapter(
    val contxt: Context,
    val categoryList: ArrayList<CommonData>,val tempList: ArrayList<String>,
    var listener: ListingItemClick
) :
    RecyclerView.Adapter<SelectedCatListAdapter.AdapterVH>() {
    var adapter: ChildAdapter? = null
var categoryName=String()
    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        var tvCatName: TextView = itemView.findViewById(R.id.tvCatName)
        var tvActivityName: TextView = itemView.findViewById(R.id.tvActivityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat_list, parent, false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val cat: CommonData = categoryList[position]

        holder.tvActivityName.text = cat.activityName
        holder.tvCatName.text = cat.categoryName
        holder.itemView.setOnClickListener {
            listener.clickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}