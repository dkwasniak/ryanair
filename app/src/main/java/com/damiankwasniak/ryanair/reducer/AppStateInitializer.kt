package com.damiankwasniak.ryanair.reducer

import javax.inject.Inject

/**
 * Created by damiankwasniak on 13.04.2018.
 */
class AppStateInitializer @Inject constructor() {

    fun initialAppState(): AppState {
        return AppState()
    }
}