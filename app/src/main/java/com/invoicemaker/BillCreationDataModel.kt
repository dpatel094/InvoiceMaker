package com.invoicemaker





data class BillCreationDataModel(
    var buyerName: String = "",
    var buyerAddress: String = "",
    var buyerGstNo: String = "",
    var buyerStateCode: String= "",
    var buyerId: String= "",
    var buyerContact: String = "",
    var buyerEmail: String = "",
    var invoiceDate: String = "",
    var termOfPayment: String = "",
)