package com.example.findgit.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.findgit.R

class CarregandoUtil(
    var activity: Activity,
    var context: Context
) {

    fun show(): AlertDialog {
        val alertDialog = AlertDialog.Builder(activity)
            .setView(LayoutInflater.from(context).inflate(R.layout.screen_carregando, null))

        val alertDialogExe = alertDialog.create()
        alertDialogExe?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialogExe.show()
        return alertDialogExe
    }

}