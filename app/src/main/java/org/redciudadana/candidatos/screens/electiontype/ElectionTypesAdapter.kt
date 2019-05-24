package org.redciudadana.candidatos.screens.electiontype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.utils.views.SimpleListItemViewHolder

class ElectionTypesAdapter(
    private val representant: ElectionTypesContract.View,
    private val presenter: ElectionTypesContract.Presenter) : RecyclerView.Adapter<SimpleListItemViewHolder>() {

    val districts: List<Pair<ElectionType, String>> = ElectionType.values().map { Pair(it, it.label)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListItemViewHolder {
        val view = LayoutInflater.from(representant.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false)
        return SimpleListItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return districts.size
    }

    override fun onBindViewHolder(holder: SimpleListItemViewHolder, position: Int) {
        val district = districts.get(position)
        holder.text?.text = district.second
        holder.setOnClickListener {
            presenter.onElectionTypeSelect(district.first)
        }
    }

}