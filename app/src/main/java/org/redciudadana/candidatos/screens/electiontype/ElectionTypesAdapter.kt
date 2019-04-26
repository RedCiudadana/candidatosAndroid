package org.redciudadana.candidatos.screens.electiontype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.data.models.ElectionType

class ElectionTypesAdapter(
    private val representant: ElectionTypesContract.View,
    private val presenter: ElectionTypesContract.Presenter) : RecyclerView.Adapter<ElectionTypesAdapter.ViewHolder>() {


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView?
            get() = view.findViewById(android.R.id.text1)
        fun setOnClickListener(listener: (View) -> Unit) {
            view.setOnClickListener(listener)
        }
    }

    val districts: List<Pair<ElectionType, String>> = ElectionType.values().map { Pair(it, it.label)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(representant.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return districts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val district = districts.get(position)
        holder.text?.text = district.second
        holder.setOnClickListener {
            presenter.onElectionTypeSelect(district.first)
        }
    }

}