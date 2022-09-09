package com.asfdfsd.todo

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import com.asfdfsd.todo.DATABASE.models.databamodel
import com.asfdfsd.todo.DATABASE.models.mydatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class bottom_sheet : BottomSheetDialogFragment() {
    lateinit var add_button: Button
    lateinit var date_button: Button
    var datetoday: Calendar = Calendar.getInstance()
    lateinit var hostdata:databamodel
    lateinit var inputtext: TextInputLayout
    lateinit var inputdescription: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add_button = view.findViewById(R.id.add_button)
        inputtext = view.findViewById(R.id.edit_title)
        date_button = view.findViewById(R.id.bottom_date)
        date_button.text = "${datetoday.get(Calendar.YEAR)}/${datetoday.get(Calendar.MONTH) + 1}/${
            datetoday.get(Calendar.DAY_OF_MONTH)
        }"
        date_button.setOnClickListener {

            var datecalander =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int,
                    ) {
                        date_button.text = "$year/${month + 1}/$dayOfMonth"
                        datetoday.set(year, month , dayOfMonth)
                    }
                }, datetoday.get(Calendar.YEAR), datetoday.get(Calendar.MONTH),
                    datetoday.get(Calendar.DAY_OF_MONTH))
            datecalander.show()

        }
        inputdescription = view.findViewById(R.id.edit_description)
        add_button.setOnClickListener {
            validet()
            datetoday.clear(Calendar.HOUR)
            datetoday.clear(Calendar.MINUTE)
            datetoday.clear(Calendar.SECOND)
            datetoday.clear(Calendar.MILLISECOND)
            Log.e("sssss,","ssss           ${datetoday.time}")
            hostdata= databamodel(title =inputtext.editText?.text.toString(), descraption = inputdescription.editText?.text.toString()
                , isdone = false, date = datetoday.time)
            mydatabase.getinstance(requireContext().applicationContext).getDAO().insert(hostdata)
            objectonclick?.onclick()
           dismiss()
        }



    }
    var objectonclick:onaddclick?=null
    interface onaddclick
    {
        fun onclick()
    }
    private fun validet() {
        if (inputtext.editText?.text.toString().isEmpty()) {
            inputtext.error = "Enter the title"
        } else {
            inputtext.error = null
        }
        if (inputdescription.editText?.text.toString().isEmpty()) {
            inputdescription.error = "Enter the descrption"
        } else {
            inputdescription.error = null
        }
    }
}