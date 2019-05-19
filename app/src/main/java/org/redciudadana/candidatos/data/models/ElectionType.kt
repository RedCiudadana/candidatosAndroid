package org.redciudadana.candidatos.data.models

import androidx.room.TypeConverter

enum class ElectionType(val label: String) {
    PRESIDENT("Presidencial"),
    VICEPRESIDENT("Vicepresidencial"),
    NATIONAL_LISTING("Listado nacional"),
    DISTRICT("Distrital"),
    MAYOR("Mayor"),
    PARLACEN("Parlacen");

    val keyName: String
        get() = "${label}Key"
}

class ElectionTypeConverter {
    @TypeConverter
    fun toString(electionType: ElectionType?): String? {
        return electionType?.toString()
    }

    @TypeConverter
    fun toEnum(electionType: String?): ElectionType? {
        if (electionType == null) return null
        return try {
            ElectionType.valueOf(electionType)
        } catch (error: IllegalArgumentException) {
            null
        }

    }
}