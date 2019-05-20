package org.redciudadana.candidatos.data.utils

import android.content.Context
import android.util.Log
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.coroutines.bgScope
import org.redciudadana.candidatos.data.api.Api
import org.redciudadana.candidatos.data.db.db
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.data.models.ProfileInfo
import org.redciudadana.candidatos.events.Events

suspend fun getPartyList() {
    Api.getPartyList()?.let {
        db.partyDao().insertParties(it)
    }
}

suspend fun getProfilesAndInfo(electionType: ElectionType, dataFunction: suspend () -> List<ProfileInfo>?) {
    val apiProfiles = Api.getProfiles(electionType)
    if (apiProfiles != null) {
        db.profileDao().insertProfiles(apiProfiles)
    }
    val profileInfo = dataFunction()
    updateProfiles(profileInfo)
}

fun updateProfiles(profileInfoList: List<ProfileInfo>?) {
    profileInfoList?.forEach { profileInfo ->
        profileInfo.id?.let { id ->
            val profile = db.profileDao().findProfile(id)
            profile?.let {
                it.profileInfo = profileInfo
                db.profileDao().updateProfile(it)
            }
        }

    }
}

fun fetchAll(context: Context) {
    bgScope.launch {
        try {
            getPartyList()
            getProfilesAndInfo(ElectionType.PRESIDENT, Api::getInfoPresident)
            getProfilesAndInfo(ElectionType.VICEPRESIDENT, Api::getInfoVicepresident)
            getProfilesAndInfo(ElectionType.DISTRICT, Api::getInfoDistrict)
            getProfilesAndInfo(ElectionType.PARLACEN, Api::getInfoParlacen)
            getProfilesAndInfo(ElectionType.NATIONAL_LISTING, Api::getInfoListing)
            getProfilesAndInfo(ElectionType.MAYOR, Api::getInfoMayor)
            Events.onEvent(Events.EventType.PROFILES_UPDATED)
        } catch (error: Exception) {
            Events.onEvent(Events.EventType.UPDATE_ERROR)
            Log.e("INIT", "$error")
        }
    }
}