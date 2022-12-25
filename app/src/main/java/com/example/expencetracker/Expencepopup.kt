package com.example.expencetracker

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment


class Expencepopup : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        AlertDialog.Builder(requireContext()).setMessage("XXX").setPositiveButton(getString(R.string.app_name)){_,_->}.create()
        return super.onCreateDialog(savedInstanceState)
    }
    companion object{
        const val TAG = "ExpenceEditPoPUp"
    }


}