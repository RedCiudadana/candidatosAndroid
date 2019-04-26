package org.redciudadana.candidatos.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Assistance(
    val perfilId: String? = null,
    val porcentaje: String? = null
): Parcelable