package org.redciudadana.candidatos.utils.mvp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import org.redciudadana.candidatos.utils.views.ActivityView

/**
 * Created by javier on 1/24/18.
 */
abstract class BaseFragment<in V : IView, T : IPresenter<V>, A: ActivityView> : Fragment(), IView {

    protected abstract var mPresenter: T
    protected var mActivityView: A? = null

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivityView) {
            mActivityView = context as A
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        mPresenter.attachView(this as V)
        mPresenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        setTitle()
    }

    override fun showLoading() {
        mActivityView?.showLoading()
    }

    override fun hideLoading() {
        mActivityView?.hideLoading()
    }

    override fun showError(message: String) {
        mActivityView?.showError("Error", message)
    }

    override fun showError(messageRes: Int) {
        mActivityView?.showError("Error", getString(messageRes))
    }

    override fun onDestroyView() {
        mPresenter.detachView()
        super.onDestroyView()
    }


}