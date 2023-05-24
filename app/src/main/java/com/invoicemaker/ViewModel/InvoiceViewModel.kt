package com.invoicemaker.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.invoicemaker.BillCreationDataModel
import com.invoicemaker.DataBase.DataModel.StorePreFillData
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.Repository.InvoiceRepository

class InvoiceViewModel(application: Application) : AndroidViewModel(application) {
    var uploadDataSuccess: MutableLiveData<Boolean?> = MutableLiveData()
    var profileDataModel: MutableLiveData<ProfileDataModel?> = MutableLiveData()
    var billCreationDataModel: MutableLiveData<BillCreationDataModel?> = MutableLiveData()
    var searchModelData: MutableLiveData<BillCreationDataModel?> = MutableLiveData()
    var invoiceRepository: InvoiceRepository = InvoiceRepository(application)


    init {
        uploadDataSuccess = invoiceRepository.uploadDataSuccess
        profileDataModel = invoiceRepository.profileDataModel
        billCreationDataModel = invoiceRepository.billCreationDataModel
        searchModelData = invoiceRepository.searchModelData
    }

    fun PostPrefillToServer(profileDataModel: ProfileDataModel, profileActivity: Context) {
        invoiceRepository.UploadPrefillData(profileDataModel, profileActivity)
    }

    fun GetPrefillFromServer() {
        invoiceRepository.getPrefillFromServer()
    }

    fun PostBuyerDetailToserver(
        billCreationDataModel: BillCreationDataModel,
        profileActivity: Context
    ) {
        invoiceRepository.UploadBuyerData(billCreationDataModel, profileActivity)
    }

    fun GetBuyerDetailToServer() {
        invoiceRepository.getBuyerDetailFromServer()
    }

    fun SearchBuyerDetail(searchText: String, context: Context) {
        invoiceRepository.SearchBuyer(searchText, context)
    }

    suspend fun StorePrefillDataToDB(storePreFillData: StorePreFillData){
        invoiceRepository.Save_Prefill_Data(storePreFillData)
    }

    /*-------------------callDatabase----------------------*/
    fun getPrefillData(){
        invoiceRepository.Get_Prefill_Data()
    }

}