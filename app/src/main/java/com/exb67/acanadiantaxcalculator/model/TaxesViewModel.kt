package com.exb67.acanadiantaxcalculator.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exb67.acanadiantaxcalculator.domain.GeneralData

const val TAG = "test"
const val GST = 5f

class TaxesViewModel : ViewModel() {
    private val _beforeTaxAmount = MutableLiveData(0f)
    val beforeTaxAmount: LiveData<Float> = _beforeTaxAmount

    private val _provData = MutableLiveData(GeneralData.DEFAULT)
    val provData: LiveData<GeneralData> = _provData

    private var _total = MutableLiveData(0f)
    val total: LiveData<Float> = _total

    fun setBeforeTaxAmount(amount: Float?) {
        _beforeTaxAmount.value = amount
        Log.d(TAG, "setBeforeTaxAmount: $_beforeTaxAmount.value")
    }

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

    fun calculateTaxes() {
        val amount: Float? = beforeTaxAmount.value
        val firstTax = amount?.takeIf { it > 0 }?.run {
            times(provData.value!!.taxAmount.div(100))
        }
        val gstTax = amount?.takeIf { it > 0 }?.run {
            times(GST.div(100))
        }
        _total.value = amount?.plus(firstTax!!)?.plus(gstTax!!)
        Log.d(TAG, "calculateTaxes: ${total.value}")
    }

    fun resetTotal() {
        _total.value = 0f
    }
    fun checkCalc(): Boolean {
        Log.d(TAG, "checkCalc: called")
        return provData.value != GeneralData.DEFAULT && beforeTaxAmount.value != 0.0f
    }

//    fun calculateQCQuickMethodAmount(amount: Double, data: GeneralData):Double {
//        val total = if (amount <= amount) {
//            amount.times(data.qmData.tax30)
//        } else {
//            val firstThirty = amount.times(data.qmData.tax30)
//            val remaining = (amount - amount).times(data.qmData.taxRest)
//            firstThirty + remaining
//        }
//
//        return total
//    }
}