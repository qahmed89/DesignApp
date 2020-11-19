package com.example.designapp.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.designapp.R
import com.example.designapp.adapter.NewsAdapter
import com.example.designapp.other.Status
import com.example.designapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    val viewModels by viewModels<NewsViewModel>()
    lateinit var newsAdapter: NewsAdapter

    var rvHomeState1: Parcelable ?=null
    var rvHomeState2: Parcelable ?=null
    var rvHomeState3: Parcelable ?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //   viewModels = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        setupRecyclerView()
        viewModels.setData(1, "us")


        viewModels.data.observe(viewLifecycleOwner, {

            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        newsAdapter.differ.submitList(result.data?.articles)
                        rvHome.layoutManager?.onRestoreInstanceState(rvHomeState1)

                        rvHome2.layoutManager?.onRestoreInstanceState(rvHomeState2)
                        rvHome3.layoutManager?.onRestoreInstanceState(rvHomeState3)




                    }
                    Status.ERROR -> {
                        //  pbDeatils.visibility = View.GONE

                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        // pbDeatils.visibility = View.VISIBLE
                    }
                }

            }
        })

        newsAdapter.setOnItemClickListener { artical, posstion ->
            findNavController().navigate(R.id.action_home_to_testFragment)
        }

    }



    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        rvHome2.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        rvHome3.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onPause() {
        super.onPause()

        rvHomeState1 = rvHome.layoutManager?.onSaveInstanceState()!!
        rvHomeState2 = rvHome2.layoutManager?.onSaveInstanceState()!!
        rvHomeState3 = rvHome3.layoutManager?.onSaveInstanceState()!!


    }

}

