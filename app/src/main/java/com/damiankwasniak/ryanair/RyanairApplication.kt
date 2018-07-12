package com.damiankwasniak.ryanair

import android.app.Application
import com.damiankwasniak.ryanair.di.component.ApplicationComponent
import com.damiankwasniak.ryanair.di.component.DaggerApplicationComponent
import com.damiankwasniak.ryanair.di.module.ApiModule
import com.damiankwasniak.ryanair.di.module.ApplicationModule
import com.damiankwasniak.ryanair.reducer.AppState
import tw.geothings.rekotlin.Store
import javax.inject.Inject

class RyanairApplication:  Application() {

    companion object {
        lateinit var instance: RyanairApplication
            private set
    }

    val applicationComponent: ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .apiModule(ApiModule(BuildConfig.STATIONS_SERVER_URL,BuildConfig.FLIGHTS_SERVER_URL)).build()

    @Inject
    lateinit var store: Store<AppState>


    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent.inject(this)
    }
}


val kidappolisApplication: RyanairApplication
    get() = RyanairApplication.instance

val applicationComponent: ApplicationComponent
    get() = RyanairApplication.instance.applicationComponent

val store: Store<AppState>
    get() = kidappolisApplication.store