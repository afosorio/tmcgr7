package com.grupo7.moneychange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.entity.History
import com.grupo7.moneychange.databinding.ConversionFragmentBinding

class IRecyclerViewAdapter : RecyclerView.Adapter<IRecyclerViewAdapter.ViewHolder>() {

    private var items: List<History> = TODO()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ItemViewHolder(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder && items.size > position) {
            holder.bind(items[position])
        }
    }

    fun update(items: List<History>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("recycler_items")
        fun RecyclerView.bindItems(items: List<History>) {
            val adapter = adapter as IRecyclerViewAdapter
            adapter.update(items)
        }
   }

   abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ConversionFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_history, parent, false )
    ) : ViewHolder(binding.root) {

        fun bind(item: History) {
            binding.historyTitle
        }
    }
}