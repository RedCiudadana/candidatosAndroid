package org.redciudadana.candidatos.screens.profile

import android.view.View
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgDispatcher
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.api.ModelStorage
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.*
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
            2 -> prepareAcademicInformationAndShow(view)
            3 -> prepareInterviewAndShow(view)
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
        uiScope.launch {
            mView?.showHistory(view, null)
            val historyEntryList = async(bgDispatcher) {
                db.historyDao().getHistoryList(profile.id)
            }
            mView?.updateHistory(historyEntryList.await())
        }
    }

    fun prepareAcademicInformationAndShow(view: View) {
        uiScope.launch {
            mView?.showAcademicInformation(view, profile.profileInfo?.experienciaAcademica ?: "Información no disponible")
        }
    }

    fun prepareInterviewAndShow(view: View) {
        uiScope.launch {
            val interviews = async(bgDispatcher) {
                db.interviewDao().getInterviews(profile.id)
            }
            mView?.showInterviews(view, interviews.await())
        }
    }

    override fun showInterview(interview: Interview) {
        openUrlOnClick(interview.url)
    }


    fun filterHistory(list: List<HistoryEntry>?, profile: Profile?): List<HistoryEntry>? {
        return list
            ?.sortedBy { it.ano?.toInt() }
            ?.filter { it.perfil == profile?.id }
    }


}