package org.redciudadana.candidatos.data.api

import org.redciudadana.candidatos.data.models.Assistance
import org.redciudadana.candidatos.data.models.HistoryEntry
import org.redciudadana.candidatos.data.models.Profile
import retrofit2.Call
import retrofit2.http.GET


interface IApi {
    @GET("perfil.json")
    fun getProfiles(): Call<List<Profile>>

    @GET("historial.json")
    fun getHistory(): Call<List<HistoryEntry>>

    @GET("asistencia.json")
    fun getAssistance(): Call<List<Assistance>>

    @GET("votaciones.json")
    fun getVoting(): Call<List<Map<String, String>>>
}