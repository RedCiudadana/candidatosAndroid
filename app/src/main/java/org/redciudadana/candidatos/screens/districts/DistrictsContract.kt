package org.redciudadana.candidatos.screens.districts

import org.redciudadana.candidatos.utils.mvp.IPresenter
import org.redciudadana.candidatos.utils.mvp.IView

object DistrictsContract {
    interface View: IView {
        fun initDistrictList(list: List<String>?)
        fun updateDistrictList(list: List<String>?)
        fun showDistrictCandidates(district: String)
    }

    interface Presenter: IPresenter<View> {
        fun onDistrictSelect(district: String)
    }


}