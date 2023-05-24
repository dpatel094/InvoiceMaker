package com.invoicemaker.DataBase.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_pre_fill")
data class StorePreFillData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
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
