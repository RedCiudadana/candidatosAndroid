package org.redciudadana.candidatos.screens.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_profile_interview_item.view.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Interview

class ProfileInterviewAdapter(
    val diputadoView: ProfileContract.View,
    val profilePresenter: ProfileContract.Presenter,
    interviewList: List<Interview>?
): RecyclerView.Adapter<ProfileInterviewAdapter.ViewHolder>(){

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val name: TextView
            get() = view.profile_interview_name
        val row: View
            get() = view

        var onClickListener: View.OnClickListener? = null
        set(value) {
            field = value
            view.setOnClickListener(value)
        }
    }

    var interviewList: List<Interview>? = interviewList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(diputadoView.getContext()).inflate(R.layout.fragment_profile_interview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return interviewList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = interviewList?.get(position)
        diputadoView.getContext()?.let {
            val backgroundColorResource = if (position % 2 == 0) R.color.row_background_white else R.color.row_background_gray
            val backgroundColor = ContextCompat.getColor(it, backgroundColorResource)
            holder.row.setBackgroundColor(backgroundColor)
        }
        holder.name.text = item?.name
        holder.onClickListener =  View.OnClickListener{
            profilePresenter.showInterview(item!!)
        }

    }
}