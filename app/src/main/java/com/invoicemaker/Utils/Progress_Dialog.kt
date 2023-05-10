package com.invoicemaker.Utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.invoicemaker.R


class Progress_Dialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dailog_fragment, container, false)
    }


}