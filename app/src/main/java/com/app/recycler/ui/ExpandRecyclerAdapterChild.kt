package com.app.recycler.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.models.Modell


class ExpandRecyclerAdapterChild(val contxt:Context, val modelList: List<Modell>):
    RecyclerView.Adapter<ExpandRecyclerAdapterChild.AdapterVH>()
{
    class AdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        var checkbox_child : CheckBox = itemView.findViewById(R.id.checkbox_child)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_expand_child,parent,false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val model :Modell = modelList[position]
        holder.checkbox_child.text = model.childtxtName

    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}