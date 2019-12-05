package com.grupo7.moneychange.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.grupo7.moneychange.R

class DetailConversionFragment : Fragment() {

    companion object {
        fun newInstance() = DetailConversionFragment()
    }

    private lateinit var viewModel: DetailConversionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.detail_conversion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailConversionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
