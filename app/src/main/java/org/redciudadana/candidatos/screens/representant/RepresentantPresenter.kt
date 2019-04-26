package org.redciudadana.candidatos.screens.representant

import org.redciudadana.candidatos.data.api.Api
import org.redciudadana.candidatos.data.api.ModelStorage
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class RepresentantPresenter: BasePresenter<RepresentantContract.View>(), RepresentantContract.Presenter {
    override fun onViewCreated() {

        mView?.getContext()?.let {
            mView?.showLoading()
            val cachedDistricts = getDistricts(ModelStorage.getProfileListFromStorage(it))
            mView?.initDistrictList(cachedDistricts)
            Api.getProfiles(it) { list, error ->
                mView?.hideLoading()
                if (error != null || list == null) {
                    mView?.showError("No se pudieron obtener nuevos datos")
                } else {
                    val districts = getDistricts(list)
                    mView?.updateDistrictList(districts)
                }
            }

        }
    }

    override fun onDistrictSelect(district: String) {
        mView?.showDistrictCandidates(district)
    }

    private fun getDistricts(profileList: List<Profile>?): List<String>? {
        return profileList
            ?.mapNotNull { it.distrito }
            ?.toSet()
            ?.toList()

    }
}