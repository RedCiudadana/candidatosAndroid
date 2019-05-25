package org.redciudadana.candidatos.screens.mayor.departments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import org.redciudadana.candidatos.utils.views.initializeRecycler
import java.lang.ref.WeakReference

class DepartmentsFragment: BaseFragment<DepartmentsContract.View, DepartmentsContract.Presenter, MainView>(), DepartmentsContract.View {

    override var mPresenter: DepartmentsContract.Presenter = DepartmentsPresenter()
    var mAdapter: WeakReference<DepartmentsAdapter>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycler(context, recycler_view)
    }

    override fun showDepartments(departments: List<String>) {
        var adapter = mAdapter?.get()
        if (adapter == null) {
           adapter = DepartmentsAdapter(context!!, mPresenter, departments)
            mAdapter = WeakReference(adapter)
            recycler_view.adapter = adapter
        }
        adapter.departmentList = departments

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAdapter = null
    }

    override fun setTitle() {
        mActivityView?.setTitle("Selecciona tu departamento")
    }
}