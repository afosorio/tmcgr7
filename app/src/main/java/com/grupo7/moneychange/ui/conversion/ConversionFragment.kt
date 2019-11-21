package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.grupo7.moneychange.R
import com.grupo7.moneychange.databinding.ConversionFragmentBinding

class ConversionFragment : Fragment() {

    companion object {
        fun newInstance() = ConversionFragment()
    }

    private lateinit var conversionViewModel: ConversionViewModel
    private lateinit var dataBindingView: ConversionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        conversionViewModel = ViewModelProviders.of(this@ConversionFragment).get(ConversionViewModel::class.java)
        dataBindingView = ConversionFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = conversionViewModel
            handler = EventHandler()
        }

        return dataBindingView.root
    }
}

class EventHandler {
    fun onHandleClick(view: View) {
        val action = ConversionFragmentDirections
            .actionConversionFragmentToDetailConversionFragment("ho")
        view.findNavController().navigate(action)
    }
}
