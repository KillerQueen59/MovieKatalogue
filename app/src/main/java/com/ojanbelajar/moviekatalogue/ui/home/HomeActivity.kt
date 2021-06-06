package com.ojanbelajar.moviekatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ojanbelajar.moviekatalogue.R
import com.ojanbelajar.moviekatalogue.databinding.ActivityHomeBinding
import com.ojanbelajar.moviekatalogue.ui.favourite.FavouriteActivity
import com.ojanbelajar.moviekatalogue.ui.movie.MovieFragment
import com.ojanbelajar.moviekatalogue.ui.series.SeriesFragment
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val movieFragment: Fragment = MovieFragment()
    private val seriesFragment: Fragment = SeriesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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
        binding.favBtn.setOnClickListener {
            startActivity<FavouriteActivity>()
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