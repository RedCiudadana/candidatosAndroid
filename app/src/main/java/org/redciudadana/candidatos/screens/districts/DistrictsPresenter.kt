package org.redciudadana.candidatos.screens.districts

import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgScope
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class DistrictsPresenter: BasePresenter<DistrictsContract.View>(), DistrictsContract.Presenter {
    override fun onViewCreated() {
        mView?.showLoading()
        bgScope.launch {
            val districts = db.profileDao().getDistricts()
            showDistricts(districts)
        }

    }

    fun showDistricts(districts: List<String>) {
        uiScope.launch {
            mView?.initDistrictList(districts)
            mView?.hideLoading()
        }
    }

    override fun onDistrictSelect(district: String) {
        mView?.showDistrictCandidates(district)
    }

}