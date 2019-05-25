package org.redciudadana.candidatos.screens.mayor.municipalities

import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object MunicipalityContract {
    const val DEPARTMENT_BUNDLE_ARG = "department"

    interface View: IView<MainView> {
        fun showMunicipalities(municipalityList: List<String>)
    }

    interface Presenter: IPresenter<View> {
        fun onMunicipalitySelected(municipality: String)
    }
}