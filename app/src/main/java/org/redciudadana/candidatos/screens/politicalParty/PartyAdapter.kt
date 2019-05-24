package org.redciudadana.candidatos.screens.politicalParty

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.utils.views.SimpleListItemViewHolder


class PartyAdapter(
    val context: Context,
    val presenter: PartyContract.Presenter,
    partyList: List<Party>
): RecyclerView.Adapter<SimpleListItemViewHolder>() {

    var partyList: List<Party> = partyList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListItemViewHolder{
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return SimpleListItemViewHolder(view)
    }

    override fun getItemCount(): Int = partyList.size

    override fun onBindViewHolder(holder: SimpleListItemViewHolder, position: Int) {
        val party = partyList[position]
        holder.text?.text = party.nombreCompleto
        holder.setOnClickListener {
            presenter.onPartySelected(party)
        }
    }
}
