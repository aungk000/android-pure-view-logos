package me.ako.logos.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Logo>>()
    val items: LiveData<List<Logo>> get() = _items

    init {
        loadData()
    }

    private fun loadData() {
        val list = arrayListOf<Logo>()
        list.add(Logo(1, "Netflix", TAG.NETFLIX))
        list.add(Logo(2, "Microsoft", TAG.MICROSOFT))
        list.add(Logo(3, "Youtube", TAG.YOUTUBE))
        _items.postValue(list)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AppViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}