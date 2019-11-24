package com.grupo7.moneychange.ui.conversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.grupo7.moneychange.R
import com.grupo7.moneychange.databinding.ConversionFragmentBinding

class ConversionFragment : Fragment() {

    var list_of_items = arrayOf("USD", "COP", "EUR")

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
            handler = EventHandler()
        }

        initViews()

        return dataBindingView.root
    }

    private fun initViews() {
        context?.let {
            val adapter = ArrayAdapter(it, R.layout.spinner_item, list_of_items)
            adapter.setDropDownViewResource(R.layout.spinner_item)
            dataBindingView.conversionFromSpinner.apply {
                this.adapter = adapter
            }
            dataBindingView.conversionToSpinner.apply {
                this.adapter = adapter
            }
        }

    }
}

class EventHandler {
    fun onHandleClick(view: View) {
        val action = ConversionFragmentDirections
            .actionConversionFragmentToDetailConversionFragment("ho")

        view.findNavController().navigate(action)
    }
}
