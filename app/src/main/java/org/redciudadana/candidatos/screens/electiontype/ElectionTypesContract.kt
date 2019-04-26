package org.redciudadana.candidatos.screens.electiontype

import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object ElectionTypesContract {
    interface View: IView {
        fun initDistrictList()
        fun showElectionType(electionType: ElectionType)
    }

    interface Presenter: IPresenter<View> {
        fun onElectionTypeSelect(electionType: ElectionType)
    }


}