package com.example.todolist

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.threeten.bp.LocalDate
import java.io.InputStream
import java.io.InputStreamReader

class TasksManager(private val context: Context) {
    fun loadTasks() : ArrayList<TaskItem> {
        var list = ArrayList<TaskItem>()
        try {
            context.openFileInput(FILE_NAME)
                .use { list = analyzeFile(it) }
        }
        catch (e: Exception) {
            Log.e("error", e.message ?: "")
        }
        finally {
            return list
        }
    }

    fun saveTasks(list: ArrayList<TaskItem>) {
        val gson = Gson()
        val listItems = ArrayTaskItems(list)
        val js = gson.toJson(listItems)

        try {
            context.openFileOutput(Companion.FILE_NAME, Context.MODE_PRIVATE).use {
                it.write(js.toByteArray())
            }
        }
        catch (e: java.lang.Exception) {
            Log.e("error", e.message ?: "")
        }
    }

    fun newTask(list: ArrayList<TaskItem>, text: String, date: LocalDate) {
        val maxIdTask = list.maxBy { it.id }
        val max = maxIdTask?.id ?: 0

        val newItem = TaskItem(max + 1, text, false, date)
        list.add(newItem)
    }

    private fun analyzeFile(file: InputStream) : ArrayList<TaskItem> {
        val streamReader = InputStreamReader(file)
        val gson = Gson()

        return gson.fromJson(streamReader, ArrayTaskItems::class.java).array
    }

    companion object {
        const val FILE_NAME = "tasks.json"
    }
}