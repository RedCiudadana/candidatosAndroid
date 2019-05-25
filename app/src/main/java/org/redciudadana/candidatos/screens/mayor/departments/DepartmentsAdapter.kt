package org.redciudadana.candidatos.screens.mayor.departments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.utils.views.SimpleListItemViewHolder

class DepartmentsAdapter(
    val context: Context,
    val presenter: DepartmentsContract.Presenter,
    departmentList: List<String>
): RecyclerView.Adapter<SimpleListItemViewHolder>() {

    var departmentList: List<String> = departmentList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListItemViewHolder{
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return SimpleListItemViewHolder(view)
    }

    override fun getItemCount(): Int = departmentList.size

    override fun onBindViewHolder(holder: SimpleListItemViewHolder, position: Int) {
        val department = departmentList[position]
        holder.text?.text = department
        holder.setOnClickListener {
            presenter.onDepartmentSelected(department)
        }
    }
}
