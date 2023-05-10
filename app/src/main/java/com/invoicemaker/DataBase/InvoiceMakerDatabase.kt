package com.invoicemaker.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.invoicemaker.DataBase.DataAcessObject.StoreInvoiceDAO
import com.invoicemaker.DataBase.DataModel.StoreInvoiceData

@Database(entities = [StoreInvoiceData::class], version = 1, exportSchema = false)
abstract class InvoiceMakerDatabase : RoomDatabase() {
    abstract fun getDao(): StoreInvoiceDAO
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
                        ).build()
                    }
                }
            }
            return instance
        }
    }
}