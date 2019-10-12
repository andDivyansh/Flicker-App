package com.divyansh.flicker.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.divyansh.flicker.R

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
class ProgressDialog (context : Context) : AlertDialog(context){

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(null)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.loading_dialog)
    }
}