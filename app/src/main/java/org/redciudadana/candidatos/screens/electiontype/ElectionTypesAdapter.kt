package org.redciudadana.candidatos.screens.electiontype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_with_image.view.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.utils.glide.GlideApp
import org.redciudadana.candidatos.utils.views.ItemWithImageViewHolder


class ElectionTypesAdapter(
    private val electionTypeView: ElectionTypesContract.View,
    private val presenter: ElectionTypesContract.Presenter) : RecyclerView.Adapter<ItemWithImageViewHolder>() {

    val electionTypes: List<ElectionType> = ElectionType.values().asList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWithImageViewHolder{
        val view = LayoutInflater.from(electionTypeView.getContext()).inflate(R.layout.recycler_item_with_image, parent, false)
        return ItemWithImageViewHolder(view)
    }


    override fun getItemCount(): Int {
        return electionTypes.size
    }

    override fun onBindViewHolder(holder: ItemWithImageViewHolder, position: Int) {
        val electionType = electionTypes.get(position)
        holder.text.text = electionType.label
        GlideApp
            .with(electionTypeView.getContext()!!)
            .load(electionType.drawable)
            .into(holder.image)
        holder.setOnClickListener {
            presenter.onElectionTypeSelect(electionType)
        }
    }

}