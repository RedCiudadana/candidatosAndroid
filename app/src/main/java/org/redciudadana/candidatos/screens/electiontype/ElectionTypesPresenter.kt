package org.redciudadana.candidatos.screens.electiontype

import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class ElectionTypesPresenter: BasePresenter<ElectionTypesContract.View>(), ElectionTypesContract.Presenter {
    override fun onViewCreated() {
        mView?.initDistrictList()
    }

    override fun onElectionTypeSelect(electionType: ElectionType) {
        mView?.showElectionType(electionType)
    }
}