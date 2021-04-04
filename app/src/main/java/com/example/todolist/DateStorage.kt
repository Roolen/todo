package com.example.todolist

import org.threeten.bp.LocalDate

class DateStorage(date: LocalDate) {
    var setFunc: (LocalDate) -> Unit = {}
    var date = date
        set(value) {
            field = value
            setFunc(value)
        }
}