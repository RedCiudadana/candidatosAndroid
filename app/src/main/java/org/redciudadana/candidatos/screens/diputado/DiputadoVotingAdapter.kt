package org.redciudadana.candidatos.screens.diputado

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_diputado_voting_item.view.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Voting

class DiputadoVotingAdapter(
    val diputadoView: DiputadoContract.View,
    votingList: List<Voting>?
): RecyclerView.Adapter<DiputadoVotingAdapter.ViewHolder>(){

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val item: TextView
            get() = view.diputado_voting_item
        val result: TextView
            get() = view.diputado_voting_result
        val row: View
            get() = view
    }

    var votingList: List<Voting>? = votingList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(diputadoView.getContext()).inflate(R.layout.fragment_diputado_voting_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return votingList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = votingList?.get(position)
        diputadoView.getContext()?.let {
            val backgroundColorResource = if (position % 2 == 0) R.color.row_background_white else R.color.row_background_gray
            val backgroundColor = ContextCompat.getColor(it, backgroundColorResource)
            holder.row.setBackgroundColor(backgroundColor)
        }
        holder.item.text = item?.element
        holder.result.text = item?.vote
    }
}