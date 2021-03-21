package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
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
        view.checker.isChecked = item.isCheck

        view.delete_button.setOnClickListener {
            items.removeAt(position)
            ma.saveTasks(items)
            ma.displayTasks()
        }

        view.checker.setOnCheckedChangeListener { _: CompoundButton, b: Boolean ->
            items[position].isCheck = b
            ma.saveTasks(items)
        }

        return view
    }

}