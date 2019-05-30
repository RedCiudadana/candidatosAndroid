package org.redciudadana.candidatos.screens.profile

import org.redciudadana.candidatos.data.models.*
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object ProfileContract {
    val options = arrayOf(
        "Información general",
        "Historial político",
        "Experiencia académica",
        "Entrevistas / foros"
    )

    interface View: IView<MainView> {
        fun showProfile(profile: Profile)
        fun showParty(party: Party)
        fun onOptionPress(view: android.view.View, position: Int?)
        fun showGeneralInformation(view: android.view.View, profile: Profile)
        fun showHistory(view: android.view.View, historyEntryList: List<HistoryEntry>?)
        fun updateHistory(historyEntryList: List<HistoryEntry>?)
        fun showAcademicInformation(view: android.view.View, details: String)
        fun showInterviews(view: android.view.View, interviewList: List<Interview>?)
    }

    interface Presenter: IPresenter<View> {
        fun onFacebookPress()
        fun onTwitterPress()
        fun onPhonePress()
        fun onOptionPress(view: android.view.View, position: Int?)
        fun showInterview(interview: Interview)
    }



}