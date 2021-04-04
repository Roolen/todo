package com.example.todolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import org.threeten.bp.LocalDate

class DatePickerFragment(private val ctx: Context, private var dateStorage: DateStorage) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(ctx, this,
            dateStorage.date.year,
            dateStorage.date.monthValue,
            dateStorage.date.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        dateStorage.date = LocalDate.of(year, month, day)
    }
}