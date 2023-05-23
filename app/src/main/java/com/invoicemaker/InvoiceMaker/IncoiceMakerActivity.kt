package com.invoicemaker.InvoiceMaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.invoicemaker.R

class IncoiceMakerActivity : AppCompatActivity() {
    var recyclerView : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoice_maker)
        
    }
}