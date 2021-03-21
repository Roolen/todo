package com.example.todolist

data class TaskItem(var text: String, var isCheck: Boolean) {
}

class ArrayTaskItems(val array: ArrayList<TaskItem>)