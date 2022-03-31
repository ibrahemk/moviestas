package com.example.movies


import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.movies.handle.fragment.Movie_details_Fragment
import com.example.movies.handle.helper.MyIdlingResource
import com.example.movies.viewmodels.Details_model
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.ArrayList

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    var model:Details_model?=null
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {


        IdlingRegistry.getInstance().register(MyIdlingResource.getIdlingResource())

    }



    @Test
    fun mainActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.movieslist),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    3
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
       var fragment= getVisibleFragment() as Movie_details_Fragment?
        if (fragment!=null){
            model= fragment.model!!
            val name = onView(
                allOf(
                    withId(R.id.name),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.maindata),
                            0
                        ),
                        1
                    )
                )
            )


            val rating = onView(
                allOf(
                    withId(R.id.rating),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.maindata),
                            0
                        ),
                        3
                    )
                )
            )
            val date = onView(
                allOf(
                    withId(R.id.year),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.maindata),
                            0
                        ),
                        2
                    )
                )
            )
            val des = onView(
                allOf(
                    withId(R.id.desc_details),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.maindata),
                            0
                        ),
                        6
                    )
                )
            )
            val cost =onView(
                allOf(
                    withId(R.id.cost),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.maindata),
                            0
                        ),
                        4
                    )
                )
            )


 name.check(ViewAssertions.matches(hasname()))
            rating.check(ViewAssertions.matches(hasrat()))
            date.check(ViewAssertions.matches(hasdate()))
            date.check(ViewAssertions.matches(hasdate()))
            des.check(ViewAssertions.matches(hasdes()))
            cost.check(ViewAssertions.matches(hascost()))




        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(MyIdlingResource.getIdlingResource())
    }

    fun hasname(): BoundedMatcher<View?, TextView> {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has mins")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return ((model!=null&&model!!.movie!=null
                        &&!model!!.movie!!.title.isNullOrEmpty()) && textView.text.toString().contains(model!!.movie!!.title))
            }
        }
    }
    fun hasrat(): BoundedMatcher<View?, TextView> {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has mins")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return ((model!=null&&model!!.movie!=null
                        &&!model!!.movie!!.vote.isNullOrEmpty()) && textView.text.toString().contains(model!!.movie!!.vote))
            }
        }
    }
    fun hasdate(): BoundedMatcher<View?, TextView> {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has mins")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return ((model!=null&&model!!.movie!=null
                        &&!model!!.movie!!.date.isNullOrEmpty()) && textView.text.toString().contains(model!!.movie!!.date))
            }
        }
    }
    fun hasdes(): BoundedMatcher<View?, TextView> {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has mins")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return ((model!=null&&model!!.movie!=null
                        &&!model!!.movie!!.overview.isNullOrEmpty()) && textView.text.toString().contains(model!!.movie!!.overview))
            }
        }
    }
    fun hascost(): BoundedMatcher<View?, TextView> {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has mins")
            }

            override fun matchesSafely(textView: TextView): Boolean {
              if (model!!.movie!!.budget.isNullOrEmpty()&&textView.visibility==View.GONE){
                  return true
              }else{
                  return ((model!=null&&model!!.movie!=null
                          &&!model!!.movie!!.budget.isNullOrEmpty()) && textView.text.toString().contains(model!!.movie!!.budget))
              }
            }
        }
    }



    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    fun getVisibleFragment(): Fragment? {
        val fragments: List<Fragment> = mActivityTestRule.activity.supportFragmentManager.fragments
        val visibleFragments: ArrayList<Fragment> = ArrayList()
        if (fragments != null) {
            for (fragment in fragments) {
                if (fragment != null && fragment.isVisible) visibleFragments.add(fragment)
            }
        }
        return visibleFragments[0]
    }
}
