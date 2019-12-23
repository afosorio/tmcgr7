package com.grupo7.moneychange.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.moneychange.data.local.entity.History

@BindingAdapter("bind_item_data")
fun setRecycleViewDataItems(recyclerView: RecyclerView, items: List<History>) {
    recyclerView.adapter.takeIf {
        it is IRecyclerViewAdapter
    }.let {
        (it as IRecyclerViewAdapter).update(items)
    }
}