package org.redciudadana.candidatos.screens.electiontype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_election_type.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import org.redciudadana.candidatos.utils.views.initializeRecycler
import java.lang.ref.WeakReference

class ElectionTypesFragment: BaseFragment<ElectionTypesContract.View, ElectionTypesContract.Presenter, MainView>(), ElectionTypesContract.View {

    override var mPresenter: ElectionTypesContract.Presenter = ElectionTypesPresenter()

    var mDistrictAdapter: WeakReference<ElectionTypesAdapter>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_election_type, container, false)
    }
    override fun initDistrictList() {
        initializeRecycler(context, recycler_view)
        val districtsAdapter = ElectionTypesAdapter(this, mPresenter)
        recycler_view.adapter = districtsAdapter
        mDistrictAdapter = WeakReference(districtsAdapter)

    }

    override fun setTitle() {
        mActivityView?.setTitle("Mis candidatos")
    }
}