package org.redciudadana.candidatos.screens.electiontype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_representant.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import java.lang.ref.WeakReference

class ElectionTypesFragment: BaseFragment<ElectionTypesContract.View, ElectionTypesContract.Presenter, MainView>(), ElectionTypesContract.View {

    override var mPresenter: ElectionTypesContract.Presenter = ElectionTypesPresenter()

    var mDistrictAdapter: WeakReference<ElectionTypesAdapter>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_representant, container, false)
    }
    override fun initDistrictList() {
        val mLayoutManager = LinearLayoutManager(context)
        val districtsAdapter = ElectionTypesAdapter(this, mPresenter)
        representant_list.setHasFixedSize(true)
        representant_list.layoutManager = mLayoutManager
        representant_list.addItemDecoration(
            DividerItemDecoration(
                context,
                mLayoutManager.orientation
            )
        )
        representant_list.adapter = districtsAdapter
        mDistrictAdapter = WeakReference(districtsAdapter)

    }

    override fun showElectionType(electionType: ElectionType) {
        mActivityView?.showElectionType(electionType)
    }

    override fun setTitle() {
        mActivityView?.setTitle("Mis candidatos")
    }
}