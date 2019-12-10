package com.grupo7.moneychange.ui.conversion

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.grupo7.moneychange.adapters.IRecyclerViewAdapter
import com.grupo7.moneychange.data.entity.History
import com.grupo7.moneychange.databinding.ConversionFragmentBinding
import com.grupo7.moneychange.utils.PermissionChecker
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConversionFragment : Fragment() {

    private val conversionViewModel: ConversionViewModel by viewModel()
    private lateinit var dataBindingView: ConversionFragmentBinding
    private lateinit var permissionChecker: PermissionChecker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        permissionChecker = PermissionChecker(this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
        dataBindingView = ConversionFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = conversionViewModel
        }
        return dataBindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListAdapter()
    }

    private fun initListAdapter() {
        val viewModel = dataBindingView.viewModel
        viewModel?.let {
            val adapter = IRecyclerViewAdapter(conversionViewModel::clickDataUp, ::navigationDetailConversionFragment)
            dataBindingView.recyclerView.adapter = adapter
        }
        conversionViewModel.getLocation(permissionChecker)
    }

    private fun navigationDetailConversionFragment(item:History) {
        val action = ConversionFragmentDirections.actionConversionFragmentToDetailConversionFragment(item.id)
        view?.findNavController()?.navigate(action)
    }
}