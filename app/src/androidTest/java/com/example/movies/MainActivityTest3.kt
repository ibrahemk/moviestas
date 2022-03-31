package com.example.movies


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest3 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest3() {
        val recyclerView = onView(
            allOf(
                withId(R.id.movieslist),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    3
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialTextView = onView(
            allOf(
                withId(R.id.cost), withText("Cost: 190000000"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    4
                )
            )
        )
        materialTextView.perform(scrollTo(), click())

        val materialTextView2 = onView(
            allOf(
                withId(R.id.desc_details),
                withText("Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist � when she gets too excited, she transforms into a giant red panda."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    6
                )
            )
        )
        materialTextView2.perform(scrollTo(), click())

        val materialTextView3 = onView(
            allOf(
                withId(R.id.desc_details),
                withText("Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist � when she gets too excited, she transforms into a giant red panda."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    6
                )
            )
        )
        materialTextView3.perform(scrollTo(), click())

        val materialTextView4 = onView(
            allOf(
                withId(R.id.desc_details),
                withText("Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist � when she gets too excited, she transforms into a giant red panda."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    6
                )
            )
        )
        materialTextView4.perform(scrollTo(), click())

        val materialTextView5 = onView(
            allOf(
                withId(R.id.desc_details),
                withText("Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist � when she gets too excited, she transforms into a giant red panda."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    6
                )
            )
        )
        materialTextView5.perform(scrollTo(), click())

        val materialTextView6 = onView(
            allOf(
                withId(R.id.rating), withText("Rating: 7.5"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    3
                )
            )
        )
        materialTextView6.perform(scrollTo(), click())

        val materialTextView7 = onView(
            allOf(
                withId(R.id.cost), withText("Cost: 190000000"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    4
                )
            )
        )
        materialTextView7.perform(scrollTo(), click())

        val materialTextView8 = onView(
            allOf(
                withId(R.id.year), withText("Year of production: 2022-03-01"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.maindata),
                        0
                    ),
                    2
                )
            )
        )
        materialTextView8.perform(scrollTo(), click())
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
}
