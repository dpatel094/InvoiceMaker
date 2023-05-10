package com.invoicemaker.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.Repository.InvoiceRepository

class InvoiceViewModel : ViewModel() {
    var uploadDataSuccess: MutableLiveData<Boolean?> = MutableLiveData()
    var profileDataModel: MutableLiveData<ProfileDataModel?> = MutableLiveData()
    var invoiceRepository: InvoiceRepository = InvoiceRepository()


    init {
        uploadDataSuccess = invoiceRepository.uploadDataSuccess
        profileDataModel = invoiceRepository.profileDataModel
    }
    fun PostPrefillToServer(profileDataModel: ProfileDataModel, profileActivity: Context) {
        invoiceRepository.UploadPrefillData(profileDataModel,profileActivity)
    }

    fun GetPrefillFromServer(){
        invoiceRepository.getPrefillFromServer()
    }

}