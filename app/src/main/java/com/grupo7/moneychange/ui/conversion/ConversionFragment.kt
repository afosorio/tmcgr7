package com.grupo7.moneychange.ui.conversion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.grupo7.moneychange.R
import com.grupo7.moneychange.databinding.ConversionFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConversionFragment : Fragment() {

    var listOfItems = arrayOf("USD", "COP", "EUR")

    private val conversionViewModel: ConversionViewModel by viewModel()
    private lateinit var dataBindingView: ConversionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //conversionViewModel = ViewModelProviders.of(this@ConversionFragment).get(ConversionViewModel::class.java)

        dataBindingView = ConversionFragmentBinding.inflate(inflater, container, false).apply {
            handler = EventHandler()
            viewModel = conversionViewModel
        }
        initViews()
        return dataBindingView.root
    }

    private fun initViews() {
        context?.let {
            val adapter = ArrayAdapter(it, R.layout.spinner_item, listOfItems)
            adapter.setDropDownViewResource(R.layout.spinner_item)
            dataBindingView.conversionFromSpinner.apply {
                this.adapter = adapter
            }
            dataBindingView.conversionToSpinner.apply {
                this.adapter = adapter
            }
        }

        conversionViewModel.serverResponse.observe(this, Observer {
            Toast.makeText(context, it.value.toString(), Toast.LENGTH_SHORT).show()
        })

        conversionViewModel.textView.observe(this, Observer {
            Log.d("alx", it.toString())
        })

    }
}

class EventHandler {
    fun onHandleClick(view: View) {
        val action = ConversionFragmentDirections
            .actionConversionFragmentToDetailConversionFragment("ho")

        view.findNavController().navigate(action)
    }
}
