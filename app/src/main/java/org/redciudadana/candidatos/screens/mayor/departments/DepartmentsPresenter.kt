package org.redciudadana.candidatos.screens.mayor.departments

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgDispatcher
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class DepartmentsPresenter: BasePresenter<DepartmentsContract.View>(), DepartmentsContract.Presenter {
    override fun onViewCreated() {
        uiScope.launch {
            val departmentsList = async(bgDispatcher) {
                db.profileDao().getMayorDepartments()
            }
            mView?.showDepartments(departmentsList.await())
        }
    }

    override fun onDepartmentSelected(department: String) {
        mView?.getActivityView()?.showMunicipalities(department)
    }
}