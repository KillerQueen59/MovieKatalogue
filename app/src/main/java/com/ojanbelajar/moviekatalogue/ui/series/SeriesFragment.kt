package com.ojanbelajar.moviekatalogue.ui.series

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
import com.ojanbelajar.moviekatalogue.databinding.FragmentSeriesBinding
import com.ojanbelajar.moviekatalogue.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.toast

@AndroidEntryPoint
class SeriesFragment: Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private val model : SeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
           init()
            val adapter = SeriesOnAiringAdapter(requireActivity())
            val array = resources.getStringArray(R.array.filter_options)
            val adapterSpinner = ArrayAdapter(requireActivity(), R.layout.spinner_item,array)
            binding.spinner.adapter = adapterSpinner
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> {
                            model.getSeries().observe(viewLifecycleOwner, Observer { series ->
                                if (series != null){
                                    when(series.status){
                                        Status.SUCCESS -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvOnAiring.visibility = View.VISIBLE
                                            adapter.submitList(series.data)
                                            binding.rvOnAiring.adapter = adapter
                                        }
                                        Status.LOADING -> {
                                            binding.progressBar.visibility = View.VISIBLE
                                            binding.rvOnAiring.visibility = View.GONE
                                        }
                                        Status.ERROR -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvOnAiring.visibility = View.VISIBLE
                                            toast("Something went wrong")
                                        }
                                    }
                                }
                            })
                        }
                        1 -> {
                            model.getSeriesByRate().observe(viewLifecycleOwner, Observer { series ->
                                if (series != null){
                                    when(series.status){
                                        Status.SUCCESS -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvOnAiring.visibility = View.VISIBLE
                                            adapter.submitList(series.data)
                                            binding.rvOnAiring.adapter = adapter
                                        }
                                        Status.LOADING -> {
                                            binding.progressBar.visibility = View.VISIBLE
                                            binding.rvOnAiring.visibility = View.GONE
                                        }
                                        Status.ERROR -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvOnAiring.visibility = View.VISIBLE
                                            toast("Something went wrong")
                                        }
                                    }
                                }
                            })
                        }
                        2 -> {
                            model.getSeriesByPopularity().observe(viewLifecycleOwner, Observer { series ->
                                if (series != null){
                                    when(series.status){
                                        Status.SUCCESS -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvOnAiring.visibility = View.VISIBLE
                                            adapter.submitList(series.data)
                                            binding.rvOnAiring.adapter = adapter
                                        }
                                        Status.LOADING -> {
                                            binding.progressBar.visibility = View.VISIBLE
                                            binding.rvOnAiring.visibility = View.GONE
                                        }
                                        Status.ERROR -> {
                                            binding.progressBar.visibility = View.GONE
                                            binding.rvOnAiring.visibility = View.VISIBLE
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
        binding.rvOnAiring.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.rvOnAiring.setHasFixedSize(true)
    }
}