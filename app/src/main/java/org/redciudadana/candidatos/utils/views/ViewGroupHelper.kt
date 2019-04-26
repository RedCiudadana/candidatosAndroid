package org.redciudadana.candidatos.utils.views

import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Created by javier on 2/28/18.
 */

interface ViewGroupHelper {
    fun getLayout(): Int
    fun populateView(context: Context, view: View, arguments: Bundle)
}