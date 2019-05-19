package org.redciudadana.candidatos.screens.diputados

import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.api.Api
import org.redciudadana.candidatos.data.api.ModelStorage
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.utils.mvp.BasePresenter

class DiputadosPresenter: BasePresenter<DiputadosContract.View>(), DiputadosContract.Presenter {
    override fun onViewCreated() {
        val district = mView?.getArguments()?.get(DiputadosContract.ELECTION_TYPE_BUNDLE_ARG) as? ElectionType
        require(district != null)
        mView?.showLoading()
        var cachedProfiles: List<Profile>? = null
        mView?.getContext()?.let {
            cachedProfiles = ModelStorage.getProfileListFromStorage(it, district)
//            Api.getProfiles(it, district) { profileList, throwable ->
//                if (throwable != null) {
//                    val profiles = mView?.getContext()?.getString(R.string.error_profiles)
//                    if (profiles != null) {
//                        mView?.showError(profiles)
//                    }
//                } else if (profileList != null){
////                    mView?.showCandidatesList(filterProfiles(district, profileList) as List<Profile>)
//                }
//                mView?.hideLoading()
//            }
        }
//        mView?.initCandidatesList(filterProfiles(district, cachedProfiles))

    }

//    fun filterProfiles(electionType: ElectionType, profiles: List<Profile>?): List<Profile>? {
//        //TODO: make api model to fetch presidential profiles
////        return profiles?.filter { it.distrito == district }
//    }

}