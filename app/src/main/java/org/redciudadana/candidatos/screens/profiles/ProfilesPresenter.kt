package org.redciudadana.candidatos.screens.profiles

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgDispatcher
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class ProfilesPresenter: BasePresenter<ProfilesContract.View>(), ProfilesContract.Presenter {
    lateinit var electionType: ElectionType
    override fun onViewCreated() {
        electionType  = mView?.getArguments()?.get(ProfilesContract.ELECTION_TYPE_BUNDLE_ARG) as ElectionType
        uiScope.launch {
            val profilesPromise = async(bgDispatcher) {
                when (electionType) {
                    ElectionType.DISTRICT -> presentDistrict()
                    ElectionType.PRESIDENT, ElectionType.VICEPRESIDENT -> {
                        presentElectionType(electionType)
                    }
                    ElectionType.NATIONAL_LISTING, ElectionType.PARLACEN -> presentParty()
                    ElectionType.MAYOR -> presentMayors()
                }
            }
            mView?.showCandidatesList(profilesPromise.await())
        }
    }


    override fun onCandidateSelected(profile: Profile) {
        mView?.getActivityView()?.showProfile(profile)
    }

    fun presentDistrict(): List<Profile> {
        val district = mView?.getArguments()?.getString(ProfilesContract.DISTRICT_BUNDLE_ARG)
        val party = mView?.getArguments()?.getString(ProfilesContract.PARTY_BUNDLE_ARG)
        requireNotNull(district)
        requireNotNull(party)
        setTitle(district)
        return db.profileDao().getProfilesFor(ElectionType.DISTRICT, district, party)
    }

    fun presentParty(): List<Profile> {
        val party = mView?.getArguments()?.getString(ProfilesContract.PARTY_BUNDLE_ARG)
        requireNotNull(party)
        setTitle(electionType)
        return db.profileDao().getProfilesFor(electionType, party).map { it.computedProfile() }
    }

    fun presentMayors(): List<Profile> {
        val department = mView?.getArguments()?.getString(ProfilesContract.DEPARTMENT_BUNDLE_ARG)
        val municipality = mView?.getArguments()?.getString(ProfilesContract.MUNICIPALITY_BUNDLE_ARG)
        requireNotNull(department)
        requireNotNull(municipality)
        setTitle("Candidatos $municipality")
        return db.profileDao().getMayorProfiles(department, municipality).map { it.computedProfile() }
    }

    fun presentElectionType(electionType: ElectionType): List<Profile> {
        setTitle(electionType)
        return db.profileDao().getProfilesFor(electionType)
            .map { it.computedProfile() }
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

}