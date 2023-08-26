package com.example.currencyapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyapp.adapter.CountriesListAdapter
import com.example.currencyapp.databinding.ActivityMainBinding
import com.example.currencyapp.model.Country
import com.example.currencyapp.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var fromAdapter: CountriesListAdapter
    private lateinit var toAdapter: CountriesListAdapter

    val viewModel: CurrencyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Adding fixed countries
        val countries = listOf(
            Country(1, "US","USD"),
            Country(2, "Japane","JPY"),
            Country(3, "UK","GBP"),
            Country(3, "Chine","CNY"),
            Country(3, "Switzerland","CHF"),
            Country(3, "Canada","CAD"),
            Country(3, "Australia","aud")
        )
        fromAdapter = CountriesListAdapter(this, countries) // Initialize the adapter
        toAdapter = CountriesListAdapter(this, countries) // Initialize the adapter

        binding.from.adapter=fromAdapter
        binding.to.adapter=toAdapter

        binding.from.onItemSelectedListener = onItemSelectedListener(countries,"FROM")
        binding.to.onItemSelectedListener = onItemSelectedListener(countries,"TO")

        binding.switchCountry.setOnClickListener {
            binding.from.setSelection(countries.indexOf( viewModel.selectedToCountry.value))
            binding.to.setSelection(countries.indexOf(viewModel.selectedFromCountry.value))
            val fromCountry=viewModel.selectedFromCountry.value
            viewModel.selectedFromCountry.value=viewModel.selectedToCountry.value
            viewModel.selectedToCountry.value=fromCountry
        }

        binding.moneyAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text changes.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val newText = s.toString()
                viewModel.onConvert(viewModel.selectedFromCountry.value!!.name,
                    viewModel.selectedToCountry.value!!.name,
                    newText
                    )
            }
        })

        viewModel.convertedData.observe(this) {
            if (it!=null)
                binding.convertedAmount.text=it.query.amount
        }

    }

    private fun onItemSelectedListener(countries: List<Country>,listenerType:String) =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCountry = countries[position]

                if(listenerType.equals("FROM")){
                    viewModel.selectedFromCountry.value = selectedCountry
                }else{
                    viewModel.selectedToCountry.value = selectedCountry
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }
}