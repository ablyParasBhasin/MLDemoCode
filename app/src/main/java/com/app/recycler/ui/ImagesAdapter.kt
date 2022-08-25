package com.app.recycler.ui



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.recycler.R
import com.app.recycler.interfaces.ImageCrossButtonClick
import com.bumptech.glide.Glide

class ImagesAdapter(val contxt:Context, val imgList: ArrayList<String>,var imageCrossClick:ImageCrossButtonClick):
    RecyclerView.Adapter<ImagesAdapter.AdapterVH>()
{
    var viewHolder:AdapterVH?=null

    inner class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img_step3 : ImageView = itemView.findViewById(R.id.img_step3)
        var btn_close : Button = itemView.findViewById(R.id.btn_close)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.lay_images,parent,false)
        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        viewHolder=holder
        Glide.with(contxt)
            .load(imgList[position])
            .into(viewHolder?.img_step3)

        viewHolder?.btn_close?.setOnClickListener {

            imageCrossClick.clickCross(position)
        }


    }

    override fun getItemCount(): Int {
        return imgList.size
    }

}