package com.example.dodomainlayout.network.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dodomainlayout.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.Holder>() {
    private  var categoryArray: Array<Category> = arrayOf()
    val data = MutableLiveData<Int>()
    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(c: Category) = with(itemView){
            itemView.findViewById<TextView>(R.id.name).text = c.strCategory
            if (c.strCategoryDescription!!.length > 100)
            {
                itemView.findViewById<TextView>(R.id.ingredients).text = c.strCategoryDescription!!.substring(0, 100)
            }
            else
            {
                itemView.findViewById<TextView>(R.id.ingredients).text = c.strCategoryDescription
            }

            Glide.with(this).load(c.strCategoryThumb).into(itemView.findViewById<ImageView>(R.id.imgView))
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoryArray[position])

        data.value = position
        data.postValue(data.value)
    }

    override fun getItemCount(): Int = categoryArray.count()
    fun refresh(c: CategoryArray) {
        this.categoryArray = c.categories!!
        notifyDataSetChanged()
    }
}