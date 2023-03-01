package com.exb67.acanadiantaxcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.exb67.acanadiantaxcalculator.databinding.FragmentProvincialTaxesBinding
import com.exb67.acanadiantaxcalculator.model.TaxesViewModel

class ProvincialTaxesFragment: Fragment() {
    private var _binding: FragmentProvincialTaxesBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: TaxesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentProvincialTaxesBinding.inflate(inflater, container, false)
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