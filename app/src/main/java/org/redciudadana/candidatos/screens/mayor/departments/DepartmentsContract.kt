package org.redciudadana.candidatos.screens.mayor.departments

import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object DepartmentsContract {
    interface View: IView<MainView> {
        fun showDepartments(departments: List<String>)
    }

    interface Presenter: IPresenter<View> {
        fun onDepartmentSelected(department: String)
    }
}