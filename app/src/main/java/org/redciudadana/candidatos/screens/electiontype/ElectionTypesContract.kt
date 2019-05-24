package org.redciudadana.candidatos.screens.electiontype

import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object ElectionTypesContract {
    interface View: IView<MainView> {
        fun initDistrictList()
    }

    interface Presenter: IPresenter<View> {
        fun onElectionTypeSelect(electionType: ElectionType)
    }


}