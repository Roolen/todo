package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.item_list.view.*

class TaskListAdapter(context: Context,
                      resource: Int,
                      dataList: ArrayList<TaskItem>,
                      val ma: MainActivity) : ArrayAdapter<TaskItem>(context, resource, dataList) {
    private var inflater = LayoutInflater.from(context)
    private var layout = resource
    private var items = dataList

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater?.inflate(layout, parent, false) ?: return View(context)
        val item = items[position]

        view.text_task?.text = item.text
        view.date_text?.text = item.date.toString()
        view.checker.isChecked = item.isCheck

        view.delete_button.setOnClickListener {
            items.removeAt(position)
            ma.tasksManager.saveTasks(items)
            ma.displayTasks()
        }

        view.edit_button.setOnClickListener {
            val intentEdit = Intent(ma, EditActivity::class.java)
            intentEdit.putExtra("index_edit", item.id)
            ma.startActivity(intentEdit)
        }

        view.checker.setOnCheckedChangeListener { _: CompoundButton, b: Boolean ->
            items[position].isCheck = b
            ma.tasksManager.saveTasks(items)
            ma.displayTasks()
        }

        return view
    }

}