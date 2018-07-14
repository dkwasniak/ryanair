package com.damiankwasniak.ryanair

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.damiankwasniak.ryanair.di.component.ApplicationComponent
import com.damiankwasniak.ryanair.di.component.DaggerApplicationComponent
import com.damiankwasniak.ryanair.di.module.ApiModule
import com.damiankwasniak.ryanair.di.module.ApplicationModule
import com.damiankwasniak.ryanair.reducer.AppState
import com.damiankwasniak.ryanair.ui.BaseActivity
import tw.geothings.rekotlin.Store
import javax.inject.Inject

class RyanairApplication:  Application(), Application.ActivityLifecycleCallbacks {

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

    private var currentActivity: BaseActivity? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent.inject(this)
        registerActivityLifecycleCallbacks(this)
    }


    fun onNoInternetConnection() {
        currentActivity?.showNoInternetError()
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {
        currentActivity = activity as? BaseActivity
    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }
}


val kidappolisApplication: RyanairApplication
    get() = RyanairApplication.instance

val applicationComponent: ApplicationComponent
    get() = RyanairApplication.instance.applicationComponent

val store: Store<AppState>
    get() = kidappolisApplication.store