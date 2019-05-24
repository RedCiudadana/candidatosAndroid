package org.redciudadana.candidatos.screens.politicalParty

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgDispatcher
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class PartyPresenter: BasePresenter<PartyContract.View>(), PartyContract.Presenter {
    lateinit var electionType: ElectionType
    var district: String? = null

    override fun onViewCreated() {
        uiScope.launch {
            electionType = mView?.getArguments()?.get(PartyContract.BUNDLE_ARG_ELECTION_TYPE) as ElectionType

            val parties = async(bgDispatcher) {
                val result = when (electionType) {
                    ElectionType.NATIONAL_LISTING -> {
                        db.profileDao().getParties(ElectionType.NATIONAL_LISTING)
                    }
                    ElectionType.DISTRICT -> {
                        val district = mView?.getArguments()?.get(PartyContract.BUNDLE_ARG_DISTRICT) as? String
                        requireNotNull(district)
                        this@PartyPresenter.district = district
                        db.profileDao().getDistrictParties(electionType, district)
                    }
                    else -> emptyList()
                }
                return@async result
            }
            mView?.presentPartyList(parties.await())
        }
    }

    override fun onPartySelected(party: Party) {
        when (electionType) {
            ElectionType.DISTRICT -> {
                val district = this.district
                requireNotNull(district)
                mView?.getActivityView()?.showProfiles(electionType, district, party.id)
            }
            ElectionType.NATIONAL_LISTING -> {
                mView?.getActivityView()?.showProfiles(electionType, party)
            }
            else -> return
        }

    }
}