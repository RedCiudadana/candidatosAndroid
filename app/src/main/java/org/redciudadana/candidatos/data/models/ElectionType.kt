package org.redciudadana.candidatos.data.models

enum class ElectionType(val label: String) {
    PRESIDENT("Presidencial"),
    NATIONAL_LISTING("Listado nacional"),
    DISTRICT("Distrital"),
    MUNICIPAL("Municipal"),
    PARLACEN("Parlacen");

    override fun toString() = label
}