package com.example.expensemanagerproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanagerproject.ModelClass.CategoryModelClass
import com.example.expensemanagerproject.R

class CategoryAdapter(var categorylist: ArrayList<CategoryModelClass>,var invoke:((String)->Unit)) : RecyclerView.Adapter<CategoryAdapter.myview>() {

    var pos=-1
    class myview(v:View):RecyclerView.ViewHolder(v) {

        var txtcategory: TextView = v.findViewById(R.id.txtcategory)
        var btnrb:RadioButton=v.findViewById(R.id.btnrb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myview {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        val adapter = myview(v)
        return adapter
    }

    override fun onBindViewHolder(holder: myview, position: Int) {
        holder.txtcategory.setText(categorylist[position].categoryname)

        invoke.invoke(categorylist[position].categoryname)

        holder.btnrb.setOnClickListener {
            pos=position
            notifyDataSetChanged()
        }
        if (position==pos){
            holder.btnrb.isChecked=true
        }
        else{
            holder.btnrb.isChecked=false
        }
    }

    override fun getItemCount(): Int {
       return categorylist.size
    }

//    fun update(datalist: ArrayList<CategoryModelClass>) {
//        list = datalist
//        notifyDataSetChanged()
//    }
}
