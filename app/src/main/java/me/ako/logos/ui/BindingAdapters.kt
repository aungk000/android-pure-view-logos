package me.ako.logos.ui

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import me.ako.logos.model.Logo

@BindingAdapter("listData")
fun bindList(recyclerView: RecyclerView, list: List<Logo>?) {
    list?.let {
        val adapter = recyclerView.adapter as LogoAdapter
        adapter.submitList(it)
    }
}