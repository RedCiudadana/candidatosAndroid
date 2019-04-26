package org.redciudadana.candidatos.screens.diputados

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

class DiputadosAdapter(
    private val context: Context,
    private val candidateView: DiputadosContract.View,
    diputados: List<Profile>?) : RecyclerView.Adapter<ViewHolder>() {

    var diputados: List<Profile>? = sortProfiles(diputados)
        set(value) {
            field = sortProfiles(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_diputados_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return diputados?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val candidate = diputados?.get(position)
        if (candidate != null) {
            GlideApp
                .with(context)
                .load(candidate.fotoUrl)
                .transform(RoundCornerTransformation(context.resources))
                .into(holder.candidateImage)
            holder.candidateText.text = candidate.nombre
            holder.candidatePartido.text = candidate.partidoactual
            holder.onClickListener = View.OnClickListener {
                candidateView.onCandidateSelected(candidate)
            }
        }
    }

    private fun sortProfiles(profiles: List<Profile>?): List<Profile>? = profiles?.sortedBy { it.nombre }

}