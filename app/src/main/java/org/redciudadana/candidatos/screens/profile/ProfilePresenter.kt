package org.redciudadana.candidatos.screens.profile

import android.view.View
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgDispatcher
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.api.ModelStorage
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.Assistance
import org.redciudadana.candidatos.data.models.HistoryEntry
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.data.models.Voting
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BasePresenter
import org.redciudadana.candidatos.utils.openUrl


val numberRegex = Regex("""(\d+)""")

class DiputadoPresenter: BasePresenter<ProfileContract.View>(), ProfileContract.Presenter {
    lateinit var profile: Profile


    override fun onViewCreated() {
        uiScope.launch {
            profile = mView?.getArguments()?.getParcelable(MainView.ARG_DIPUTADO) as Profile
            mView?.showProfile(profile)
            val party = async(bgDispatcher) {
                val partido = profile.partido
                if (partido != null) {
                    db.partyDao().getById(partido)
                } else {
                    null
                }
            }
            party.await()?.let {
                mView?.showParty(it)
            }
        }

    }

    override fun onFacebookPress() {
        openUrlOnClick(profile.fb)
    }

    override fun onPhonePress() {
        openUrlOnClick(getPhoneNumberUrl(profile.telefono))
    }

    override fun onTwitterPress() {
        openUrlOnClick(buildTwitterUrl(profile.tw))
    }

    override fun onOptionPress(view: View, position: Int?) {
        when (position) {
            0 -> mView?.showGeneralInformation(view, profile)
            1 -> prepareHistoryAndShow(view)
            2 -> prepareAssistance(view)
            3 -> prepareVoting(view)
        }
    }

    private fun openUrlOnClick(string: String?) {
        if (string == null || string.isEmpty()) {
            mView?.showError("Información no disponible")
        } else {
            try {
                openUrl(mView?.getContext(), string)
            } catch (error: Error) {
                mView?.showError("Información no disponible")
            }
        }
    }

    private fun buildTwitterUrl(twitterAccount: String?): String? {
        if (twitterAccount != null && !twitterAccount.isEmpty()) {
            if (twitterAccount.contains("twitter.com")) {
                return twitterAccount
            }
            return String.format("https://twitter.com/%s", twitterAccount)
        }
        return null
    }

    fun getPhoneNumberUrl(rawData: String?): String? {
        if (rawData != null) {
            val result = numberRegex.find(rawData)
            if (result != null) {
                return String.format("tel:%s", result.value)
            }
        }

        return null
    }

    fun prepareHistoryAndShow(view: View) {
        mView?.showLoading()
        mView?.getContext()?.let {
            val cachedHistory = ModelStorage.getHistoryEntryList(it)
            mView?.showHistory(view, filterHistory(cachedHistory, profile))
//            Api.getHistoryEntryList(it) { response, error ->
//                mView?.hideLoading()
//                if (error != null) {
//                    mView?.showError("No se pudo cargar la información")
//                } else {
//                    mView?.updateHistory(filterHistory(response, profile))
//                }
//            }
        }
    }

    fun prepareAssistance(view: View) {
        mView?.showLoading()
        mView?.getContext()?.let {
            val cachedAssistance = ModelStorage.getAssistanceList(it)
            mView?.showAssistance(view, filterAssistance(cachedAssistance, profile))
//            Api.getAssistanceList(it) { response, error ->
//                mView?.hideLoading()
//                if (error != null) {
//                    mView?.showError("No se pudo cargar la información")
//                } else {
//                    mView?.updateAssistance(filterAssistance(response, profile))
//                }
//            }
        }
    }

    fun prepareVoting(view: View) {
        mView?.showLoading()
        mView?.getContext()?.let {
            val cachedVoting = filterVoting(ModelStorage.getVotingList(it), profile)
            mView?.showVoting(view, cachedVoting)
//            Api.getVotingList(it) { response, error ->
//                mView?.hideLoading()
//                if (error != null) {
//                    mView?.showError("No se pudo cargar la información")
//                } else {
//                    mView?.updateVoting(filterVoting(response, profile))
//                }
//            }
        }
    }

    fun filterHistory(list: List<HistoryEntry>?, profile: Profile?): List<HistoryEntry>? {
        return list
            ?.sortedBy { it.ano?.toInt() }
            ?.filter { it.perfil == profile?.id }
    }

    fun filterAssistance(assistance: List<Assistance>?, profile: Profile?): Assistance? {
        return assistance
            ?.filter { it.perfilId == profile?.id }
            ?.firstOrNull()
    }

    fun filterVoting(votingList: List<Map<String, String>>?, profile: Profile?): List<Voting>? {
        return votingList
            ?.filter { it.get("perfilId") == profile?.id }
            ?.firstOrNull()
            ?.toList()
            ?.filter { it.first != "perfilId" }
            ?.map { Voting(it.first, it.second) }
    }

}