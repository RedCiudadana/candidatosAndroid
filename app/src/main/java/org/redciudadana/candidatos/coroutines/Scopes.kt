package org.redciudadana.candidatos.coroutines


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
val uiScope: CoroutineScope = CoroutineScope(uiDispatcher)

val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
val bgScope: CoroutineScope = CoroutineScope(bgDispatcher)
