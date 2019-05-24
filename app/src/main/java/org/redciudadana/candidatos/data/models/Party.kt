package org.redciudadana.candidatos.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Party(
    @PrimaryKey
    val id: String = "",
    val nombreCorto: String? = null,
    val nombreCompleto: String? = null,
    @Json(name = "logoURL") val logoUrl: String? = null
)