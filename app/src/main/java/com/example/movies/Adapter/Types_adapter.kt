package com.example.movies.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Objects.Genres
import com.example.movies.R
import com.example.movies.Viewholder.Type_holder
import com.example.movies.viewmodels.Movies_model

class Types_adapter(var activity: FragmentActivity,var model:Movies_model,var list:ArrayList<Genres>) : RecyclerView.Adapter<Type_holder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Type_holder {


        return Type_holder( LayoutInflater.from(parent.context).inflate(R.layout.type_layout, parent, false)
        ,activity,model)
    }

    override fun onBindViewHolder(holder: Type_holder, position: Int) {
    holder.handletype(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}