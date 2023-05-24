package com.invoicemaker.Setting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.R
import com.invoicemaker.Repository.InvoiceRepository
import com.invoicemaker.Utils.Utils
import com.invoicemaker.ViewModel.InvoiceViewModel
import com.invoicemaker.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    var invoiceViewModel: InvoiceViewModel? = null
    var utils: Utils = Utils()
//    var invoiceRepository: InvoiceRepository? = InvoiceRepository(application)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils.context = this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.toolbar.title = "Profile"
        invoiceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[InvoiceViewModel::class.java]

        invoiceViewModel?.uploadDataSuccess?.observe(this, Observer {
            if (it == true) {
                Toast.makeText(this, "s", Toast.LENGTH_LONG).show();
            }
        })
        invoiceViewModel?.profileDataModel?.observe(this, Observer {
            if (it != null) {
                binding.databingmodel = getCompany(it)
            }
        })



        binding.subBtn.setOnClickListener {

            displayGreeting()

        }

        getPrefillDataFromServer()
    }

    private fun getPrefillDataFromServer() {
        invoiceViewModel!!.getPrefillData()
    }

    private fun displayGreeting() {
        binding.apply {
            if (binding.databingmodel!!.cName.isBlank() && binding.databingmodel!!.cName.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Company Name", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.databingmodel!!.address.isBlank() && binding.databingmodel!!.address.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Address", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.databingmodel!!.gstNo.isBlank() && binding.databingmodel!!.gstNo.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter GST No.", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.databingmodel!!.sGst.isBlank() && binding.databingmodel!!.sGst.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter State GSt", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.databingmodel!!.cGst.isBlank() && binding.databingmodel!!.cGst.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Centre GST", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.databingmodel!!.declaration.isBlank() && binding.databingmodel!!.declaration.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Declaration", Toast.LENGTH_LONG).show()
                return@apply
            } else if (binding.databingmodel!!.invMsg.isBlank() && binding.databingmodel!!.invMsg.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Invoice Message", Toast.LENGTH_LONG)
                    .show()
                return@apply
            } else if (binding.databingmodel!!.tod.isBlank() && binding.databingmodel!!.tod.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Terms Of Delivery", Toast.LENGTH_LONG)
                    .show()
                return@apply
            } else if (binding.databingmodel!!.other.isBlank() && binding.databingmodel!!.other.isEmpty()) {
                Toast.makeText(this@ProfileActivity, "Enter Other", Toast.LENGTH_LONG).show()
                return@apply
            } else {
                val profileDataModel = ProfileDataModel(
                    binding.databingmodel!!.userName,
                    binding.databingmodel!!.cName,
                    binding.databingmodel!!.address,
                    binding.databingmodel!!.gstNo,
                    binding.databingmodel!!.sGst,
                    binding.databingmodel!!.cGst,
                    binding.databingmodel!!.declaration,
                    binding.databingmodel!!.invMsg,
                    binding.databingmodel!!.tod,
                    binding.databingmodel!!.other
                )
                invoiceViewModel!!.PostPrefillToServer(profileDataModel, this@ProfileActivity)
            }

        }
    }

    private fun getCompany(ProfileDataModel: ProfileDataModel?): ProfileDataModel {
        return ProfileDataModel(
            "",
            ProfileDataModel!!.cName,
            ProfileDataModel.address,
            ProfileDataModel.gstNo,
            ProfileDataModel.sGst,
            ProfileDataModel.cGst,
            ProfileDataModel.declaration,
            ProfileDataModel.invMsg,
            ProfileDataModel.tod,
            ProfileDataModel.other
        )
    }

}