package com.ojanbelajar.moviekatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.moviekatalogue.R
import com.ojanbelajar.moviekatalogue.databinding.FragmentMovieBinding
import com.ojanbelajar.moviekatalogue.movie.MovieNowPlayingAdapter
import com.ojanbelajar.moviekatalogue.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.toast

@AndroidEntryPoint
class MovieFragment: Fragment() {

    private lateinit var binding : FragmentMovieBinding
    private val model : MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if( activity != null){
            init()
            val adapter = MovieNowPlayingAdapter(requireActivity())
            val array = resources.getStringArray(R.array.filter_options)
            val adapterSpinner = ArrayAdapter(requireActivity(),R.layout.spinner_item,array)
            binding.spinner.adapter = adapterSpinner
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(position){
                        0 -> {
                            model.getMovies().observe(viewLifecycleOwner, Observer { movies ->
                                if (movies != null){
                                    when(movies.status){
                                        Status.SUCCESS -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvNowPlaying.visibility = View.VISIBLE
                                            adapter.submitList(movies.data)
                                            binding.rvNowPlaying.adapter = adapter
                                        }
                                        Status.LOADING -> {
                                            binding.progressBar.visibility = View.VISIBLE
                                            binding.rvNowPlaying.visibility = View.GONE
                                        }
                                        Status.ERROR -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvNowPlaying.visibility = View.VISIBLE
                                            toast("Something went wrong")
                                        }
                                    }
                                }
                            })
                        }
                        1 -> {
                            model.getMoviesByRate().observe(viewLifecycleOwner, Observer { movies ->
                                if (movies != null){
                                    when(movies.status){
                                        Status.SUCCESS -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvNowPlaying.visibility = View.VISIBLE
                                            adapter.submitList(movies.data)
                                            binding.rvNowPlaying.adapter = adapter
                                        }
                                        Status.LOADING -> {
                                            binding.progressBar.visibility = View.VISIBLE
                                            binding.rvNowPlaying.visibility = View.GONE
                                        }
                                        Status.ERROR -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvNowPlaying.visibility = View.VISIBLE
                                            toast("Something went wrong")
                                        }
                                    }
                                }
                            })
                        }
                        2 -> {
                            model.getMoviesByPopularity().observe(viewLifecycleOwner, Observer { movies ->
                                if (movies != null){
                                    when(movies.status){
                                        Status.SUCCESS -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvNowPlaying.visibility = View.VISIBLE
                                            adapter.submitList(movies.data)
                                            binding.rvNowPlaying.adapter = adapter
                                        }
                                        Status.LOADING -> {
                                            binding.progressBar.visibility = View.VISIBLE
                                            binding.rvNowPlaying.visibility = View.GONE
                                        }
                                        Status.ERROR -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvNowPlaying.visibility = View.VISIBLE
                                            toast("Something went wrong")
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun init() {
        binding.rvNowPlaying.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvNowPlaying.setHasFixedSize(true)
    }
}