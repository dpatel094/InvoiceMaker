package com.invoicemaker.DataBase.DataAcessObject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.invoicemaker.DataBase.DataModel.StoreInvoiceData

@Dao
interface  StoreInvoiceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: StoreInvoiceData)
}