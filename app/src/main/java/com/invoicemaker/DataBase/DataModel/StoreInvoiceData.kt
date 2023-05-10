package com.invoicemaker.DataBase.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "StoreInvoiceData")
data class StoreInvoiceData(
    var data: String,
    var date: String,
    var characters: Long
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}