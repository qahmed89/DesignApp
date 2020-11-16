package com.example.designapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.designapp.R
import com.example.designapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModels: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModels = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
      //  viewModels.setData(1,"us")
    }

}