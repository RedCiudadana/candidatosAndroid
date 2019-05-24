package org.redciudadana.candidatos.screens.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_diputado_history_item.view.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.HistoryEntry


class ProfileHistoryAdapter(
    val diputadoView: ProfileContract.View,
    historyList: List<HistoryEntry>?
): RecyclerView.Adapter<ProfileHistoryAdapter.ViewHolder>(){

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val year: TextView
            get() = view.diputado_history_year
        val entity: TextView
            get() = view.diputado_history_entity
        val row: View
            get() = view
    }

    var historyList: List<HistoryEntry>? = historyList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(diputadoView.getContext()).inflate(R.layout.fragment_diputado_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = historyList?.get(position)
        diputadoView.getContext()?.let {
            val backgroundColorResource = if (position % 2 == 0) R.color.row_background_white else R.color.row_background_gray
            val backgroundColor = ContextCompat.getColor(it, backgroundColorResource)
            holder.row.setBackgroundColor(backgroundColor)
        }
        holder.year.text = item?.ano
        holder.entity.text = item?.partido
    }
}