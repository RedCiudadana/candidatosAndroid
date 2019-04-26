package org.redciudadana.candidatos.utils.views

import androidx.annotation.StringRes

/**
 * Created by javier on 1/24/18.
 */

interface ActivityView {
    fun showLoading()
    fun hideLoading()
    fun setTitle(@StringRes title: Int)
    fun setTitle(title: String)
    fun showError(title: String, message: String)
}