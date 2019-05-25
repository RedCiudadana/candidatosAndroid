package org.redciudadana.candidatos.screens.profiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_diputados.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import org.redciudadana.candidatos.utils.views.initializeRecycler
import java.lang.ref.WeakReference

class ProfilesFragment: BaseFragment<ProfilesContract.View, ProfilesContract.Presenter, MainView>(), ProfilesContract.View {

    override var mPresenter: ProfilesContract.Presenter = ProfilesPresenter()
    var mAdapter: WeakReference<ProfilesAdapter>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diputados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycler(context, diputados_list)
    }

    override fun showCandidatesList(list: List<Profile>) {
        var adapter = mAdapter?.get()
        if (adapter == null) {
            adapter = ProfilesAdapter(context!!, mPresenter, list)
            diputados_list.adapter = adapter
            mAdapter = WeakReference(adapter)
        }
        adapter.profiles = list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAdapter = null
    }

    override fun setTitle() {
        mActivityView?.setTitle("")
    }
    override fun setTitle(title: String) {
        mActivityView?.setTitle(title)
    }

}