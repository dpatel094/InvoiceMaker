package com.invoicemaker.Repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.invoicemaker.BillCreationDataModel
import com.invoicemaker.DataBase.DataModel.StoreBuyerData
import com.invoicemaker.DataBase.DataModel.StorePreFillData
import com.invoicemaker.DataBase.InvoiceMakerDatabase
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.Utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class InvoiceRepository(application: Application) {
    var invoiceMakerDatabase: InvoiceMakerDatabase? = null
    private var db: FirebaseFirestore? = null
    val uploadDataSuccess: MutableLiveData<Boolean?> = MutableLiveData()
    val profileDataModel: MutableLiveData<ProfileDataModel?> = MutableLiveData()
    val billCreationDataModel: MutableLiveData<BillCreationDataModel?> = MutableLiveData()
    var searchModelData: MutableLiveData<BillCreationDataModel?> = MutableLiveData()

    init {
        db = FirebaseFirestore.getInstance()
        invoiceMakerDatabase = InvoiceMakerDatabase.getInstance(application)
    }


    fun UploadPrefillData(profileDataModel: ProfileDataModel, invoiceViewModel: Context) {

        db!!.collection(Constant.prefilldatabase)
            .document(Constant.prefilldatabase)
            .set(profileDataModel)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    uploadDataSuccess.postValue(true)
                    val storePreFillData = StorePreFillData()
                    storePreFillData.userName = profileDataModel.userName
                    storePreFillData.cName = profileDataModel.cName
                    storePreFillData.address = profileDataModel.address
                    storePreFillData.gstNo = profileDataModel.gstNo
                    storePreFillData.sGst = profileDataModel.sGst
                    storePreFillData.cGst = profileDataModel.cGst
                    storePreFillData.declaration = profileDataModel.declaration
                    storePreFillData.invMsg = profileDataModel.invMsg
                    storePreFillData.tod = profileDataModel.tod
                    storePreFillData.other = profileDataModel.other
                    Save_Prefill_Data(storePreFillData)
                    // roomDatabaserepository.delete_record(1)

                    // databaserepository.saveRecord(category)

                } else {
                    uploadDataSuccess.postValue(false)

                }
            }
            .addOnFailureListener {
                uploadDataSuccess.postValue(false)

            }


    }

    fun getPrefillFromServer() {

        if (invoiceMakerDatabase!!.storePrefill().getPreFillData().isEmpty()) {
            Log.d("Call_HitApi", "HitApi")
            db!!.collection(Constant.prefilldatabase)
                .get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    if (!queryDocumentSnapshots.isEmpty) {
                        val obj =
                            queryDocumentSnapshots.documents[0].toObject(ProfileDataModel::class.java)


                        if (obj != null) {
                            val storePreFillData = StorePreFillData()
                            storePreFillData.userName = obj.userName
                            storePreFillData.cName = obj.cName
                            storePreFillData.address = obj.address
                            storePreFillData.gstNo = obj.gstNo
                            storePreFillData.sGst = obj.sGst
                            storePreFillData.cGst = obj.cGst
                            storePreFillData.declaration = obj.declaration
                            storePreFillData.invMsg = obj.invMsg
                            storePreFillData.tod = obj.tod
                            storePreFillData.other = obj.other
                            Save_Prefill_Data(storePreFillData)
                        }
                    }
                }
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        uploadDataSuccess.postValue(true)
                        // roomDatabaserepository.delete_record(1)

                        // databaserepository.saveRecord(category)

                    } else {
                        uploadDataSuccess.postValue(false)

                    }
                }
                .addOnFailureListener {
                    uploadDataSuccess.postValue(false)

                }
        }
    }


    fun SearchBuyer(searchText: String, context: Context) {

        Search_Buyer_Data(searchText)

//        val usersRef = db!!.collection(Constant.buyertable)
//        val query: Query = usersRef.whereEqualTo("buyer_Name", searchText)
//        query.get().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val querySnapshot: QuerySnapshot = task.result
//                if (!querySnapshot.isEmpty) {
//                    // for (document in querySnapshot.documents) {
//                    val obj = querySnapshot.documents[0].toObject(BillCreationDataModel::class.java)
//                    searchModelData.postValue(obj)
//                    //}
//
//                } else {
//                    Log.d("TAG", "Error getting documents: ", task.getException())
//                }
//            } else {
//                Log.d("TAG", "Error getting documents: ", task.getException())
//            }
//        }

    }

    fun BillCreationDataModel.toMap(): HashMap<String, Any?> {
        return hashMapOf(
            "buyer_Name" to buyer_Name,
            "buyer_Address" to buyer_Address,
            "buyer_GstNo" to buyer_GstNo,
            "buyer_StateCode" to buyer_StateCode,
            "buyer_Contact" to buyer_Contact,
            "buyer_Email" to buyer_Email,
            "invoiceDate" to invoiceDate,
            "termOfPayment" to termOfPayment,
        )
    }

    fun UploadBuyerData(billCreationDataModel: BillCreationDataModel, invoiceViewModel: Context) {

        db!!.collection(Constant.buyertable).document(billCreationDataModel.buyer_Name).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val data = hashMapOf(
                        "buyer_Name" to billCreationDataModel.buyer_Name,
                        "buyer_Address" to billCreationDataModel.buyer_Address,
                        "buyer_GstNo" to billCreationDataModel.buyer_GstNo,
                        "buyer_StateCode" to billCreationDataModel.buyer_StateCode,
                        "buyer_Contact" to billCreationDataModel.buyer_Contact,
                        "buyer_Email" to billCreationDataModel.buyer_Email,
                        "invoiceDate" to billCreationDataModel.invoiceDate,
                        "termOfPayment" to billCreationDataModel.termOfPayment,
                    )
                    db!!.collection(Constant.buyertable)
                        .document(billCreationDataModel.buyer_Name)
                        .update(data as Map<String, Any>)
                        .addOnCompleteListener { queryDocumentSnapshots ->
                            if (queryDocumentSnapshots.isSuccessful) {
                                uploadDataSuccess.postValue(true)
                                Save_Buyer_Data_InsertOrUpdate(billCreationDataModel)

                            } else {
                                uploadDataSuccess.postValue(false)

                            }
                        }.addOnFailureListener {
                            uploadDataSuccess.postValue(false)

                        }
                } else {
                    db!!.collection(Constant.buyertable)
                        .document(billCreationDataModel.buyer_Name)
                        .set(billCreationDataModel)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                uploadDataSuccess.postValue(true)
                                Save_Buyer_Data_InsertOrUpdate(billCreationDataModel)

                            } else {
                                uploadDataSuccess.postValue(false)

                            }
                        }
                        .addOnFailureListener {
                            uploadDataSuccess.postValue(false)

                        }
                }
            }.addOnFailureListener { e ->
                Log.w("TAG", "Error checking if document with ID exists", e)
            }


    }

    fun getBuyerDetailFromServer() {
        if (invoiceMakerDatabase!!.storeBuyerData().getBuyerData().isEmpty()) {
            db!!.collection(Constant.buyertable)
                .get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    if (!queryDocumentSnapshots.isEmpty) {
                        val obj =
                            queryDocumentSnapshots.documents[0].toObject(BillCreationDataModel::class.java)
                        billCreationDataModel.postValue(obj)


                        for (queryDocumentSnapshot in queryDocumentSnapshots) {
                            val storeBuyerData = StoreBuyerData()
                            val buydataFB = queryDocumentSnapshot.data
                            storeBuyerData.buyer_Name = buydataFB["buyer_Name"].toString()
                            storeBuyerData.buyer_Address = buydataFB["buyer_Address"].toString()
                            storeBuyerData.buyer_GstNo = buydataFB["buyer_GstNo"].toString()
                            storeBuyerData.buyer_StateCode = buydataFB["buyer_StateCode"].toString()
                            storeBuyerData.buyer_Contact = buydataFB["buyer_Contact"].toString()
                            storeBuyerData.buyer_Email = buydataFB["buyer_Email"].toString()
                            storeBuyerData.invoiceDate = buydataFB["invoiceDate"].toString()
                            storeBuyerData.termOfPayment = buydataFB["termOfPayment"].toString()
                            storeBuyerData.buy_uId = buydataFB["buy_uId"].toString()
                            Save_Buyer_Data(storeBuyerData)
                        }

                    }
                }
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        uploadDataSuccess.postValue(true)
                        // roomDatabaserepository.delete_record(1)

                        // databaserepository.saveRecord(category)

                    } else {
                        uploadDataSuccess.postValue(false)

                    }
                }
                .addOnFailureListener {
                    uploadDataSuccess.postValue(false)

                }
        }
    }


    /*---------------------------DatabaseStore-------------------------------*/
    fun Save_Prefill_Data(storePreFillData: StorePreFillData) {
        GlobalScope.launch(Dispatchers.Main) {
            invoiceMakerDatabase!!.storePrefill().insert(storePreFillData)
        }
    }

    fun Get_Prefill_Data() {
        try {
            val storePreFillData = invoiceMakerDatabase!!.storePrefill().getPreFillData()
            val profileDataModell = ProfileDataModel()
            profileDataModell.userName = storePreFillData[0].userName
            profileDataModell.cName = storePreFillData[0].cName
            profileDataModell.address = storePreFillData[0].address
            profileDataModell.gstNo = storePreFillData[0].gstNo
            profileDataModell.sGst = storePreFillData[0].sGst
            profileDataModell.cGst = storePreFillData[0].cGst
            profileDataModell.declaration = storePreFillData[0].declaration
            profileDataModell.invMsg = storePreFillData[0].invMsg
            profileDataModell.tod = storePreFillData[0].tod
            profileDataModell.other = storePreFillData[0].other
            profileDataModel.postValue(profileDataModell)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun Get_Buyer_Data() {
        val storeBuyerData = invoiceMakerDatabase!!.storeBuyerData().getBuyerData()
    }
    fun Save_Buyer_Data(storeBuyerData: StoreBuyerData) {
        GlobalScope.launch(Dispatchers.Main) {
            invoiceMakerDatabase!!.storeBuyerData().insert(storeBuyerData)
        }
    }
    fun Save_Buyer_Data_InsertOrUpdate(storeBuyerData: BillCreationDataModel) {
        GlobalScope.launch(Dispatchers.Main) {

            val storeBuyerDat = StoreBuyerData()
            storeBuyerDat.buyer_Name = storeBuyerData.buyer_Name
            storeBuyerDat.buyer_Address = storeBuyerData.buyer_Address
            storeBuyerDat.buyer_GstNo = storeBuyerData.buyer_GstNo
            storeBuyerDat.buyer_StateCode = storeBuyerData.buyer_StateCode
            storeBuyerDat.buyer_Contact = storeBuyerData.buyer_Contact
            storeBuyerDat.buyer_Email = storeBuyerData.buyer_Email
            storeBuyerDat.invoiceDate = storeBuyerData.invoiceDate
            storeBuyerDat.termOfPayment = storeBuyerData.termOfPayment
            storeBuyerDat.buy_uId = storeBuyerData.buy_uId


            invoiceMakerDatabase!!.storeBuyerData().insertOrUpdate(storeBuyerDat)
        }
    }

    fun Search_Buyer_Data(buyerName: String) {
//  var buyer_Name: String = "",
//    var buyer_Address: String = "",
//    var buyer_GstNo: String = "",
//    var buyer_StateCode: String= "",
//    var buyer_Contact: String = "",
//    var buyer_Email: String = "",
//    var invoiceDate: String = "",
//    var termOfPayment: String = "",
//    var buy_uId : String = ""


       val  buyerData = invoiceMakerDatabase!!.storeBuyerData().getSearchResult(buyerName)

        val billCreationDataModel = BillCreationDataModel()
        billCreationDataModel.buyer_Address = buyerData[0].buyer_Address
        billCreationDataModel.buyer_Name = buyerData[0].buyer_Name
        billCreationDataModel.buyer_GstNo = buyerData[0].buyer_GstNo
        billCreationDataModel.buyer_StateCode = buyerData[0].buyer_StateCode
        billCreationDataModel.buyer_Contact = buyerData[0].buyer_Contact
        billCreationDataModel.buyer_Email = buyerData[0].buyer_Email
        billCreationDataModel.invoiceDate = buyerData[0].invoiceDate
        billCreationDataModel.termOfPayment = buyerData[0].termOfPayment
        billCreationDataModel.buy_uId = buyerData[0].buy_uId
        searchModelData.postValue(billCreationDataModel)
    }
    /*---------------------------DatabaseStore-------------------------------*/
}