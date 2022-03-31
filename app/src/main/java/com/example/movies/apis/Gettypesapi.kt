package com.example.movies.apis

import androidx.fragment.app.FragmentActivity
import com.example.movies.handle.AsyncRq
import com.example.movies.handle.helper.Globals
import com.example.movies.handle.helper.Requests
import com.example.movies.viewmodels.Movies_model

class Gettypesapi(var activity: FragmentActivity,var model:Movies_model) : AsyncRq<String?, Any>() {
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg Param: Any): String? {
return Requests.sendGet("${Globals.types}api_key=${Globals.Apikey}&language=en-US",activity)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (!result.isNullOrEmpty()&& result.contains("genres")) {

            model.setgadapter(model.parsetypes(result))
            model.callapis(1)
        }
    }
}