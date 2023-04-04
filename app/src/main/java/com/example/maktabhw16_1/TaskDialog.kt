package com.example.maktabhw16_1



import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast


import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class TaskDialog : DialogFragment() {
    private lateinit var edtTitle:EditText
    private lateinit var edtDescription:EditText
    private lateinit var btnDate:Button
    private lateinit var btnTime:Button
    private lateinit var cbDone:CheckBox
    private lateinit var listener:TaskDialogListener
    private var position=-1

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val view= inflater?.inflate(R.layout.layout_dialog,null)

        if (view != null) {
            initView(view)

        }
        btnDate.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager =activity?.supportFragmentManager
            supportFragmentManager?.setFragmentResultListener(
                "REQUEST_KEY",
               requireActivity()
            ){resultkey,bundle->
                if (resultkey=="REQUEST_KEY"){
                    val date=bundle.getString("SELECTED_DATE")
                    btnDate.text=date
                }

            }
            if (supportFragmentManager != null) {
                datePickerFragment.show(supportFragmentManager,"")
            }

        }

        btnTime.setOnClickListener {
            openTimePicker()
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
                    val date=btnDate.text.toString()
                    val time=btnTime.text.toString()
                    val task=Task(title,description,date,time)
                    val taskPosition=TaskPosition(position, task)
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("task",taskPosition)
                }


            })

        return builder.create()

    }

   private fun openTimePicker(){
       val isSystem24Hour=is24HourFormat(requireActivity())
       val clockFormat=if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
       val picker = MaterialTimePicker.Builder()
           .setTimeFormat(clockFormat)
           .setHour(12)
           .setMinute(0).build()
       picker.show(childFragmentManager,"")
       picker.addOnPositiveButtonClickListener{
           btnTime.text="${picker.hour}:${picker.minute}"
       }
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