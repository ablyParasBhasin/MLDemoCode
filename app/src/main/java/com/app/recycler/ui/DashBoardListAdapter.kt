package com.app.recycler.ui



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.models.step1.CommonData

class DashBoardListAdapter(val contxt:Context, var activityList:ArrayList<CommonData>):
    RecyclerView.Adapter<DashBoardListAdapter.AdapterVH>()
{
    var viewHolder:AdapterVH?=null
    var adapter:ChildAdapter?=null
    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_activity_id : TextView = itemView.findViewById(R.id.txt_activity_id)
        var txt_collecter_id : TextView = itemView.findViewById(R.id.txt_collecter_id)
        var txt_estate_name : TextView = itemView.findViewById(R.id.txt_estate_name)
        var txt_estate_location : TextView = itemView.findViewById(R.id.txt_estate_location)
        var txt_form_start_date_time : TextView = itemView.findViewById(R.id.txt_form_start_date_time)
        var txt_form_end_date_time : TextView = itemView.findViewById(R.id.txt_form_end_date_time)
        var txt_status : TextView = itemView.findViewById(R.id.txt_status)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.lay_item_dashboard,parent,false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        viewHolder=holder
        val data :CommonData = activityList[position]

        holder.txt_activity_id.text = data.activity_id
        holder.txt_collecter_id.text = data.user_id
        holder.txt_estate_name.text = data.estateName
        holder.txt_estate_location.text = data.estate_district_name
        holder.txt_form_start_date_time.text = data.form_start_date_time
        holder.txt_form_end_date_time.text = data.form_end_date_time
        holder.txt_status.text = data.status
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

}