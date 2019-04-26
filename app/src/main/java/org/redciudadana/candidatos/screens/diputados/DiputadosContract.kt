package org.redciudadana.candidatos.screens.diputados

import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object DiputadosContract {

    const val ELECTION_TYPE_BUNDLE_ARG = "electionType"

    interface View: IView {
        fun initCandidatesList(list: List<Profile>?)
        fun showCandidatesList(list: List<Profile>)
        fun onCandidateSelected(profile: Profile)
    }

    interface Presenter: IPresenter<View>
}
