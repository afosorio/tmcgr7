package com.grupo7.moneychange.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.grupo7.moneychange.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var list_of_items = arrayOf("USD", "COP", "EUR")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, R.layout.spinner_item, list_of_items)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        conversion_from_spinner!!.adapter = adapter
        conversion_to_spinner!!.adapter = adapter
    }
}
