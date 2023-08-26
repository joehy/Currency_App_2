package com.example.currencyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.currencyapp.R
import com.example.currencyapp.databinding.CountriesListItemBinding
import com.example.currencyapp.model.Country

class CountriesListAdapter(
    context: Context,
    private val items: List<Country> // Replace with your ViewModel class
) : ArrayAdapter<Country>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: CountriesListItemBinding = if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            DataBindingUtil.inflate(inflater, R.layout.countries_list_item, parent, false)
        } else {
            DataBindingUtil.getBinding(convertView)!!
        }

        binding.country = getItem(position)
        binding.executePendingBindings()
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}
