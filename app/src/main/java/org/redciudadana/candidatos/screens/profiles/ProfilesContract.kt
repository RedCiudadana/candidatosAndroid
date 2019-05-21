package org.redciudadana.candidatos.screens.profiles

import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object ProfilesContract {

    const val ELECTION_TYPE_BUNDLE_ARG = "electionType"
    const val DISTRICT_BUNDLE_ARG = "district"

    interface View: IView<MainView> {
        fun initCandidatesList(list: List<Profile>?)
        fun showCandidatesList(list: List<Profile>)
        fun onCandidateSelected(profile: Profile)
        fun setTitle(title: String)
    }

    interface Presenter: IPresenter<View>
}