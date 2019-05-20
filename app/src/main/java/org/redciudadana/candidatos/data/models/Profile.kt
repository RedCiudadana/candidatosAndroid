package org.redciudadana.candidatos.data.models

import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(
    indices = arrayOf(
        Index(value=["distrito"]),
        Index(value=["nombre"]),
        Index(value=["partido"])
    ),
    foreignKeys = arrayOf(
        ForeignKey(entity = Party::class, parentColumns = ["id"], childColumns = ["partido"])
    )
)
@TypeConverters(ElectionTypeConverter::class)
data class Profile(
    @PrimaryKey val id: String = "",
    val nombre: String? = null,
    val institucion: String? = null,
    val partido: String? = null,
    val profesion: String? = null,
    val educacion: String? = null,
    val edad: String? = null,
    val estadocivil: String? = null,
    val anosprofesional: String? = null,
    val colegiado: String? = null,
    val ocupacion: String? = null,
    val planTrabajo: String? = null,
    val biografia: String? = null,
    val experienciaProfesional: String? = null,
    val experienciaAcademica: String? = null,
    val proyeccion: String? = null,
    val primerNombre: String? = null,
    val apellidos: String? = null,
    val partidopostulante: String? = null,
    val partidoactual: String? = null,
    val distrito: String? = null,
    val nacimiento: String? = null,
    val estado: String? = null,
    val historialpolitico: String? = null,
    val informaciongeneral: String? = null,
    val cargo: String? = null,
    val sexo: String? = null,
    val tw: String? = null,
    val fb: String? = null,
    val fotoUrlPartido: String? = null,
    val fotoUrl: String? = null,
    val Contacto: String? = null,
    val direccion: String? = null,
    val telefono: String? = null,
    val ext: String? = null,
    val email: String? = null,
    var electionType: ElectionType? = null,
    @Embedded(prefix = "info_") var profileInfo: ProfileInfo? = ProfileInfo()
) : Parcelable


@Parcelize
data class ProfileInfo (
    @Ignore var id: String? = null,
    var biography: String? = null,
    @Json(name = "experienciaAcadémica") var experienciaAcademica: String? = null,
    @Json(name = "historialPolítico") var historialPolitico: String? = null,
    var planesDeGobiernoURL: String? = null
): Parcelable