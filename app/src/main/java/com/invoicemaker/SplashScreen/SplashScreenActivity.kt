package com.invoicemaker.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.invoicemaker.Dashboard.DashBoardActivity
import com.invoicemaker.R


class SplashScreenActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(Runnable { // This method will be executed once the timer is over
            val i = Intent(this, DashBoardActivity::class.java)
            startActivity(i)
            finish()
        }, 500)
    }
}