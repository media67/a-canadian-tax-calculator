package com.exb67.acanadiantaxcalculator.domain

enum class GeneralData(val title: String = "", val taxAmount: Float = 0f, val QmData: QmData? = null) {
    DEFAULT(),
    ALBERTA("Alberta", 0f, QmData()),
    BRITISH_COLUMBIA("British Columbia", 7f, QmData()),
    MANITOBA("Manitoba", 7f, QmData()),
    NEW_BRUNSWICK("New Brunswick", 10f, QmData(0.09f, 0.1f)),
    NEWFOUNDLAND_AND_LABRADOR("Newfoundland and Labrador", 10f, QmData(0.09f, 0.1f)),
    NORTHWEST_TERRITORIES("Northwest Territories", 0f, QmData()),
    NOVA_SCOTIA("Nova Scotia", 10f, QmData(0.09f, 0.01f)),
    NUNAVUT("Nunavut", 0f, QmData()),
    ONTARIO("Ontario", 8f, QmData(0.078f, 0.088f)),
    PRINCE_EDWARD_ISLAND("Prince Edward Island", 10f, QmData(0.09f, 0.1f)),
    QUEBEC("Quebec", 9.975f, QmData(taxSaleGst = 0.018f, taxSaleQst = 0.034f, taxRawGst = 0.05f, taxRawQst = 0.09975f, taxServiceGst30 = 0.026f, taxServiceGstRest = 0.036f, taxServiceQst30 = 0.056f, taxServiceQstRest = 0.066f)),
    SASKATCHEWAN("Saskatchewan", 6f, QmData()),
    YUKON("Yukon", 0f, QmData())
}

data class QmData (
    val tax30: Float? = 0.026f,
    val taxRest: Float? = 0.036f,
    val taxSaleGst: Float? = null,
    val taxSaleQst: Float? = null,
    val taxRawGst: Float? = null,
    val taxRawQst: Float? = null,
    val taxServiceGst30: Float? = null,
    val taxServiceGstRest: Float? = null,
    val taxServiceQst30: Float? = null,
    val taxServiceQstRest: Float? = null,
)