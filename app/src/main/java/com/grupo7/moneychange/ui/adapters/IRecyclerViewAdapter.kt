package com.grupo7.moneychange.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.moneychange.R
import com.grupo7.domain.History
import kotlinx.android.synthetic.main.item_history.view.*

typealias Listener = ((History) -> Unit)

class IRecyclerViewAdapter(private val clickDataUp: Listener, private val clickDetailHistory: Listener) :
    RecyclerView.Adapter<IRecyclerViewAdapter.ViewHolder>() {

    private var items = emptyList<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_history, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickDataUp, clickDetailHistory)

    fun update(items: List<History>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(item: History, clickDataUp: Listener, clickDetailHistory: Listener) {
            with(itemView){
                from_text_value?.text = item.valueFrom.toString()
                from_text?.text = item.currencyFrom.toString()
                val toText = item.currencyTo.toString()
                //TODO("Se debe verificar la siguiente linea, ya que hace crachear la app")
                //to_text?.text = toText.subSequence(toText.length - 3, toText.length)
                to_text_value?.text = item.valueTo.toString()
                data_up?.setOnClickListener { clickDataUp(item) }
                card_parent?.setOnClickListener { clickDetailHistory(item) }
            }
        }
    }
}