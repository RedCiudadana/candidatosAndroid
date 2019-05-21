package org.redciudadana.candidatos.screens.profiles

import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgScope
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class ProfilesPresenter: BasePresenter<ProfilesContract.View>(), ProfilesContract.Presenter {
    override fun onViewCreated() {
        mView?.initCandidatesList(null)
        val electionType = mView?.getArguments()?.get(ProfilesContract.ELECTION_TYPE_BUNDLE_ARG) as ElectionType
        when (electionType) {
            ElectionType.DISTRICT -> presentDistrict()
            ElectionType.PRESIDENT, ElectionType.VICEPRESIDENT -> {
                presentElectionType(electionType)
            }
            else -> return
        }
    }

    fun presentDistrict() = bgScope.launch {
        val district = mView?.getArguments()?.getString(ProfilesContract.DISTRICT_BUNDLE_ARG)
        require(district != null)
        setTitle(district)
        val profiles = db.profileDao().getProfilesFor(ElectionType.DISTRICT, district)
        presentProfiles(profiles)
    }

    fun presentElectionType(electionType: ElectionType) = bgScope.launch {
        setTitle(electionType)
        val profiles = db.profileDao().getProfilesFor(electionType)
        presentProfiles(profiles)
    }

    fun setTitle(electionType: ElectionType) = uiScope.launch {
        val title = when (electionType) {
            ElectionType.PRESIDENT -> "Presidente"
            ElectionType.VICEPRESIDENT -> "Vicepresidente"
            ElectionType.DISTRICT -> "Distrito"
            ElectionType.MAYOR -> "Alcaldía"
            ElectionType.NATIONAL_LISTING -> "Listado nacional"
            ElectionType.PARLACEN -> "PARLACEN"
        }
        mView?.setTitle(title)
    }

    fun setTitle(title: String) = uiScope.launch {
        mView?.setTitle(title)
    }

    fun presentProfiles(profileList: List<Profile>) = uiScope.launch {
        mView?.showCandidatesList(profileList)
    }

}