package com.grupo7.moneychange.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.moneychange.R
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import kotlinx.android.synthetic.main.item_history.view.*

typealias Listener = ((HistoryItem) -> Unit)

class IRecyclerViewAdapter(private val clickDataUp: Listener, private val clickDetailHistory: Listener) :
    RecyclerView.Adapter<IRecyclerViewAdapter.ViewHolder>() {

    private var items = emptyList<HistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_history, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickDataUp, clickDetailHistory)
        holder.itemView.setOnClickListener { clickDetailHistory(items[position]) }
    }

    fun update(items: List<HistoryItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(item: HistoryItem, clickDataUp: Listener, clickDetailHistory: Listener) {
            with(itemView) {
                from_text_value?.text = item.valueFrom.toString()
                val fromText = item.currencyFrom
                from_text?.text = if (fromText.length > 3) {
                    fromText.subSequence(fromText.length - 3, fromText.length)
                } else fromText
                val toText = item.currencyTo
                to_text?.text = if (toText.length > 3) {
                    toText.subSequence(toText.length - 3, toText.length)
                } else toText
                to_text_value?.text = item.valueTo.toString()
                data_up?.setOnClickListener { clickDataUp(item) }
            }
        }
    }
}