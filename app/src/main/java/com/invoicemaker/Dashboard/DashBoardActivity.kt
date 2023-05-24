package com.invoicemaker.Dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.Stetho
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.invoicemaker.CreateBill.CreateBillActivity
import com.invoicemaker.R
import com.invoicemaker.Setting.ProfileActivity
import com.invoicemaker.ViewModel.InvoiceViewModel


class DashBoardActivity : AppCompatActivity() {
    var floatingActionButton: FloatingActionButton? = null
    var invoiceViewModel: InvoiceViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        Stetho.initializeWithDefaults(this)
        invoiceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[InvoiceViewModel::class.java]

        getPrefillDataFromServer()
        getBuyerDataDataFromServer()
        setSupportActionBar(findViewById(R.id.toolbar))
        floatingActionButton = findViewById(R.id.create_invoice)
        floatingActionButton!!.setOnClickListener {
            val intent = Intent(this, CreateBillActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.id_setting -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getPrefillDataFromServer() {
        invoiceViewModel!!.GetPrefillFromServer()
    }
    private fun getBuyerDataDataFromServer() {
        invoiceViewModel!!.GetBuyerDetailToServer()
    }
}