package com.app.recycler.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemDataClick
import com.app.recycler.models.step1.CommonData

class ChildAdapter(val contxt:Context, val modelList: ArrayList<CommonData>, var listener: ListingItemDataClick, var catName:String):
    RecyclerView.Adapter<ChildAdapter.AdapterVH>()
{
    class AdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        var checkbox_child : CheckBox = itemView.findViewById(R.id.checkbox_child)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayoutChild)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_expand_child,parent,false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val model :CommonData = modelList[position]
        holder.checkbox_child.text = model.activityName

        holder.checkbox_child.setOnCheckedChangeListener { _, isChecked ->
                listener.clickChildItem(position,catName,isChecked,model.id)
            modelList[position].isSubCatChecked = isChecked
        }

    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}