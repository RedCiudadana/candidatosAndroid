package org.redciudadana.candidatos.data.db

import androidx.room.*
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.ElectionTypeConverter
import org.redciudadana.candidatos.data.models.Profile

@Dao
interface ProfileDao {
    @TypeConverters(ElectionTypeConverter::class)
    @Query("select * from profile where electionType = :electionType")
    fun getProfilesFor(electionType: ElectionType): List<Profile>

    @TypeConverters(ElectionTypeConverter::class)
    @Query("select * from profile where electionType = :electionType and distrito = :district")
    fun getProfilesFor(electionType: ElectionType, district: String): List<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfiles(profiles: List<Profile>)

    @Query("select * from profile where id = :id limit 1")
    fun findProfile(id: String): Profile?

    @Update
    fun updateProfile(profile: Profile)

    @TypeConverters(ElectionTypeConverter::class)
    @Query("select distinct distrito from profile where electionType = 'DISTRICT' and distrito != '' order by distrito")
    fun getDistricts(): List<String>
}