package com.app.recycler.ui


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.interfaces.ListingItemDataClick
import com.app.recycler.models.step1.CommonData

class ParentAdapter(val contxt:Context, val categoryList: ArrayList<CommonData>, var listener: ListingItemClick):
    RecyclerView.Adapter<ParentAdapter.AdapterVH>()
{
    var viewHolder:AdapterVH?=null
    var adapter:ChildAdapter?=null
    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox_parent : CheckBox = itemView.findViewById(R.id.checkbox_parent)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayout)
        var expendableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        var subcategory_recyler : RecyclerView = itemView.findViewById(R.id.subcategory_recyler)


    }
    @SuppressLint("NotifyDataSetChanged")
    fun setChildAdapter(contxt: Context, activityList:ArrayList<CommonData>, listenerChild: ListingItemDataClick, catName:String){
           viewHolder?.subcategory_recyler?.layoutManager = LinearLayoutManager(contxt)
           adapter = ChildAdapter(contxt, activityList, listenerChild, catName)
           viewHolder?.subcategory_recyler?.adapter = adapter
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_expand,parent,false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        viewHolder=holder
        val cat :CommonData = categoryList[position]
        holder.checkbox_parent.text = cat.categoryName
        holder.subcategory_recyler.layoutManager = LinearLayoutManager(contxt)

        var isExpandable: Boolean = categoryList[position].isExpandable
        holder.expendableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE


        //it will prevent crash
        holder.checkbox_parent.setOnCheckedChangeListener(null)
        holder.checkbox_parent.isChecked = categoryList[position].isCatChecked


        holder.checkbox_parent.setOnCheckedChangeListener { _, isChecked ->
            val version = categoryList[position]
            version.isExpandable = !cat.isExpandable
            notifyItemChanged(position)

          //  modelList.setSelected(isChecked);
            categoryList[position].isCatChecked = isChecked
            if(isChecked)
            listener.clickItem(position)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    private fun setRecyclerView() {

    }


}