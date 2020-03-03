package com.grupo7.moneychange.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem

@BindingAdapter("bind_item_data")
fun setRecycleViewDataItems(recyclerView: RecyclerView, items: List<HistoryItem>) {
    recyclerView.adapter.takeIf {
        it is IRecyclerViewAdapter
    }.let {
        (it as IRecyclerViewAdapter).update(items)
    }
}