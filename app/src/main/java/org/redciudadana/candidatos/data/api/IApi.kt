package org.redciudadana.candidatos.data.api

import org.redciudadana.candidatos.data.models.*
import retrofit2.Call
import retrofit2.http.GET


interface IApi {
    @GET("perfil.json")
    fun getProfiles(): Call<List<Profile>>

    @GET("distrito.json")
    fun getDistrictProfiles(): Call<List<Profile>>

    @GET("president.json")
    fun getPresidentProfiles(): Call<List<Profile>>

    @GET("vicepresident.json")
    fun getVicepresidentProfiles(): Call<List<Profile>>

    @GET("listado.json")
    fun getListingProfiles(): Call<List<Profile>>

    @GET("parlacen.json")
    fun getParlacenProfiles(): Call<List<Profile>>

    @GET("mayor.json")
    fun getMayorProfiles(): Call<List<Profile>>

    @GET("historial.json")
    fun getHistory(): Call<List<HistoryEntry>>

    @GET("asistencia.json")
    fun getAssistance(): Call<List<Assistance>>

    @GET("votaciones.json")
    fun getVoting(): Call<List<Map<String, String>>>

    @GET("info-distrito.json")
    fun getInfoDistrict(): Call<List<ProfileInfo>>

    @GET("info-listado.json")
    fun getInfoListing(): Call<List<ProfileInfo>>

    @GET("info-mayor.json")
    fun getInfoMayor(): Call<List<ProfileInfo>>

    @GET("info-parlacen.json")
    fun getInfoParlacen(): Call<List<ProfileInfo>>

    @GET("info-president.json")
    fun getInfoPresident(): Call<List<ProfileInfo>>

    @GET("info-vicepresident.json")
    fun getInfoVicepresident(): Call<List<ProfileInfo>>

    @GET("partido.json")
    fun getPartyList(): Call<List<Party>>

}