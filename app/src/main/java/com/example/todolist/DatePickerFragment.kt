package com.example.todolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import org.threeten.bp.LocalDate

class DatePickerFragment(var ma: MainActivity) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(ma, this, ma.date.year, ma.date.monthValue, ma.date.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        ma.date = LocalDate.of(year, month, day)
    }
}