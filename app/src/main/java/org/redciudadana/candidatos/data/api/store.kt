package org.redciudadana.candidatos.data.api

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import eleccionmp.redciudadana.org.eleccionmp.utils.Storage
import org.redciudadana.candidatos.data.models.Assistance
import org.redciudadana.candidatos.data.models.HistoryEntry
import org.redciudadana.candidatos.data.models.Profile

object ModelStorage {
    private const val diputadosKey = "diputados"
    private const val historialKey = "historial"
    private const val assistanceKey = "asistencia"
    private const val votingKey = "votaciones"

    private fun <T> getListFromStorage(context: Context, classType: Class<T>, key: String): List<T>? {
        val type = Types.newParameterizedType(List::class.java, classType)
        val adapter = Moshi.Builder().build().adapter<List<T>>(type)
        val stored = Storage.getStringPreference(context, key) ?: return null
        return adapter.fromJson(stored)

    }

    private fun getMapListFromStorage(context: Context, key: String): List<Map<String, String>>? {
        val classType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val type = Types.newParameterizedType(List::class.java, classType)
        val adapter = Moshi.Builder().build().adapter<List<Map<String, String>>>(type)
        val stored = Storage.getStringPreference(context, key) ?: return null
        return adapter.fromJson(stored)
    }

    private fun <T> saveListToStorage(context: Context, key: String, list: List<T>, listType: Class<T>) {
        val type = Types.newParameterizedType(List::class.java, listType)
        val adapter = Moshi.Builder().build().adapter<List<T>>(type)
        Storage.setStringPreference(context, key, adapter.toJson(list))
    }

    private fun saveMapListToStorage(context: Context, key: String, list: List<Map<String, String>>) {
        val listType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val type = Types.newParameterizedType(List::class.java, listType)
        val adapter = Moshi.Builder().build().adapter<List<Map<String, String>>>(type)
        Storage.setStringPreference(context, key, adapter.toJson(list))
    }


    fun getProfileListFromStorage(context: Context): List<Profile>?  = getListFromStorage(
        context = context,
        classType = Profile::class.java,
        key = diputadosKey
    )

    fun saveProfileListToStorage(context: Context, profileList: List<Profile>) = saveListToStorage(
        context = context,
        key = diputadosKey,
        list = profileList,
        listType = Profile::class.java
    )

    fun getHistoryEntryList(context: Context): List<HistoryEntry>? = getListFromStorage(
        context = context,
        classType = HistoryEntry::class.java,
        key = historialKey
    )

    fun saveHistoryEntryList(context: Context, historyEntryList: List<HistoryEntry>) = saveListToStorage(
        context = context,
        key = historialKey,
        list = historyEntryList,
        listType = HistoryEntry::class.java
    )

    fun getAssistanceList(context: Context): List<Assistance>? = getListFromStorage(
        context = context,
        classType = Assistance::class.java,
        key = assistanceKey
    )

    fun saveAssistanceList(context: Context, assistanceList: List<Assistance>) = saveListToStorage(
        context = context,
        key = assistanceKey,
        list = assistanceList,
        listType = Assistance::class.java
    )

    fun getVotingList(context: Context): List<Map<String, String>>? = getMapListFromStorage(
        context = context,
        key = votingKey
    )


    fun saveVotingList(context: Context, votingList: List<Map<String, String>>) = saveMapListToStorage(
        context = context,
        key = votingKey,
        list = votingList
    )
}