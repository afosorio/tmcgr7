package com.grupo7.moneychange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.entity.History
import kotlinx.android.synthetic.main.item_history.view.*

class IRecyclerViewAdapter : RecyclerView.Adapter<IRecyclerViewAdapter.ViewHolder>() {

    private var items = emptyList<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_history, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun update(items: List<History>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: History) {
            itemView.conversion_text.text = "From = ${item.valueFrom} -  To = ${item.valueTo}"
        }
    }
}