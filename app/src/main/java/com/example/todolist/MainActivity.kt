package com.example.todolist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var ds: DateStorage
    val tasksManager = TasksManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidThreeTen.init(this)
        ds = DateStorage(LocalDate.now())
        ds.setFunc = {
            date_text.text = it.toString()
        }

        displayTasks()

        button2.setOnClickListener {
            val list = tasksManager.loadTasks()

            tasksManager.newTask(list, editText.text.toString(), ds.date)

            tasksManager.saveTasks(list)

            editText.text.clear()
            displayTasks()
        }
    }

    fun displayTasks() {
        val list = tasksManager.loadTasks()
        list.sortBy { it.isCheck }
        tasks_list.adapter = TaskListAdapter(this, R.layout.item_list, list, this)
    }

    fun showDatePickerDialog(v: View) {
        DatePickerFragment(this, ds).show(this.supportFragmentManager, "datePicker")
    }
}