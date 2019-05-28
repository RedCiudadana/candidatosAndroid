package org.redciudadana.candidatos.screens.politicalParty

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.utils.glide.GlideApp
import org.redciudadana.candidatos.utils.views.ItemWithImageViewHolder
import org.redciudadana.candidatos.utils.views.SimpleListItemViewHolder


class PartyAdapter(
    val context: Context,
    val presenter: PartyContract.Presenter,
    partyList: List<Party>
): RecyclerView.Adapter<ItemWithImageViewHolder>() {

    var partyList: List<Party> = partyList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWithImageViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_with_image, parent, false)
        return ItemWithImageViewHolder(view)
    }

    override fun getItemCount(): Int = partyList.size

    override fun onBindViewHolder(holder: ItemWithImageViewHolder, position: Int) {
        val party = partyList[position]
        holder.text.text = party.nombreCompleto
        GlideApp
            .with(context)
            .load(party.logoUrl)
            .fallback(R.drawable.menu_2)
            .error(R.drawable.menu_2)
            .into(holder.image)
        holder.setOnClickListener {
            presenter.onPartySelected(party)
        }
    }
}
