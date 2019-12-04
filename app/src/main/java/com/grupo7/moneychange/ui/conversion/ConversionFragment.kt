package com.grupo7.moneychange.ui.conversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.grupo7.moneychange.R
import com.grupo7.moneychange.databinding.ConversionFragmentBinding
import kotlinx.android.synthetic.main.conversion_fragment.*
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
        return dataBindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }
}