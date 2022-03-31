package com.example.movies.viewmodels

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Adapter.Movies_adapter
import com.example.movies.Adapter.Types_adapter
import com.example.movies.Interface.Listinterface
import com.example.movies.Objects.Genres
import com.example.movies.Objects.Movie
import com.example.movies.apis.GetMovies_by_type
import com.example.movies.apis.Gettypesapi
import com.example.movies.handle.AsyncRq
import com.example.movies.handle.fragment.Movies_list_Fragment
import com.example.movies.handle.helper.Globals
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class Movies_model(var activity: FragmentActivity,var mclass:Movies_list_Fragment) : ViewModel(),Listinterface {

    override var templimit: Int=0
    override var moviestype: Int=0
    override var searcha: Boolean=false


    override var masync: AsyncRq<String, Objects>? =null
    var async: AsyncRq<String?, Any>?=null
    var mLayoutManager:RecyclerView.LayoutManager?=null
    var   m2LayoutManager:RecyclerView.LayoutManager?=null

    override fun handleview() {
        mclass.typeslist.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )


         m2LayoutManager = GridLayoutManager(activity, 2)


        mclass.movieslist.layoutManager = m2LayoutManager

        mclass.search.onFocusChangeListener = searchfoucs()
mclass.searchicon.setOnClickListener(searchaction())

        mclass.search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val imm =
                   activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(mclass.search.windowToken, 0)
                mclass.searchicon.performClick()
                return@OnEditorActionListener true
            }
            false
        })
handle_scroll()
    }

    override fun callapis(type:Int) {

        if (type==0) {
            Gettypesapi(activity, this@Movies_model).execute<String>()
        }else{
            templimit=1
async=  GetMovies_by_type(activity, this@Movies_model)
            async!!.execute<String>()

        }
    }

    override fun setgadapter(list:ArrayList<Genres>) {
mclass.typeslist.adapter= Types_adapter(activity,this@Movies_model,list)
    }

    override fun setmadapter(list:ArrayList<Movie>) {
        if (templimit > 1) {
if (mclass.movieslist.adapter!=null){
    var adapter:Movies_adapter=mclass.movieslist.adapter as Movies_adapter
    adapter.setlimitlist(list)
}
        } else{
            mclass.movieslist.adapter = Movies_adapter(activity, this@Movies_model, list)
        mclass.movieslist.visibility = View.VISIBLE
    }
    }

override fun searchfoucs():View.OnFocusChangeListener{
    return View.OnFocusChangeListener { view, b ->
        if (b){
            mclass.typeslist.visibility=View.GONE
            mclass.movieslist.visibility=View.GONE
        }else{
            if (view is TextView && view.text.toString().trim().isEmpty() ) {
                mclass.typeslist.visibility = View.VISIBLE
                mclass.movieslist.visibility = View.VISIBLE
                searcha=false
                callapis(1)
            }
        }
    }
}

    override fun searchaction(): View.OnClickListener {
        return View.OnClickListener {

            if (!mclass.search.text.toString().isNullOrEmpty()) {
                searcha=true
                callapis(1)
            }
        }
    }



    override fun handle_scroll() {

        mclass.movieslist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) //check for scroll down
                {
                    //                    get count of visible items  in recycle
                    val visibleItemCount: Int = m2LayoutManager!!.childCount
                    //                    get count of total items thet recycle own in layout
                    val totalItemCount: Int = m2LayoutManager!!.itemCount
                    //                    get cout f past visible item in recycle view
                    val pastVisiblesItems: Int = (m2LayoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                    //                    if  the count of items that appeared bigger than total items return true

                    //                    if  the count of items that appeared bigger than total items return true
//                    this meaning the recycle  view calling more information from server

                    val loadMore = pastVisiblesItems + visibleItemCount >= totalItemCount
                    if (loadMore) {

                        var run=true

                        if (templimit>1&&async!=null&&async!!.getStatus()==AsyncRq.Status.RUNNING){
                            run=false
                        }
                        if (run){
                            templimit += 1
                            if (async!=null) {
                                async!!.execute<String>()
                            }else{
                                async=  GetMovies_by_type(activity, this@Movies_model)
                                async!!.execute<String>()
                            }
                        }
                    }
                }
            }
        })
    }


    override fun parsetypes(result: String):ArrayList<Genres> {
//        {
//            "genres": [
//            {
//                "id": 28,
//                "name": "Action"
//            }
//            ]
//        }
var list=ArrayList<Genres>()
var genres:Genres=Genres()
        genres.id="0"
        genres.name="All"
        list.add(genres)
        if (result.contains("genres")){
            var json:JSONObject= JSONObject(result)

            var jsonarray=json.getJSONArray("genres")
            for (i in 0 until jsonarray.length()) {
                var j:JSONObject=jsonarray.getJSONObject(i)


                if (j.has("id")&&j.has("name")){
                    var g: Genres = Genres()
                    g.id=j.getString("id")
                    g.name=j.getString("name")
                    list.add(g)
                }
            }
        }
return list


    }

    override fun parsemovies(result: String): ArrayList<Movie> {
//        "results": [
//        {
//            "poster_path": "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
//            "adult": false,
//            "overview": "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
//            "release_date": "2016-08-03",
//            "genre_ids": [
//            14,
//            28,
//            80
//            ],
//            "id": 297761,
//            "original_title": "Suicide Squad",
//            "original_language": "en",
//            "title": "Suicide Squad",
//            "backdrop_path": "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
//            "popularity": 48.261451,
//            "vote_count": 1466,
//            "video": false,
//            "vote_average": 5.91
//        }}
        var list=ArrayList<Movie>()
        if (result.contains("results"))
        {
            var json:JSONObject= JSONObject(result)
            if (json.has("results")){
            var jsonarray:JSONArray=json.getJSONArray("results")
                for (i in 0 until jsonarray.length()) {
                    var j:JSONObject=jsonarray.getJSONObject(i)
                    var m:Movie=Movie()
                    if (j.has("id")&&j.has("title")&&j.has("release_date")&&j.has("poster_path")){
                        m.id=j.getString("id")
                        m.title=j.getString("title")
                        m.date=j.getString("release_date")
                        m.poster_path=Globals.imagew500+j.getString("poster_path")
                        list.add(m)
                    }
                }
        }}


        return list

    }
}