package com.grupo7.moneychange.ui.conversion

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
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
import com.grupo7.moneychange.utils.PermissionChecker
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConversionFragment : Fragment() {

    var list_of_items = arrayOf("USD", "COP", "EUR")
    private lateinit var permissionChecker: PermissionChecker

    companion object {
        fun newInstance() = ConversionFragment()
    }

    private val conversionViewModel: ConversionViewModel by viewModel()
    private lateinit var dataBindingView: ConversionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        permissionChecker = PermissionChecker(this.requireActivity(), ACCESS_FINE_LOCATION)
        //conversionViewModel = ViewModelProviders.of(this@ConversionFragment).get(ConversionViewModel::class.java)

        dataBindingView = ConversionFragmentBinding.inflate(inflater, container, false).apply {
            handler = EventHandler()
        }

        //Aqui con el Observer realizas el llamado el servicio
        conversionViewModel.getLive().observe(this, Observer {
            //Aqui se obtuvo la respuesta de tipo LiveResponse
            Toast.makeText(context, it.privacy, Toast.LENGTH_LONG).show()
        })

        conversionViewModel.country.observe(this, Observer {
            dataBindingView.locationText.text = it
        })

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
        conversionViewModel.getLocation(permissionChecker)
    }
}

class EventHandler {
    fun onHandleClick(view: View) {
        val action = ConversionFragmentDirections
            .actionConversionFragmentToDetailConversionFragment("ho")

        view.findNavController().navigate(action)
    }
}
