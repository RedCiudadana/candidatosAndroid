package org.redciudadana.candidatos.screens.districts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.utils.views.SimpleListItemViewHolder

class DistrictsAdapter(
    private val representant: DistrictsContract.View,
    private val presenter: DistrictsContract.Presenter,
    districts: List<String>?) : RecyclerView.Adapter<SimpleListItemViewHolder>() {

    var districts: List<String>? = districts?.sorted()
        set(value) {
            field = value?.sorted()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListItemViewHolder{
        val view = LayoutInflater.from(representant.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false)
        return SimpleListItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return districts?.size ?: 0
    }

    override fun onBindViewHolder(holder: SimpleListItemViewHolder, position: Int) {
        val district = districts?.get(position)
        if (district != null) {
            holder.text?.text = district
            holder.setOnClickListener {
                presenter.onDistrictSelect(district)
            }
        }
    }

}