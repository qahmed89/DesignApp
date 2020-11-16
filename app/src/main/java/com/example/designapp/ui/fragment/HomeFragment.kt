package com.example.designapp.ui.fragment

import android.os.Bundle
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
    var postion1 = 0
    var postion2 = 0
    var postion3 = 0
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


                           rvHome.layoutManager?.scrollToPosition(postion1)
                        rvHome2.layoutManager?.scrollToPosition(postion2)
                        rvHome3.layoutManager?.scrollToPosition(postion3)


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

        newsAdapter.setOnItemClickListener {artical , posstion ->
            findNavController().navigate(R.id.action_home_to_testFragment)
            postion1 = posstion
        }

    }
    val pl = object : RecyclerView.OnScrollListener()  {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val x = 1

            val layoutManager1 = rvHome.layoutManager as LinearLayoutManager
            val layoutManager2 = rvHome2.layoutManager as LinearLayoutManager
            val layoutManager3 = rvHome3.layoutManager as LinearLayoutManager

            postion1 = layoutManager1.findFirstVisibleItemPosition()
            postion2 = layoutManager2.findFirstVisibleItemPosition()
            postion3 = layoutManager3.findFirstVisibleItemPosition()

        }

    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            this@HomeFragment.pl?.let { addOnScrollListener(it) }
        }
        rvHome2.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            this@HomeFragment.pl?.let { addOnScrollListener(it) }
        }
        rvHome3.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            this@HomeFragment.pl?.let { addOnScrollListener(it) }
        }
    }


}

