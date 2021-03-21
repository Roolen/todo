package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTasks()

        button2.setOnClickListener {
            var list = ArrayList<TaskItem>()

            try {
                baseContext.openFileInput(Companion.FILE_NAME)
                    .use {
                        list = analyzeFile(it)
                    }
            }
            catch (e: Exception) {
                Log.e("error", e.message ?: "")
            }

            val newItem = TaskItem(editText.text.toString(), false)
            list.add(newItem)

            saveTasks(list)

            editText.text.clear()
            displayTasks()
        }
    }

    fun saveTasks(list: ArrayList<TaskItem>) {
        val gson = Gson()
        val listItems = ArrayTaskItems(list)
        val js = gson.toJson(listItems)

        try {
            baseContext.openFileOutput(Companion.FILE_NAME, Context.MODE_PRIVATE).use {
                it.write(js.toByteArray())
            }
        }
        catch (e: Exception) {
            Log.e("error", e.message ?: "")
        }
    }

    private fun loadTasks() : ArrayList<TaskItem> {
        var list = ArrayList<TaskItem>()
        try {
            baseContext.openFileInput(Companion.FILE_NAME)
                .use { list = analyzeFile(it) }
        }
        catch (e: Exception) {
            Log.e("error", e.message ?: "")
        }
        finally {
            return list
        }
    }

    fun displayTasks() {
        val list = loadTasks()
        tasks_list.adapter = TaskListAdapter(this, R.layout.item_list, list, this)
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