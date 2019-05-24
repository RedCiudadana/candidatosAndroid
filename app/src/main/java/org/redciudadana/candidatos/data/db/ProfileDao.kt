package org.redciudadana.candidatos.data.db

import androidx.room.*
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.ElectionTypeConverter
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.data.models.Profile

@Dao
interface ProfileDao {

    data class ProfileParty(
        @Embedded
        val profile: Profile,
        val partyName: String
    )

    @TypeConverters(ElectionTypeConverter::class)
    @Query("select profile.*, coalesce(party.nombreCorto, profile.nombrePartido) as partyName from profile left join party on (party.id = profile.partido) where electionType = :electionType")
    fun getProfilesFor(electionType: ElectionType): List<ProfileParty>

    @TypeConverters(ElectionTypeConverter::class)
    @Query("select * from profile where electionType = :electionType and partido = :party order by cast(casilla as unsigned), nombre")
    fun getProfilesFor(electionType: ElectionType, party: String): List<Profile>

    @TypeConverters(ElectionTypeConverter::class)
    @Query("select * from profile where electionType = :electionType and distrito = :district and partido = :party")
    fun getProfilesFor(electionType: ElectionType, district: String, party: String): List<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfiles(profiles: List<Profile>)

    @Query("select * from profile where id = :id limit 1")
    fun findProfile(id: String): Profile?

    @Update
    fun updateProfile(profile: Profile)

    @TypeConverters(ElectionTypeConverter::class)
    @Query("select distinct distrito from profile where electionType = 'DISTRICT' and distrito != '' order by distrito")
    fun getDistricts(): List<String>

    @TypeConverters(ElectionTypeConverter::class)
    @Query("""select distinct party.* from party join profile on (party.id = profile.partido)
            where profile.electionType = :electionType order by party.nombreCompleto""")
    fun getParties(electionType: ElectionType): List<Party>

    @TypeConverters(ElectionTypeConverter::class)
    @Query("""select distinct party.* from party join profile on (party.id = profile.partido)
            where profile.electionType = :electionType and profile.distrito = :district order by party.nombreCompleto""")
    fun getDistrictParties(electionType: ElectionType, district: String): List<Party>
}