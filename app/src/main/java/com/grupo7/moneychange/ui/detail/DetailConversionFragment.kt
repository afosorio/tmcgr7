package com.grupo7.moneychange.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.grupo7.moneychange.databinding.DetailConversionFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailConversionFragment : Fragment() {

    private val detailViewModel: DetailConversionViewModel by viewModel()
    private lateinit var dataBindingView: DetailConversionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBindingView = DetailConversionFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = detailViewModel
        }
        var args = DetailConversionFragmentArgs.fromBundle(arguments!!)
        detailViewModel.fetchHistoryById(args.historyId)
        return dataBindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*detailViewModel.historyData.observe(this, Observer {
            val s = it.toString()
        })*/
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }

}
