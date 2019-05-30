package org.redciudadana.candidatos.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import com.squareup.moshi.Json

@Entity(
    primaryKeys = arrayOf("perfil", "url"),
    foreignKeys = arrayOf(
        ForeignKey(entity = Profile::class, parentColumns = ["id"], childColumns = ["perfil"])
    )
)
data class Interview(
    val perfil: String,
    val url: String,
    @Json(name ="nombre") val name: String? = null,
    val photo: String? = null
)