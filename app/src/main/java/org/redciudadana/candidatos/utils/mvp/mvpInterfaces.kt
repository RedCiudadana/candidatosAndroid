package org.redciudadana.candidatos.utils.mvp

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import org.redciudadana.candidatos.utils.views.ActivityView

/**
 * Created by javier on 1/23/18.
 */

interface IPresenter<in V: IView<out ActivityView>> {
    fun attachView(view: V)
    fun detachView()
    fun onViewCreated()
}

interface IView<A: ActivityView> {
    fun getActivityView(): A?
    fun getContext(): Context?
    fun getArguments(): Bundle?
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
    fun showError(@StringRes messageRes: Int)
    fun setTitle()
}