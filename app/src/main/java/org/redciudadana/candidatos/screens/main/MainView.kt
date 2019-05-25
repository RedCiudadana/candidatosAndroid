package org.redciudadana.candidatos.screens.main

import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.views.ActivityView

interface MainView : ActivityView {
    fun showMainMenu()
    fun showProfiles()
    fun showProfiles(electionType: ElectionType, district: String? = null, department: String? = null, municipality: String? = null, party: Party? = null)
    fun showProfile(profile: Profile)
    fun showElectionTypes()
    fun showParties(electionType: ElectionType, district: String? = null, department: String? = null, municipality: String? = null)
    fun showDistricts()
    fun showMayorDepartments()
    fun showMunicipalities(department: String)
    fun showCongressData()
    fun showNews()
    fun setOnBackListener(listener: () -> Boolean)

    companion object {
        const val ARG_DIPUTADO = "diputado"
    }
}