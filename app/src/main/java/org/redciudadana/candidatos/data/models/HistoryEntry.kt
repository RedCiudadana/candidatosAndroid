package org.redciudadana.candidatos.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    indices = arrayOf(
        Index(value=["perfil"]),
        Index(value=["partido"])
    ),
    foreignKeys = arrayOf(
        ForeignKey(entity = Profile::class, parentColumns = ["id"], childColumns = ["perfil"])
    )
)
@Parcelize
data class HistoryEntry(
    @PrimaryKey val id: String,
    val perfil: String?,
    val ano: String?,
    val partido: String?
) : Parcelable
