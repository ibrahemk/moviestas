package com.example.movies.viewmodels

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.movies.Adapter.ImageAdapter
import com.example.movies.Interface.Details_interface
import com.example.movies.Objects.Movie
import com.example.movies.apis.Get_movies_details
import com.example.movies.handle.AsyncRq
import com.example.movies.handle.fragment.Movie_details_Fragment
import com.example.movies.handle.helper.Globals
import org.json.JSONObject

class Details_model(var activity: FragmentActivity,var dclass:Movie_details_Fragment): ViewModel(),Details_interface {
    var async: AsyncRq<String?, Any>?=null
    var movie:Movie?=null
    override fun callapis(movie: Movie) {
        async=Get_movies_details(activity,movie,this@Details_model)
        async!!.execute<String>()

    }



    override fun parsemovie(result: String): Movie {
   var m:Movie= Movie()
     if (result.isNotEmpty()){
         var json:JSONObject = JSONObject(result)
         if (json.has("id")&&json.has("title")
             &&json.has("overview")&&json.has("backdrop_path")&&json.has("poster_path")
             &&json.has("vote_average")&&json.has("release_date")){

             m.id=json.getString("id")
             m.title=json.getString("title")
             m.date=json.getString("release_date")
             m.overview=json.getString("overview")
             m.backdrop_path=Globals.imageoriginal+json.getString("backdrop_path")
             m.poster_path=Globals.imageoriginal+json.getString("poster_path")
             m.vote=json.getString("vote_average")

             if (json.has("budget")){
                 m.budget=json.getString("budget")
             }
         }
     }
        return m
    }

    override fun cancelapi() {
       if (async!=null&&async!!.getStatus()==AsyncRq.Status.RUNNING){
           async!!.cancel()
       }
    }


    override fun handleui(movie: Movie) {
        this.movie=movie
        dclass.name.text=movie.title
        dclass.year.text = "Year of production: ${movie.date}"
        dclass.rating.text="Rating: ${movie.vote}"


        if (movie.budget.isNotEmpty()) {
            dclass.cost.text = "Cost: ${movie.budget}"
            dclass.cost.visibility=View.VISIBLE
        }else{
            dclass.cost.visibility=View.GONE
        }

        dclass.desc.text=movie.overview
        val list=ArrayList<String>()
        list.add(movie.poster_path)
        list.add(movie.backdrop_path)
        var imageadapter:ImageAdapter=ImageAdapter(activity,list)
        dclass.images.adapter=imageadapter


    }


}