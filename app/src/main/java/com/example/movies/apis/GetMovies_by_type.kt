package com.example.movies.apis

import androidx.fragment.app.FragmentActivity
import com.example.movies.handle.AsyncRq
import com.example.movies.handle.helper.Globals
import com.example.movies.handle.helper.Requests
import com.example.movies.viewmodels.Movies_model

class GetMovies_by_type(var activity: FragmentActivity, var model: Movies_model) : AsyncRq<String?, Any>() {

   override fun doInBackground(vararg Param: Any): String? {
if (model.searcha){

    return Requests.sendGet(
        "${Globals.search}api_key=${Globals.Apikey}&page=${model.templimit.toString()}&query=${model.mclass.search.text.toString()}",
        activity
    )
}
else if (model.moviestype==0){
    return Requests.sendGet(
        "${Globals.getpopular}api_key=${Globals.Apikey}&page=${model.templimit.toString()}",
        activity
    )
}
else {
    return Requests.sendGet(
        "${Globals.getMoviesbytype}api_key=${Globals.Apikey}&with_genres=${model.moviestype}&page=${model.templimit.toString()}",
        activity
    )
}
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (!result.isNullOrEmpty()&&result.contains("results")){
            model.setmadapter(model.parsemovies(result))
        }
    }
}