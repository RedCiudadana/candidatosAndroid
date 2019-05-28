package org.redciudadana.candidatos.data.models

import androidx.room.TypeConverter
import org.redciudadana.candidatos.R

enum class ElectionType(val label: String, val drawable: Int) {
    PRESIDENT("Presidencial", R.drawable.menu_1),
    VICEPRESIDENT("Vicepresidencial", R.drawable.menu_1),
    NATIONAL_LISTING("Listado nacional", R.drawable.menu_2),
    DISTRICT("Distrital", R.drawable.menu_3),
    MAYOR("Alcaldía", R.drawable.menu_4),
    PARLACEN("Parlacen", R.drawable.menu_5);

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