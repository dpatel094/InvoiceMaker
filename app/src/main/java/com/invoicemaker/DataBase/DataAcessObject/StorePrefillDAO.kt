package com.invoicemaker.DataBase.DataAcessObject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.invoicemaker.DataBase.DataModel.StorePreFillData

@Dao
interface  StorePrefillDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(storePreFillData: StorePreFillData)

    @Query("SELECT * FROM store_pre_fill")
   fun getPreFillData() : List<StorePreFillData>
}