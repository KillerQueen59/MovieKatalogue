package com.ojanbelajar.moviekatalogue.ui.favourite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ojanbelajar.moviekatalogue.R
import com.ojanbelajar.moviekatalogue.databinding.ActivityFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity(){

    private lateinit var binding: ActivityFavouriteBinding
    private val movieFragment: Fragment = FavouriteMoviesFragment()
    private val seriesFragment: Fragment = FavouriteSeriesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.chipNav.setItemSelected(R.id.movie,true)
        commitFragment(movieFragment)
        binding.chipNav.setOnItemSelectedListener {
            clearFragmentStack()
            when(it){
                R.id.movie -> commitFragment(movieFragment)
                R.id.series -> commitFragment(seriesFragment)
            }
        }
    }


    private fun commitFragment(fragment: Fragment){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
    }

    private fun clearFragmentStack(){
        val fm = this.supportFragmentManager
        for ( i in 0..fm.backStackEntryCount){
            fm.popBackStack()
        }
    }
}