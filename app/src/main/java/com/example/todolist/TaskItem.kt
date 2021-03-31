package com.example.todolist

import org.threeten.bp.LocalDate

data class TaskItem(var text: String, var isCheck: Boolean, var date: LocalDate)

class ArrayTaskItems(val array: ArrayList<TaskItem>)