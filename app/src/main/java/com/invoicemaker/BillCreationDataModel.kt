package com.invoicemaker
data class BillCreationDataModel(
    var buyer_Name: String = "",
    var buyer_Address: String = "",
    var buyer_GstNo: String = "",
    var buyer_StateCode: String= "",
    var buyer_Contact: String = "",
    var buyer_Email: String = "",
    var invoiceDate: String = "",
    var termOfPayment: String = "",
    var buy_uId : String = ""
)