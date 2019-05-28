package org.redciudadana.candidatos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import org.redciudadana.candidatos.data.models.Interview

@Dao
interface InterviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInterviewList(interviewList: List<Interview>)
}