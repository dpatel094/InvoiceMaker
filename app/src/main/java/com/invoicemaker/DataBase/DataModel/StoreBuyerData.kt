package com.invoicemaker.DataBase.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_buyer_data")
data class StoreBuyerData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var buyer_Name: String = "",
    var buyer_Address: String = "",
    var buyer_GstNo: String = "",
    var buyer_StateCode: String = "",
    var buyer_Contact: String = "",
    var buyer_Email: String = "",
    var invoiceDate: String = "",
    var termOfPayment: String = "",
    var buy_uId: String = ""
)