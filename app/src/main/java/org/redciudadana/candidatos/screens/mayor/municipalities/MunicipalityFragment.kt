package org.redciudadana.candidatos.screens.mayor.municipalities

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

class MunicipalityFragment: BaseFragment<MunicipalityContract.View, MunicipalityContract.Presenter, MainView>(), MunicipalityContract.View {

    override var mPresenter: MunicipalityContract.Presenter = MunicipalityPresenter()
    var mAdapter: WeakReference<MunicipalityAdapter>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycler(context, recycler_view)
    }

    override fun showMunicipalities(municipalityList: List<String>) {
        var adapter = mAdapter?.get()
        if (adapter == null) {
            adapter = MunicipalityAdapter(context!!, mPresenter, municipalityList)
            mAdapter = WeakReference(adapter)
            recycler_view.adapter = adapter
        }
        adapter.municipalityList = municipalityList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAdapter = null
    }

    override fun setTitle() {
        mActivityView?.setTitle("Selecciona tu muni")
    }
}