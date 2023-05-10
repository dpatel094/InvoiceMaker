package com.invoicemaker.Repository

import android.content.Context
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.invoicemaker.ProfileDataModel
import com.invoicemaker.Utils.Constant

class InvoiceRepository {
    private var db: FirebaseFirestore? = null
    val uploadDataSuccess: MutableLiveData<Boolean?> = MutableLiveData()
    val profileDataModel: MutableLiveData<ProfileDataModel?> = MutableLiveData()
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
//                    Toast.makeText(
//                        application,
//                        "Failed",
//                        Toast.LENGTH_LONG
//                    ).show()
                }
            }
            .addOnFailureListener {
                uploadDataSuccess.postValue(false)
//                Toast.makeText(
//                    application,
//                    it.message,
//                    Toast.LENGTH_LONG
//                ).show()
            }

//        db!!.collection(Constant.prefilldatabase).add(profileDataModel).addOnSuccessListener { documentReference ->
//            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            if (documentReference.isSuccessful) {
//            } else {
//            }
//            uploadDataSuccess.postValue(true)
//
//        }
//            .addOnFailureListener { e ->
//                Log.w(ContentValues.TAG, "Error adding document", e)
//                uploadDataSuccess.postValue(true)
//            }
    }

    fun getPrefillFromServer(){
        db!!.collection(Constant.prefilldatabase)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val obj = queryDocumentSnapshots.documents[0].toObject(ProfileDataModel::class.java)
                    profileDataModel.postValue(obj)
                }else{

                }
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    uploadDataSuccess.postValue(true)
                    // roomDatabaserepository.delete_record(1)

                    // databaserepository.saveRecord(category)

                } else {
                    uploadDataSuccess.postValue(false)
//                    Toast.makeText(
//                        application,
//                        "Failed",
//                        Toast.LENGTH_LONG
//                    ).show()
                }
            }
            .addOnFailureListener {
                uploadDataSuccess.postValue(false)
//                Toast.makeText(
//                    application,
//                    it.message,
//                    Toast.LENGTH_LONG
//                ).show()
            }
    }
}