package com.invoicemaker.Dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.invoicemaker.CreateBill.CreateBillActivity
import com.invoicemaker.R
import com.invoicemaker.Setting.ProfileActivity


class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_dash_board)

        setSupportActionBar(findViewById(R.id.toolbar))


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.id_setting ->
            {
                val intent=Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.id_menu ->
            {
                val intent=Intent(this, CreateBillActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}