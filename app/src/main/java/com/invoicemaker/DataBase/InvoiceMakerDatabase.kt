package com.invoicemaker.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.invoicemaker.DataBase.DataAcessObject.StoreBuyerDAO
import com.invoicemaker.DataBase.DataAcessObject.StoreInvoiceDAO
import com.invoicemaker.DataBase.DataAcessObject.StorePrefillDAO
import com.invoicemaker.DataBase.DataModel.StoreBuyerData
import com.invoicemaker.DataBase.DataModel.StoreInvoiceData
import com.invoicemaker.DataBase.DataModel.StorePreFillData

@Database(entities = [StoreInvoiceData::class,StorePreFillData::class,StoreBuyerData::class], version = 1, exportSchema = false)
abstract class InvoiceMakerDatabase : RoomDatabase() {
    abstract fun getDao(): StoreInvoiceDAO
    abstract fun storePrefill(): StorePrefillDAO
    abstract fun storeBuyerData(): StoreBuyerDAO
    companion object {
        private const val DATABASE_NAME = "INVOICEDATABASE"

        @Volatile
        var instance: InvoiceMakerDatabase? = null
        fun getInstance(context: Context): InvoiceMakerDatabase? {
            if (instance == null) {
                synchronized(InvoiceMakerDatabase::class.java)
                {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context, InvoiceMakerDatabase::class.java,
                            DATABASE_NAME
                        ).allowMainThreadQueries().build()
                    }
                }
            }
            return instance
        }
    }
}