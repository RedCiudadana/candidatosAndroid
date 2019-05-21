package org.redciudadana.candidatos.screens.profiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_diputados_item.view.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.glide.GlideApp
import org.redciudadana.candidatos.utils.glide.RoundCornerTransformation

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val candidateImage: ImageView
        get() = view.diputado_image
    val candidateText: TextView
        get() = view.diputado_name
    val candidatePartido: TextView
        get() = view.diputado_partido

    var onClickListener: View.OnClickListener? = null
        set(value) {
            field = value
            view.setOnClickListener(field)
        }

}

class ProfilesAdapter(
    private val context: Context,
    private val candidateView: ProfilesContract.View,
    diputados: List<Profile>?) : RecyclerView.Adapter<ViewHolder>() {

    var profiles: List<Profile>? = sortProfiles(diputados)
        set(value) {
            field = sortProfiles(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_diputados_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return profiles?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profiles?.get(position)
        if (profile != null) {
            GlideApp
                .with(context)
                .load(profile.fotoUrl)
                .transform(RoundCornerTransformation(context.resources))
                .into(holder.candidateImage)
            holder.candidateText.text = profile.nombre
            holder.candidatePartido.text = profile.partidoactual
            holder.onClickListener = View.OnClickListener {
                candidateView.onCandidateSelected(profile)
            }
        }
    }

    private fun sortProfiles(profiles: List<Profile>?): List<Profile>? = profiles?.sortedBy { it.nombre }

}