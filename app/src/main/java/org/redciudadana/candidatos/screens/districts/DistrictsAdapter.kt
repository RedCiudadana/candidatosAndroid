package org.redciudadana.candidatos.screens.districts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DistrictsAdapter(
    private val representant: DistrictsContract.View,
    private val presenter: DistrictsContract.Presenter,
    districts: List<String>?) : RecyclerView.Adapter<DistrictsAdapter.ViewHolder>() {


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView?
            get() = view.findViewById(android.R.id.text1)
        fun setOnClickListener(listener: (View) -> Unit) {
            view.setOnClickListener(listener)
        }
    }

    var districts: List<String>? = districts?.sorted()
        set(value) {
            field = value?.sorted()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(representant.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return districts?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val district = districts?.get(position)
        if (district != null) {
            holder.text?.text = district
            holder.setOnClickListener {
                presenter.onDistrictSelect(district)
            }
        }
    }

}