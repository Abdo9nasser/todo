package com.asfdfsd.todo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asfdfsd.todo.DATABASE.models.databamodel

class adapter(var list:MutableList<databamodel>) : RecyclerView.Adapter<adapter.view_holder>() {
    class view_holder(view:View) :RecyclerView.ViewHolder(view)
    {
        var title_text:TextView=view.findViewById(R.id.titli_item)
        var descrption_text:TextView=view.findViewById(R.id.description_item)
        var ime_isdone:ImageView=view.findViewById(R.id.img_done)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return view_holder(view)
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        var host=list.get(position)
        holder.descrption_text.text=host.descraption
        holder.title_text.text=host.title
        val test=list.get(position)
        if(test.isdone==true)
        {
            holder.ime_isdone.setImageResource(R.drawable.mark_don)
            holder.descrption_text.setTextColor(Color.parseColor("green"))
            holder.title_text.setTextColor(Color.parseColor("green"))
        }
        holder.ime_isdone.setOnClickListener{
            object_click_img?.onclick(position,holder)

        }

    }
    var object_click_img:onimg_click?=null
    interface onimg_click{
        fun onclick(position: Int,holder: view_holder)
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}