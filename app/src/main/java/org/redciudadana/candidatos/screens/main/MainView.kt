package org.redciudadana.candidatos.screens.main

import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.views.ActivityView

interface MainView : ActivityView {
    fun showMainMenu()
    fun showProfiles()
    fun showProfiles(electionType: ElectionType)
    fun showProfiles(electionType: ElectionType, district: String, party: Party)
    fun showProfiles(electionType: ElectionType, party: Party)
    fun showProfiles(electionType: ElectionType, department: String, municipality: String)
    fun showProfile(profile: Profile)
    fun showElectionTypes()
    fun showElectionType(electionType: ElectionType)
    fun showParties(electionType: ElectionType, district: String? = null, department: String? = null, municipality: String? = null)
    fun showDistricts()
    fun showDepartments()
    fun showMunicipalities()
    fun showCongressData()
    fun showNews()
    fun setOnBackListener(listener: () -> Boolean)

    companion object {
        const val ARG_DIPUTADO = "diputado"
    }
}