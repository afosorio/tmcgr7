package com.grupo7.moneychange.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.grupo7.moneychange.R
import com.grupo7.moneychange.ui.conversion.ConversionViewModel
import com.grupo7.moneychange.ui.conversion.ConversionStates
import com.grupo7.moneychange.ui.conversion.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailConversionFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by viewModel()

    companion object {
        fun newInstance() = DetailConversionFragment()
    }

    private lateinit var viewModel: DetailConversionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.detail_conversion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*viewModel = ViewModelProviders.of(this).get(DetailConversionViewModel::class.java)
        // TODO: Use the ViewModel*/
        initObservers()
    }

    private fun initObservers() {
        val a = sharedViewModel.selected.value
        sharedViewModel.getConversionStates().observe(this, Observer {
            adminStates(it)
        })
    }

    private fun adminStates(states: ConversionStates) {
        when (states) {
            is ConversionStates.ItemSelected -> Log.i("valueFrom", states.item.valueFrom.toString())
        }
    }
}
