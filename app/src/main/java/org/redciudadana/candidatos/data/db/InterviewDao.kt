package org.redciudadana.candidatos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.redciudadana.candidatos.data.models.Interview

@Dao
interface InterviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInterviewList(interviewList: List<Interview>)

    @Query("select * from interview where perfil = :profileId order by name")
    fun getInterviews(profileId: String): List<Interview>
}