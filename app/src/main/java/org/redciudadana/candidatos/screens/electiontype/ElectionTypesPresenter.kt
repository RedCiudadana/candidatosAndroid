package org.redciudadana.candidatos.screens.electiontype

import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class ElectionTypesPresenter: BasePresenter<ElectionTypesContract.View>(), ElectionTypesContract.Presenter {
    override fun onViewCreated() {
        mView?.initDistrictList()
    }

    override fun onElectionTypeSelect(electionType: ElectionType) {
        when (electionType) {
            ElectionType.PRESIDENT, ElectionType.VICEPRESIDENT-> {
                mView?.getActivityView()?.showProfiles(electionType)
            }
            ElectionType.NATIONAL_LISTING, ElectionType.PARLACEN -> {
                mView?.getActivityView()?.showParties(electionType)
            }
            ElectionType.DISTRICT -> mView?.getActivityView()?.showDistricts()
            ElectionType.MAYOR -> mView?.getActivityView()?.showMayorDepartments()
            else -> return
        }
    }
}