package org.redciudadana.candidatos.data.api

import android.content.Context
import org.redciudadana.candidatos.data.models.Assistance
import org.redciudadana.candidatos.data.models.HistoryEntry
import org.redciudadana.candidatos.data.models.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val BASE_URL = "https://raw.githubusercontent.com/RedCiudadana/Congreso/gh-pages/static-files/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val api: IApi = retrofit.create(IApi::class.java)


fun <T> apiCallGet(context: Context, getFromStorage: (Context) -> T?, setToStorage: (Context, T) -> Unit,
                   apiCall: () -> Call<T>, callback: ((T?, Throwable?) -> Unit)?) {
    val callResponse = apiCall()
    callResponse.enqueue(object: Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            val persistedData = getFromStorage(context)
            if (persistedData != null) {
                if (callback != null) callback(persistedData, null)
            } else {
                if (callback != null) callback(null, t)
            }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val body = response.body()
            if (body != null) {
                setToStorage(context, body)
            }
            if (callback != null) {
                callback(body, null)
            }
        }
    })
}


object Api {

    fun getProfiles(context: Context, callback: ((List<Profile>?, Throwable?) -> Unit)?) = apiCallGet(
        context = context,
        getFromStorage = ModelStorage::getProfileListFromStorage,
        setToStorage = ModelStorage::saveProfileListToStorage,
        apiCall = api::getProfiles,
        callback = callback
    )

    fun getHistoryEntryList(context: Context, callback: ((List<HistoryEntry>?, Throwable?) -> Unit)?) = apiCallGet(
        context = context,
        getFromStorage = ModelStorage::getHistoryEntryList,
        setToStorage = ModelStorage::saveHistoryEntryList,
        apiCall = api::getHistory,
        callback = callback
    )

    fun getAssistanceList(context: Context, callback: ((List<Assistance>?, Throwable?) -> Unit)?) = apiCallGet(
        context = context,
        getFromStorage = ModelStorage::getAssistanceList,
        setToStorage = ModelStorage::saveAssistanceList,
        apiCall = api::getAssistance,
        callback = callback
    )

    fun getVotingList(context: Context, callback: ((List<Map<String, String>>?, Throwable?) -> Unit)?) = apiCallGet(
        context = context,
        getFromStorage = ModelStorage::getVotingList,
        setToStorage = ModelStorage::saveVotingList,
        apiCall = api::getVoting,
        callback = callback
    )
}