package com.example.movies.Viewholder

import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Objects.Genres
import com.example.movies.R
import com.example.movies.viewmodels.Movies_model

class Type_holder(view:View,var activity: FragmentActivity,var model:Movies_model) : RecyclerView.ViewHolder(view) {
var title:TextView?=null
    init  {
title=view.findViewById(R.id.type)as TextView
    }

    fun handletype(g: Genres){
        if (!g.name.isNullOrEmpty()){
            title!!.text=g.name
            title!!.setOnClickListener(typeclick(g.id!!.toInt()))
        }
    }
    fun typeclick(type: Int):View.OnClickListener{
        return View.OnClickListener {
            model.moviestype=type
            model.callapis(1)
        }
    }

}