package com.example.maktabhw16_1



import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker


import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class TaskDialog : DialogFragment() {
    private lateinit var edtTitle:EditText
    private lateinit var edtDescription:EditText
    private lateinit var btnDate:Button
    private lateinit var btnTime:Button
    private lateinit var cbDone:CheckBox
    private lateinit var listener:TaskDialogListener
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val view= inflater?.inflate(R.layout.layout_dialog,null)
        if (view != null) {
            initView(view)
        }
        builder.setView(view)
            .setTitle("")
            .setNegativeButton("Cancel",object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                   dismiss()
                }


            })
            .setPositiveButton("Save",object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val title=edtTitle.text.toString()
                    val description=edtDescription.text.toString()
                    val task=Task(title,description)
                   // findNavController().previousBackStackEntry?.savedStateHandle?.set("task",task)
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("task",task)
                    //listener.applyTask(title,description)
                }


            })
        return builder.create()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    interface TaskDialogListener{
        fun applyTask(title: String,description:String)
    }
    private fun initView(view: View){
        edtTitle=view.findViewById(R.id.edt_title)
        edtDescription=view.findViewById(R.id.edt_description)
        btnDate=view.findViewById(R.id.btn_date)
        btnTime=view.findViewById(R.id.btn_time)
        cbDone=view.findViewById(R.id.cb_done)
    }
}