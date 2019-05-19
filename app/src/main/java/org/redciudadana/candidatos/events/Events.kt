package org.redciudadana.candidatos.events

object Events {

    enum class EventType {
        PROFILES_UPDATED,
        UPDATE_ERROR,
    }

    interface Listener {
        fun onEvent(eventType: EventType)
    }


    val listeners: MutableList<Pair<EventType, Listener>> = mutableListOf()

    fun registerListener(eventType: EventType, listener: Listener) {
        listeners.add(Pair(eventType, listener))
    }

    fun unregisterListener(listener: Listener) {
        val index = listeners.indexOfFirst { it.second === listener }
        listeners.removeAt(index)
    }

    fun onEvent(eventType: EventType) {
        listeners
            .filter { it.first == eventType }
            .forEach {
                it.second.onEvent(eventType)
            }
    }
}