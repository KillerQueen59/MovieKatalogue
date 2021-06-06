package com.ojanbelajar.moviekatalogue.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.moviekatalogue.databinding.FragmentFavouriteMovieBinding
import com.ojanbelajar.moviekatalogue.movie.MovieNowPlayingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMoviesFragment: Fragment() {

    private lateinit var binding: FragmentFavouriteMovieBinding
    private val model : FavouriteViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavouriteMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if( activity != null){
            init()
            val adapter = MovieNowPlayingAdapter(requireActivity())
            model.getFavouriteMovie().observe(viewLifecycleOwner, Observer { movies ->
                if (!movies.isEmpty()) {
                    binding.empty.visibility = View.GONE
                    adapter.submitList(movies)
                    adapter.notifyDataSetChanged()
                    binding.rvFavouriteMovie.adapter = adapter
                } else {
                    binding.empty.visibility = View.VISIBLE
                }
            })
        }

    }

    private fun init() {
        binding.rvFavouriteMovie.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvFavouriteMovie.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        model.getFavouriteMovie().observe(viewLifecycleOwner, Observer { movies ->
            val adapter = MovieNowPlayingAdapter(requireActivity())
            if (!movies.isEmpty()) {
                binding.empty.visibility = View.GONE
                adapter.submitList(movies)
                adapter.notifyDataSetChanged()
                binding.rvFavouriteMovie.adapter = adapter
            } else {
                binding.empty.visibility = View.VISIBLE
            }
        })
    }
}