package org.redciudadana.candidatos.screens.profiles

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_diputados.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment

class ProfilesFragment: BaseFragment<ProfilesContract.View, ProfilesContract.Presenter, MainView>(), ProfilesContract.View {

    override var mPresenter: ProfilesContract.Presenter = ProfilesPresenter()
    private lateinit var mAdapter: ProfilesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diputados, container, false)
    }

    override fun initCandidatesList(list: List<Profile>?) {
        val mLayoutManager = LinearLayoutManager(context)
        mAdapter = ProfilesAdapter(context!!, this, list)
        diputados_list.setHasFixedSize(true)
        diputados_list.layoutManager = mLayoutManager
        diputados_list.addItemDecoration(
            DividerItemDecoration(
                context,
                mLayoutManager.orientation
            )
        )
        diputados_list.adapter = mAdapter
    }


    override fun setTitle() {
        mActivityView?.setTitle("")
    }
    override fun setTitle(title: String) {
        mActivityView?.setTitle(title)
    }

    override fun showCandidatesList(list: List<Profile>) {
        mAdapter.profiles = list
    }

    override fun onCandidateSelected(profile: Profile) {
        mActivityView?.showProfile(profile)
    }

}