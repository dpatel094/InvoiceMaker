package com.invoicemaker.CreateBill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.invoicemaker.BillCreationDataModel
import com.invoicemaker.InvoiceMaker.IncoiceMakerActivity
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.R
import com.invoicemaker.Setting.ProfileActivity
import com.invoicemaker.Utils.Utils
import com.invoicemaker.ViewModel.InvoiceViewModel
import com.invoicemaker.databinding.ActivityCreateBillBinding

class CreateBillActivity : AppCompatActivity() {
    lateinit var currentbinding: ActivityCreateBillBinding
    var invoiceViewModel: InvoiceViewModel? = null
    var utils: Utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils = Utils()
        currentbinding = DataBindingUtil.setContentView(this, R.layout.activity_create_bill)

        currentbinding.toolbar.title = "Create Bill"
        invoiceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[InvoiceViewModel::class.java]

        invoiceViewModel?.uploadDataSuccess?.observe(this,Observer{

                val intent = Intent(this, IncoiceMakerActivity::class.java)
                startActivity(intent)

        })
        invoiceViewModel?.billCreationDataModel?.observe(this, Observer {
            if (it != null) {

                currentbinding.billingBindingModel = getCompany(it)
            }
        })
        invoiceViewModel?.searchModelData?.observe(this, Observer {
            if (it != null) {
                currentbinding.billingBindingModel = getCompany(it)
            }
        })

        currentbinding.edtBuyerName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (editable!!.length >= 3) {
                    currentbinding.detailActivity.visibility = View.VISIBLE
                    if (editable.length in 3..8) {
                        invoiceViewModel!!.SearchBuyerDetail(
                            editable.toString(),
                            this@CreateBillActivity
                        )
                    }
                } else {
                    currentbinding.detailActivity.visibility = View.GONE
                }
            }

        })

        currentbinding.subBtnBuyer.setOnClickListener {

            displayGreeting()

        }
        //getBuyerDataDataFromServer()
    }

    private fun getBuyerDataDataFromServer() {
        invoiceViewModel!!.GetBuyerDetailToServer()
    }

    private fun displayGreeting() {
        try {
            currentbinding.apply {
                if (currentbinding.edtBuyerName.text.toString()
                        .isBlank() && currentbinding.edtBuyerName.text.toString().isEmpty()
                ) {
                    Toast.makeText(this@CreateBillActivity, "Enter Buyer Name", Toast.LENGTH_LONG)
                        .show()
                    return@apply
                } else
                    if (currentbinding.edtbuyerAddress.text.toString()
                            .isBlank() && currentbinding.edtbuyerAddress.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer Address",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else if (currentbinding.edtbuyerGstNo.text.toString()
                            .isBlank() && currentbinding.edtbuyerGstNo.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer GST No.",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else if (currentbinding.edtbuyerStateCode.text.toString()
                            .isBlank() && currentbinding.edtbuyerStateCode.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer State Code",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else if (currentbinding.edtbuyerContact.text.toString()
                            .isBlank() && currentbinding.edtbuyerContact.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer Contact",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else if (currentbinding.edtbuyerEmail.text.toString()
                            .isBlank() && currentbinding.edtbuyerEmail.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer E-Mail",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else if (currentbinding.edtinvoiceDate.text.toString()
                            .isBlank() && currentbinding.edtinvoiceDate.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer Invoice",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else if (currentbinding.edttermOfPayment.text.toString()
                            .isBlank() && currentbinding.edttermOfPayment.text.toString().isEmpty()
                    ) {
                        Toast.makeText(
                            this@CreateBillActivity,
                            "Enter Buyer Terms Of Payment",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        return@apply
                    } else {
                        //                val dialogFragment = Progress_Dialog()
                        //                dialogFragment.show(supportFragmentManager, "My  Fragment")
                        val billCreationDataModel = BillCreationDataModel(
                            currentbinding.edtBuyerName.text.toString(),
                            currentbinding.edtbuyerAddress.text.toString(),
                            currentbinding.edtbuyerGstNo.text.toString(),
                            currentbinding.edtbuyerStateCode.text.toString(),
                            currentbinding.edtbuyerContact.text.toString(),
                            currentbinding.edtbuyerEmail.text.toString(),
                            currentbinding.edtinvoiceDate.text.toString(),
                            currentbinding.edttermOfPayment.text.toString(),
                            utils.generateUniqueId()

                        )
                        invoiceViewModel!!.PostBuyerDetailToserver(
                            billCreationDataModel,
                            this@CreateBillActivity
                        )
//                        val intent = Intent(this@CreateBillActivity, IncoiceMakerActivity::class.java)
//                        startActivity(intent)
                    }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCompany(currentbinding: BillCreationDataModel?): BillCreationDataModel {

        return BillCreationDataModel(
            currentbinding!!.buyer_Name,
            currentbinding.buyer_Address,
            currentbinding.buyer_GstNo,
            currentbinding.buyer_StateCode,
            currentbinding.buyer_Contact,
            currentbinding.buyer_Email,
            currentbinding.invoiceDate,
            currentbinding.termOfPayment,
            currentbinding.buy_uId
        )
    }
}