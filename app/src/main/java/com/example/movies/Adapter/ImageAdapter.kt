package com.example.movies.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R


class ImageAdapter(var activity: FragmentActivity,var list:ArrayList<String>) : PagerAdapter() {

    override fun getCount(): Int {
      return  list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ImageView
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(activity)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        imageView.setImageResource(sliderImageId.get(position))
        bindPhoto(imageView,list[position])
        (container as ViewPager).addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        (container as ViewPager).removeView(`object` as ImageView)
    }
    fun bindPhoto( image:ImageView,photoUrl: String?) {
        val myOptions: RequestOptions = RequestOptions()
            .override(200, 200)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
        Glide.with(activity)
            .asBitmap()
            .load(photoUrl)
            .apply(myOptions)
            .into(image)
    }
}