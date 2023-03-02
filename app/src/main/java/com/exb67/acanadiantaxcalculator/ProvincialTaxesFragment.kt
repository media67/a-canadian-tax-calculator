package com.exb67.acanadiantaxcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.exb67.acanadiantaxcalculator.databinding.FragmentProvincialTaxesBinding
import com.exb67.acanadiantaxcalculator.model.TaxesViewModel

const val TAG = "observed"

class ProvincialTaxesFragment : Fragment() {
    private var _binding: FragmentProvincialTaxesBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: TaxesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProvincialTaxesBinding.inflate(inflater, container, false)
        binding.viewModel = sharedViewModel
        sharedViewModel.provData.observe(viewLifecycleOwner) {
            val calcEnabled = sharedViewModel.checkCalc()
            binding.calculateButton.isEnabled = calcEnabled
            if (calcEnabled) sharedViewModel.calculateTaxes()

        }
        sharedViewModel.total.observe(viewLifecycleOwner) {
            binding.total.text = sharedViewModel.total.value.toString()
        }
        sharedViewModel.beforeTaxAmount.observe(viewLifecycleOwner) {
            val calcEnabled = sharedViewModel.checkCalc()
            binding.calculateButton.isEnabled = calcEnabled
            if (calcEnabled) sharedViewModel.calculateTaxes()
        }

        binding.beforeTaxAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that the text is about to change.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that the text has changed.
                Log.d(TAG, "onTextChanged: $s")
                var amountBeforeTax = 0f
                if (!s.isNullOrEmpty() && s.toString() != ".") {
                    amountBeforeTax = String.format("%.2f", s.toString().toFloat()).toFloat()
                } else {
                    sharedViewModel.resetTotal()
                }
                Log.d(TAG, "amountBeforeTax: $amountBeforeTax")
                sharedViewModel.setBeforeTaxAmount(amountBeforeTax)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called to notify you that the text has been changed and the user is no longer typing.
            }
        })

        binding.generalDataSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                    resources.getStringArray(R.array.general_data_titles)[pos].apply {
                        sharedViewModel.setProvince(this)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        binding.calculateButton.setOnClickListener {
            sharedViewModel.calculateTaxes()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.provincialTaxesFragment = this
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}