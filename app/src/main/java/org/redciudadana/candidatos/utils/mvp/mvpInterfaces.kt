package org.redciudadana.candidatos.utils.mvp

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes

/**
 * Created by javier on 1/23/18.
 */

interface IPresenter<in V: IView> {
    fun attachView(view: V)
    fun detachView()
    fun onViewCreated()
}

interface IView {
    fun getContext(): Context?
    fun getArguments(): Bundle?
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
    fun showError(@StringRes messageRes: Int)
    fun setTitle()
}