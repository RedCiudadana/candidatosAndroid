package org.redciudadana.candidatos.screens.politicalParty

import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object PartyContract {

    const val BUNDLE_ARG_ELECTION_TYPE = "election type"
    const val BUNDLE_ARG_DISTRICT = "district"

    interface View: IView<MainView> {
        fun presentPartyList(partyList: List<Party>)
    }

    interface Presenter: IPresenter<View> {
        fun onPartySelected(party: Party)
    }
}