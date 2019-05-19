package org.redciudadana.candidatos.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.data.models.ProfileInfo

@Database(entities = arrayOf(Party::class, Profile::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun partyDao(): PartyDao
    abstract fun profileDao(): ProfileDao
}

lateinit var db: AppDatabase
@Volatile private var called = false


fun initializeDatabase(context: Context) {
    if (called) throw IllegalStateException("Function cannot be called twice")
    called = true
    db = Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase")
        .build()
}