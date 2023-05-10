package com.invoicemaker.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.invoicemaker.BillCreationDataModel
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.Repository.InvoiceRepository

class InvoiceViewModel : ViewModel() {
    var uploadDataSuccess: MutableLiveData<Boolean?> = MutableLiveData()
    var profileDataModel: MutableLiveData<ProfileDataModel?> = MutableLiveData()
    var billCreationDataModel: MutableLiveData<BillCreationDataModel?> = MutableLiveData()
    var searchModelData: MutableLiveData<BillCreationDataModel?> = MutableLiveData()
    var invoiceRepository: InvoiceRepository = InvoiceRepository()


    init {
        uploadDataSuccess = invoiceRepository.uploadDataSuccess
        profileDataModel = invoiceRepository.profileDataModel
        billCreationDataModel = invoiceRepository.billCreationDataModel
        searchModelData = invoiceRepository.searchModelData
    }
    fun PostPrefillToServer(profileDataModel: ProfileDataModel, profileActivity: Context) {
        invoiceRepository.UploadPrefillData(profileDataModel,profileActivity)
    }

    fun GetPrefillFromServer(){
        invoiceRepository.getPrefillFromServer()
    }
    fun PostBuyerDetailToserver(billCreationDataModel: BillCreationDataModel, profileActivity: Context) {
        invoiceRepository.UploadBuyerData(billCreationDataModel,profileActivity)
    }

    fun GetBuyerDetailToServer(){
        invoiceRepository.getBuyerDetailFromServer()
    }


    fun SearchBuyerDetail(searchText: String, context: Context) {
        invoiceRepository.SearchBuyer(searchText,context)
    }

}