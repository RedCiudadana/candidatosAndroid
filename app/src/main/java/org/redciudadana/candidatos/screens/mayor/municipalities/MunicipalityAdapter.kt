package org.redciudadana.candidatos.screens.mayor.municipalities

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.utils.views.SimpleListItemViewHolder

class MunicipalityAdapter(
    val context: Context,
    val presenter: MunicipalityContract.Presenter,
    municipalityList: List<String>
): RecyclerView.Adapter<SimpleListItemViewHolder>() {

    var municipalityList: List<String> = municipalityList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListItemViewHolder{
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return SimpleListItemViewHolder(view)
    }

    override fun getItemCount(): Int = municipalityList.size

    override fun onBindViewHolder(holder: SimpleListItemViewHolder, position: Int) {
        val municipality = municipalityList[position]
        holder.text?.text = municipality
        holder.setOnClickListener {
            presenter.onMunicipalitySelected(municipality)
        }
    }
}
