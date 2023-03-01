package com.exb67.acanadiantaxcalculator.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exb67.acanadiantaxcalculator.domain.GeneralData

const val TAG = "test"
class TaxesViewModel: ViewModel() {
    private val _beforeTaxAmount = MutableLiveData<Int>()
    val beforeTaxAmount: LiveData<Int> = _beforeTaxAmount

    private val _provData = MutableLiveData<GeneralData>(GeneralData.ALBERTA)
    val provData: LiveData<GeneralData> = _provData


    fun setProvince(title: String) {
        val provinceMap = mapOf(
            "Alberta" to GeneralData.ALBERTA,
            "British Columbia" to GeneralData.BRITISH_COLUMBIA,
            "Manitoba" to GeneralData.MANITOBA,
            "New Brunswick" to GeneralData.NEW_BRUNSWICK,
            "Newfoundland and Labrador" to GeneralData.NEWFOUNDLAND_AND_LABRADOR,
            "Northwest Territories" to GeneralData.NORTHWEST_TERRITORIES,
            "Nova Scotia" to GeneralData.NOVA_SCOTIA,
            "Nunavut" to GeneralData.NUNAVUT,
            "Ontario" to GeneralData.ONTARIO,
            "Prince Edward Island" to GeneralData.PRINCE_EDWARD_ISLAND,
            "Quebec" to GeneralData.QUEBEC,
            "Saskatchewan" to GeneralData.SASKATCHEWAN,
            "Yukon" to GeneralData.YUKON
        )
        Log.d(TAG, "setProvince: $title")
        _provData.value = provinceMap[title] ?: GeneralData.DEFAULT
    }
}