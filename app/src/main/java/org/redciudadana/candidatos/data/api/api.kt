package org.redciudadana.candidatos.data.api

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


val BASE_URL = "https://cdn.jsdelivr.net/gh/RedCiudadana/CandiDatos2@gh-pages/static-files/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

val api: IApi = retrofit.create(IApi::class.java)


suspend fun <T> apiCallGet(apiCall: () -> Call<T>): T? {
    val callResponse = apiCall()
    val response = suspendCoroutine<T?> { continuation ->
        callResponse.enqueue(object: Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWith(Result.failure(t))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                continuation.resume(body)
            }
        })
    }
    return response
}


object Api {

    suspend fun getProfiles(electionType: ElectionType): List<Profile>? {
        val listing = apiCallGet(
            apiCall = {
                when (electionType) {
                    ElectionType.PRESIDENT -> api.getPresidentProfiles()
                    ElectionType.VICEPRESIDENT -> api.getVicepresidentProfiles()
                    ElectionType.DISTRICT -> api.getDistrictProfiles()
                    ElectionType.MAYOR -> api.getMayorProfiles()
                    ElectionType.NATIONAL_LISTING -> api.getListingProfiles()
                    ElectionType.PARLACEN -> api.getParlacenProfiles()
                }
            }
        )
        listing?.forEach {
            it.electionType = electionType
        }
        return listing
    }

    suspend fun getHistoryEntryList() = apiCallGet(
        apiCall = api::getHistory
    )

    suspend fun getAssistanceList() = apiCallGet(
        apiCall = api::getAssistance
    )

    suspend fun getVotingList() = apiCallGet(
        apiCall = api::getVoting
    )

    suspend fun getInfoDistrict() = apiCallGet(api::getInfoDistrict)
    suspend fun getInfoListing() = apiCallGet(api::getInfoListing)
    suspend fun getInfoMayor() = apiCallGet(api::getInfoMayor)
    suspend fun getInfoParlacen() = apiCallGet(api::getInfoParlacen)
    suspend fun getInfoPresident() = apiCallGet(api::getInfoPresident)
    suspend fun getInfoVicepresident() = apiCallGet(api::getInfoVicepresident)
    suspend fun getPartyList() = apiCallGet(api::getPartyList)
}