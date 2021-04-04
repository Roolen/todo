package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit.*
import org.threeten.bp.LocalDate

class EditActivity : AppCompatActivity() {
    private val tasksManager = TasksManager(this)
    private lateinit var ds: DateStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ds = DateStorage(LocalDate.now())
        ds.setFunc = {
            date_text.text = it.toString()
        }

        val id = intent.getIntExtra("index_edit", 0)
        val list = tasksManager.loadTasks()
        val item = list.find { it.id == id } ?: throw Exception("Not found id of task")

        task_text.text.insert(0, item.text)
        date_text.text = item.date.toString()

        save_button.setOnClickListener {
            item.apply {
                text = task_text.text.toString()
                date = LocalDate.parse(date_text.text)
            }
            tasksManager.saveTasks(list)

            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

    fun showDatePickerDialog(v: View) {
        DatePickerFragment(this, ds).show(this.supportFragmentManager, "datePicker_edit")
    }
}