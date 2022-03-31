package com.example.movies.apis

import androidx.fragment.app.FragmentActivity
import com.example.movies.Objects.Movie
import com.example.movies.handle.AsyncRq
import com.example.movies.handle.helper.Globals
import com.example.movies.handle.helper.Requests
import com.example.movies.viewmodels.Details_model

class Get_movies_details(var activity: FragmentActivity,var movie: Movie,var model:Details_model) : AsyncRq<String?, Any>() {
    override fun doInBackground(vararg Param: Any): String? {
        return Requests.sendGet(
            "${Globals.moviedetails}${movie.id}?api_key=${Globals.Apikey}&language=en-US",
            activity
        )
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(!result.isNullOrEmpty()&&result.contains("id")){
          model.handleui(model.parsemovie(result))
        }
    }
}