package com.ojanbelajar.moviekatalogue.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.moviekatalogue.databinding.FragmentFavouriteSeriesBinding
import com.ojanbelajar.moviekatalogue.ui.series.SeriesOnAiringAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteSeriesFragment: Fragment() {

    private lateinit var binding: FragmentFavouriteSeriesBinding
    private val model : FavouriteViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavouriteSeriesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if( activity != null){
            init()
            val adapter = SeriesOnAiringAdapter(requireActivity())
            model.getFavouriteSeries().observe(viewLifecycleOwner, Observer { series ->
                if (!series.isEmpty()) {
                    binding.empty.visibility = View.GONE
                    adapter.submitList(series)
                    adapter.notifyDataSetChanged()
                    binding.rvFavouriteSeries.adapter = adapter
                } else {
                    binding.empty.visibility = View.VISIBLE
                }
            })
        }

    }

    private fun init() {
        binding.rvFavouriteSeries.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvFavouriteSeries.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        val adapter = SeriesOnAiringAdapter(requireActivity())
        model.getFavouriteSeries().observe(viewLifecycleOwner, Observer { series ->
            if (!series.isEmpty()) {
                binding.empty.visibility = View.GONE
                adapter.submitList(series)
                adapter.notifyDataSetChanged()
                binding.rvFavouriteSeries.adapter = adapter
            } else {
                binding.empty.visibility = View.VISIBLE
            }
        })
    }
}