package org.redciudadana.candidatos.screens.diputado

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_diputado_option.view.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.screens.diputado.DiputadoContract.options
import org.redciudadana.candidatos.utils.glide.GlideApp

class ViewHolder(val view: View) {
    val image: ImageView
        get() = view.diputado_option_image
    val text: TextView
        get() = view.diputado_option_text
    val container: View
        get() =  view.container

    fun setOnClickListener(listener: (View) -> Unit) {
        view.setOnClickListener(listener)
    }
}

class DiputadoOptionsAdapter(val diputadoView: DiputadoContract.View): ArrayAdapter<String>(diputadoView.getContext()!!, 0, options) {

    val images = arrayListOf(
        R.drawable.icon_document_black,
        R.drawable.icon_history_black,
        R.drawable.icon_people_black,
        R.drawable.icon_check_black
    )


    override fun getView(position: Int, viewParam: View?, parent: ViewGroup?): View {
        val view = viewParam ?: LayoutInflater.from(diputadoView.getContext()).inflate(R.layout.fragment_diputado_option, parent, false)
        val holder = ViewHolder(view)
        holder.text.text = DiputadoContract.options[position]
        GlideApp
            .with(diputadoView.getContext()!!)
            .load(images[position])
            .into(holder.image)
        holder.setOnClickListener {
            diputadoView.onOptionPress(holder.container, position)
        }
        return view
    }
}