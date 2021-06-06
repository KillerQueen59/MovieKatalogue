package com.ojanbelajar.moviekatalogue.ui.home


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.ojanbelajar.moviekatalogue.R
import com.ojanbelajar.moviekatalogue.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadHome(){
        onView(withId(R.id.frame_container)).check(matches(isDisplayed()))
        onView(withId(R.id.chip_nav)).check(matches(isDisplayed()))
        onView(withId(R.id.movie)).perform(click())
        onView(withId(R.id.series)).perform(click())
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.movie)).perform(click())
        onView(withId(R.id.rv_now_playing)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_now_playing)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadMovieDetail(){
        onView(withId(R.id.rv_now_playing)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rate)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_detail)).perform(scrollTo())
        onView(withId(R.id.detail_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.poster_detail)).check(matches(isDisplayed()))
    }


    @Test
    fun loadSeries(){
        onView(withId(R.id.series)).perform(click())
        onView(withId(R.id.rv_on_airing)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_on_airing)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadSeriesDetailAndAddFavourite(){
        onView(withId(R.id.series)).perform(click())
        onView(withId(R.id.rv_on_airing)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rate)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_detail)).perform(scrollTo())
        onView(withId(R.id.detail_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_detail)).perform(scrollTo())
        onView(withId(R.id.backdrop_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.poster_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun addFavouriteMovie(){
        onView(withId(R.id.rv_now_playing)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_now_playing)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.btn_favourite)).perform(click())
        pressBack()
    }

    @Test
    fun addFavouriteSeries(){
        onView(withId(R.id.series)).perform(click())
        onView(withId(R.id.rv_on_airing)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_on_airing)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.btn_favourite)).perform(click())
        pressBack()
    }

}
