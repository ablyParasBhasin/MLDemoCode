package com.app.recycler.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ListingItemClick
import com.app.recycler.models.Modell

class ExpandRecyclerAdapter(val contxt:Context,val modelList: List<Modell>,var listener: ListingItemClick):
    RecyclerView.Adapter<ExpandRecyclerAdapter.AdapterVH>()
{
    class AdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        var checkbox_parent : CheckBox = itemView.findViewById(R.id.checkbox_parent)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayout)
        var expendableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        var subcategory_recyler : RecyclerView = itemView.findViewById(R.id.subcategory_recyler)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_expand,parent,false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val model :Modell = modelList[position]
        holder.checkbox_parent.text = model.parentTitle
        holder.subcategory_recyler.layoutManager = LinearLayoutManager(contxt)


        val adapter = ExpandRecyclerAdapterChild(contxt,modelList)
        holder.subcategory_recyler.adapter = adapter
        holder.subcategory_recyler.setHasFixedSize(true)


        val isExpandable: Boolean = modelList[position].expandable
        holder.expendableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE


        //it will prevent crash
        holder.checkbox_parent.setOnCheckedChangeListener(null);
        holder.checkbox_parent.setChecked(modelList[position].isParentChecked);


        holder.checkbox_parent.setOnCheckedChangeListener { _, isChecked ->
            val version = modelList[position]
            version.expandable = !model.expandable
            notifyItemChanged(position)

          //  modelList.setSelected(isChecked);
            modelList[position].isParentChecked=isChecked
        }

    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    private fun setRecyclerView() {

    }

}