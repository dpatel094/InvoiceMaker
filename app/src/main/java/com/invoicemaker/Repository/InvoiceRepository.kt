package com.invoicemaker.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.invoicemaker.BillCreationDataModel
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.Utils.Constant


class InvoiceRepository {
    private var db: FirebaseFirestore? = null
    val uploadDataSuccess: MutableLiveData<Boolean?> = MutableLiveData()
    val profileDataModel: MutableLiveData<ProfileDataModel?> = MutableLiveData()
    val billCreationDataModel: MutableLiveData<BillCreationDataModel?> = MutableLiveData()
    var searchModelData: MutableLiveData<BillCreationDataModel?> = MutableLiveData()

    init {
        db = FirebaseFirestore.getInstance()
    }


    fun UploadPrefillData(profileDataModel: ProfileDataModel, invoiceViewModel: Context) {

        db!!.collection(Constant.prefilldatabase)
            .document(Constant.prefilldatabase)
            .set(profileDataModel)
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

    fun getPrefillFromServer() {
        db!!.collection(Constant.prefilldatabase)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val obj =
                        queryDocumentSnapshots.documents[0].toObject(ProfileDataModel::class.java)
                    profileDataModel.postValue(obj)
                } else {

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


    fun SearchBuyer(searchText: String, context: Context) {
        val usersRef = db!!.collection(Constant.buyertable)
        val query: Query = usersRef.whereEqualTo("buyer_Name", searchText)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot: QuerySnapshot = task.result
                if (!querySnapshot.isEmpty) {
                    val obj = querySnapshot.documents[0].toObject(BillCreationDataModel::class.java)
                        searchModelData.postValue(obj)


                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException())
                }
            } else {
                Log.d("TAG", "Error getting documents: ", task.getException())
            }
        }

    }

    fun UploadBuyerData(billCreationDataModel: BillCreationDataModel, invoiceViewModel: Context) {

        db!!.collection(Constant.buyertable)
            .document(billCreationDataModel.buyer_Name)
            .set(billCreationDataModel)
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

    fun getBuyerDetailFromServer() {
        db!!.collection(Constant.buyertable)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val obj =
                        queryDocumentSnapshots.documents[0].toObject(BillCreationDataModel::class.java)
                    billCreationDataModel.postValue(obj)
                } else {

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