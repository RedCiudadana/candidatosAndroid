package org.redciudadana.candidatos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.redciudadana.candidatos.data.models.Party

@Dao
interface PartyDao {

    @Query("select * from party")
    fun getAll(): List<Party>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParties(parties: List<Party>)
}