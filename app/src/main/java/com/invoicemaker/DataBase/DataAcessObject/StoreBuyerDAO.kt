package com.invoicemaker.DataBase.DataAcessObject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.invoicemaker.DataBase.DataModel.StoreBuyerData

@Dao
interface  StoreBuyerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(storePreFillData: StoreBuyerData)

    @Query("SELECT * FROM store_buyer_data")
   fun getBuyerData() : List<StoreBuyerData>

    suspend fun insertOrUpdate(storeBuyerData: StoreBuyerData){
        val itemsFromDB: List<StoreBuyerData> = getItemById(storeBuyerData.buyer_Name)
        if (itemsFromDB.isNullOrEmpty()) {
            insert(storeBuyerData)
        }else{
            updateBuyerData(storeBuyerData.buyer_Name,
                storeBuyerData.buyer_Address,
                storeBuyerData.buyer_GstNo,
                storeBuyerData.buyer_StateCode,
                storeBuyerData.buyer_Contact,
                storeBuyerData.buyer_Email,
                storeBuyerData.invoiceDate,
                storeBuyerData.termOfPayment,
                storeBuyerData.buy_uId,
                itemsFromDB[0].id)
        }
    }
    @Query("SELECT * FROM store_buyer_data WHERE buyer_Name = :bName")
    fun getItemById(bName: String): List<StoreBuyerData>
    @Query("UPDATE store_buyer_data SET buyer_Name = :buyerName ,buy_uId = :buyUid, buyer_Address = :buyerAddress,buyer_GstNo = :buyerGstno, buyer_StateCode = :buyerStatecode ,buyer_Contact = :buyerContact, buyer_Email = :buyerEmail , termOfPayment = :termOfPayment, invoiceDate = :invoiceDate  WHERE id = :id")
    fun updateBuyerData(
    buyerName: String,
    buyerAddress: String,
    buyerGstno: String,
    buyerStatecode: String,
    buyerContact: String,
    buyerEmail: String,
    invoiceDate: String,
    termOfPayment: String,
    buyUid: String,
    id: Int
)

    @Query("SELECT * FROM store_buyer_data WHERE buyer_Name LIKE '%' ||  :bName || '%'")
    fun getSearchResult(bName: String): List<StoreBuyerData>
}