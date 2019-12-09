package ru.shepetov.navicotest

import android.annotation.TargetApi
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
/*

object SearchViewBindingAdapter {
    @BindingAdapter(
        value = ["bind:onQueryTextSubmit", "bind:onQueryTextChange"],
        requireAll = false
    )
    fun setOnQueryTextListener(
        view: SearchView,
        submit: ((String) -> Boolean)?,
        change: ((String) -> Boolean)?
    ) {
        if (submit == null && change == null) {
            view.setOnQueryTextListener(null)
            return
        }

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return submit?.invoke(query ?: "") ?: false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return change?.invoke(newText ?: "") ?: false
            }
        })
    }
//
//    @BindingAdapter(
//        value = ["bind:onSuggestionSelect", "bind:onSuggestionClick"],
//        requireAll = false
//    )
//    fun setOnSuggestListener(
//        view: SearchView,
//        submit: OnSuggestionSelect?,
//        change: OnSuggestionClick?
//    ) {
//        if (submit == null && change == null) {
//            view.setOnSuggestionListener(null)
//        } else {
//            view.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
//                override fun onSuggestionSelect(position: Int): Boolean {
//                    return submit?.onSuggestionSelect(position) ?: false
//                }
//
//                override fun onSuggestionClick(position: Int): Boolean {
//                    return change?.onSuggestionClick(position) ?: false
//                }
//            })
//        }
//    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    interface OnQueryTextSubmit {
        fun onQueryTextSubmit(query: String?): Boolean
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    interface OnQueryTextChange {
        fun onQueryTextChange(newText: String?): Boolean
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    interface OnSuggestionSelect {
        fun onSuggestionSelect(position: Int): Boolean
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    interface OnSuggestionClick {
        fun onSuggestionClick(position: Int): Boolean
    }
}*/
