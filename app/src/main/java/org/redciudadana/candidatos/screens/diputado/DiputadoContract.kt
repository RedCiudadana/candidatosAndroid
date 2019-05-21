package org.redciudadana.candidatos.screens.diputado

import org.redciudadana.candidatos.data.models.Assistance
import org.redciudadana.candidatos.data.models.HistoryEntry
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.data.models.Voting
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object DiputadoContract {
    val options = arrayOf("Información general", "Historial político", "Asistencia", "Votación")

    interface View: IView<MainView> {
        fun showProfile(profile: Profile)
        fun onOptionPress(view: android.view.View, position: Int?)
        fun showGeneralInformation(view: android.view.View, profile: Profile)
        fun showHistory(view: android.view.View, historyEntryList: List<HistoryEntry>?)
        fun updateHistory(historyEntryList: List<HistoryEntry>?)
        fun showAssistance(view: android.view.View, assistance: Assistance?)
        fun updateAssistance(assistance: Assistance?)
        fun showVoting(view: android.view.View, voting: List<Voting>?)
        fun updateVoting(voting: List<Voting>?)
    }

    interface Presenter: IPresenter<View> {
        fun onFacebookPress()
        fun onTwitterPress()
        fun onPhonePress()
        fun onOptionPress(view: android.view.View, position: Int?)
    }



}