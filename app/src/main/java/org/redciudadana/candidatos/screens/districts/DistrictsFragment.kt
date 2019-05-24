package org.redciudadana.candidatos.screens.districts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import org.redciudadana.candidatos.utils.views.initializeRecycler
import java.lang.ref.WeakReference

class DistrictsFragment: BaseFragment<DistrictsContract.View, DistrictsContract.Presenter, MainView>(), DistrictsContract.View {

    override var mPresenter: DistrictsContract.Presenter = DistrictsPresenter()

    var mDistrictAdapter: WeakReference<DistrictsAdapter>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }
    override fun initDistrictList(list: List<String>?) {
        initializeRecycler(context, recycler_view)
        val districtsAdapter = DistrictsAdapter(this, mPresenter, list)
        recycler_view.adapter = districtsAdapter
        mDistrictAdapter = WeakReference(districtsAdapter)

    }

    override fun updateDistrictList(list: List<String>?) {
        mDistrictAdapter?.get()?.districts = list
    }

    override fun showDistrictCandidates(district: String) {
        mActivityView?.showParties(ElectionType.DISTRICT, district)
    }

    override fun setTitle() {
        mActivityView?.setTitle("Selecciona tu distrito")
    }
}