package org.redciudadana.candidatos.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryEntry(
    val id: String?,
    val perfil: String?,
    val ano: String?,
    val partido: String?
) : Parcelable
