package com.grupo7.moneychange.ui.conversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.grupo7.moneychange.databinding.ConversionFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConversionFragment : Fragment() {

    private val conversionViewModel: ConversionViewModel by viewModel()
    private lateinit var dataBindingView: ConversionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dataBindingView = ConversionFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = conversionViewModel
        }
        observerList()
        return dataBindingView.root
    }

    private fun observerList(){
        conversionViewModel.currencyList.observe(this, Observer {
            conversionViewModel.list2 = it
            dataBindingView.notifyChange()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }
}
