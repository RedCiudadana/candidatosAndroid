package org.redciudadana.candidatos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.redciudadana.candidatos.data.models.HistoryEntry

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistoryEntryList(historyEntryList: List<HistoryEntry>)

    @Query("select * from historyentry where perfil = :profileId")
    fun getHistoryList(profileId: String): List<HistoryEntry>
}