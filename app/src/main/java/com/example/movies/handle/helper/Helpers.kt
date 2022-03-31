package com.example.movies.handle.helper

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.example.movies.R

class Helpers {

    companion object {
        fun changeToolbar(
            activity: FragmentActivity,
            s: String?,
            v: View,

        ) {
            val toolbar = v.findViewById<View>(R.id.toolbar) as Toolbar


            if (toolbar != null) {
                val title = toolbar.findViewById<View>(R.id.title) as TextView

                title.text = s
                toolbar.setNavigationIcon(R.drawable.ic_left_arrow)

                toolbar.setNavigationOnClickListener { activity.supportFragmentManager.popBackStack() }
                //            ((MainActivity)activity).toggleNavDrawer(toolbar);
            }
        }

    }
}