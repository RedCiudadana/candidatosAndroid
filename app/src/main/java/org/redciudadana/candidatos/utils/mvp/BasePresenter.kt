package org.redciudadana.candidatos.utils.mvp

import org.redciudadana.candidatos.utils.views.ActivityView

/**
 * Created by javier on 1/24/18.
 */

abstract class BasePresenter<V: IView<out ActivityView>>: IPresenter<V> {
    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}