package org.redciudadana.candidatos.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Party(
    @PrimaryKey
    val id: String = "",
    val name: String? = null
)