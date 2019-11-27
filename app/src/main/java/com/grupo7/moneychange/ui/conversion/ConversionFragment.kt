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
import com.grupo7.moneychange.models.response.LiveResponse
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KCallable

class ConversionFragment : Fragment() {

    var listOfItems = mutableListOf<String>()

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

        initObserver()
        return dataBindingView.root
    }

    private fun initObserver() {

//        conversionViewModel.getLive().observe(this, Observer {
//
//            it?.takeIf {
//                it.success
//            }?.let { response ->
//                successPath(response)
//            } ?: errorPath()
//        })

        conversionViewModel.serverResponse.observe(this, Observer {
            Toast.makeText(context, it.quotes.toString(), Toast.LENGTH_SHORT).show()
            Log.d("alx", it.quotes.toString())
        })

        conversionViewModel.textView.observe(this, Observer {
            Log.d("alx", it.toString())
        })
    }

    private fun errorPath() {
        dataBindingView.llErrorContainer.visibility = View.VISIBLE

    }

    private fun successPath(response: LiveResponse) {
        dataBindingView.llErrorContainer.visibility = View.GONE
        Log.d("alx", response.quotes::class.members.toString())

        val objectMembers = response.quotes::class.members.filter { kClass ->
            kClass.returnType.toString().contains("kotlin.Double")
        }
        listOfItems = createListOfCoins(objectMembers)
        successView()
    }

    private fun createListOfCoins(objectMembers: List<KCallable<*>>): MutableList<String> {
        val items = mutableListOf<String>()

        for (objectName: KCallable<*> in objectMembers) {

            if (objectName.name.contains("us"))
                items.add(objectName.name)

            Log.d("alxList", "{*** ")
            Log.d("alxList", objectName.name)
            Log.d("alxList", objectName.typeParameters.toString())
            Log.d("alxList", " ***}")
        }

        return items
    }

    private fun successView() {
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

    }
}

class EventHandler {
    fun onHandleClick(view: View) {
        val action = ConversionFragmentDirections
            .actionConversionFragmentToDetailConversionFragment("ho")

        view.findNavController().navigate(action)
    }
}
