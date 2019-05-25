package org.redciudadana.candidatos.screens.mayor.municipalities

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgDispatcher
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class MunicipalityPresenter: BasePresenter<MunicipalityContract.View>(), MunicipalityContract.Presenter {

    lateinit var department: String

    override fun onViewCreated() {
        uiScope.launch {
            department = mView?.getArguments()?.getString(MunicipalityContract.DEPARTMENT_BUNDLE_ARG) as String
            val municipalities = async(bgDispatcher) {
                db.profileDao().getMayorMunicipalities(department)
            }
            mView?.showMunicipalities(municipalities.await())
        }
    }

    override fun onMunicipalitySelected(municipality: String) {
        mView?.getActivityView()?.showProfiles(ElectionType.MAYOR, department = department, municipality = municipality)
    }
}