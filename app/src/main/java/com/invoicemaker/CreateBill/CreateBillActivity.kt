package com.invoicemaker.CreateBill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.R
import com.invoicemaker.ViewModel.InvoiceViewModel
import com.invoicemaker.databinding.ActivityCreateBillBinding
import com.invoicemaker.databinding.ActivityProfileBinding

class CreateBillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBillBinding
    var invoiceViewModel: InvoiceViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_bill)
        binding.toolbar.title = "Create Bill"
        invoiceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[InvoiceViewModel::class.java]


        binding.subBtn.setOnClickListener {

           // displayGreeting()

        }
    }
    private fun displayGreeting() {
        binding.apply {
            if (binding.billingModel!!.buyerName.isBlank() && binding.billingModel!!.buyerName.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter Company Name", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.billingModel!!.buyerAddress.isBlank() && binding.billingModel!!.buyerAddress.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter Address", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.billingModel!!.buyerGstNo.isBlank() && binding.billingModel!!.buyerGstNo.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter GST No.", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.billingModel!!.buyerStateCode.isBlank() && binding.billingModel!!.buyerStateCode.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter State GSt", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.billingModel!!.buyerContact.isBlank() && binding.billingModel!!.buyerContact.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter Centre GST", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.billingModel!!.buyerEmail.isBlank() && binding.billingModel!!.buyerEmail.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter Declaration", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.billingModel!!.invoiceDate.isBlank() && binding.billingModel!!.invoiceDate.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter Invoice Message", Toast.LENGTH_LONG)
                    .show()
                return@apply
            } else if (binding.billingModel!!.termOfPayment.isBlank() && binding.billingModel!!.termOfPayment.isEmpty()) {
                Toast.makeText(this@CreateBillActivity, "Enter Terms Of Delivery", Toast.LENGTH_LONG)
                    .show()
                return@apply
            } else {
//                val dialogFragment = Progress_Dialog()
//                dialogFragment.show(supportFragmentManager, "My  Fragment")
                val profileDataModel = ProfileDataModel(
                    binding.billingModel!!.userName,
                    binding.billingModel!!.cName,
                    binding.billingModel!!.address,
                    binding.billingModel!!.gstNo,
                    binding.billingModel!!.sGst,
                    binding.billingModel!!.cGst,
                    binding.billingModel!!.declaration,
                    binding.billingModel!!.invMsg,
                    binding.billingModel!!.tod,
                    binding.billingModel!!.other
                )
                invoiceViewModel!!.PostPrefillToServer(profileDataModel, this@CreateBillActivity)
            }

        }
    }
}