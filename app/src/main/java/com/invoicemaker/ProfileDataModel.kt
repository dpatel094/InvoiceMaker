package com.invoicemaker

import android.util.Patterns




data class ProfileDataModel(
    var userName: String = "",
    var cName: String = "",
    var address: String = "",
    var gstNo: String= "",
    var sGst: String= "",
    var cGst: String = "",
    var declaration: String = "",
    var invMsg: String = "",
    var tod: String = "",
    var other: String = ""
)