package org.redciudadana.candidatos.utils.views

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_with_image.view.*

fun initializeRecycler(context: Context?, recycler: RecyclerView) {
    val mLayoutManager = LinearLayoutManager(context)
    recycler.setHasFixedSize(true)
    recycler.layoutManager = mLayoutManager
    recycler.addItemDecoration(
        DividerItemDecoration(
            context,
            mLayoutManager.orientation
        )
    )
}

private const val INSTANCE_STATE_SAVE = "instance state save"

fun onSaveRecyclerInstanceState(recycler: RecyclerView, outState: Bundle) {
    recycler.layoutManager?.onSaveInstanceState().let {
        outState.putParcelable(INSTANCE_STATE_SAVE, it)
    }
}

fun getRecyclerInstanceState(savedState: Bundle?): Parcelable? {
    return savedState?.getParcelable(INSTANCE_STATE_SAVE)
}

fun restoreRecyclerState(savedState: Parcelable?, layoutManager: RecyclerView.LayoutManager) {
    savedState?.let {
        layoutManager.onRestoreInstanceState(it)
    }
}

class SimpleListItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val text: TextView?
        get() = view.findViewById(android.R.id.text1)
    fun setOnClickListener(listener: (View) -> Unit) {
        view.setOnClickListener(listener)
    }
}

class ItemWithImageViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val image: ImageView
        get() = view.item_image
    val text: TextView
        get() = view.item_text

    fun setOnClickListener(listener: () -> Unit) {
        view.setOnClickListener { listener() }
    }
}