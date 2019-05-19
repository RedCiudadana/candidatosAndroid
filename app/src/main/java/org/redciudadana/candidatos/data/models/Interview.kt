package org.redciudadana.candidatos.data.models

import androidx.room.Entity

@Entity(primaryKeys = arrayOf("profileId", "url"))
data class Interview(
    val profileId: String? = null,
    val name: String? = null,
    val url: String? = null,
    val photo: String? = null
)