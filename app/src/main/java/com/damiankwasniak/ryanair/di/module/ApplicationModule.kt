package com.damiankwasniak.ryanair.di.module

import android.app.Application
import android.content.Context
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.damiankwasniak.ryanair.reducer.AppReducer.appReducer
import com.damiankwasniak.ryanair.reducer.AppState
import com.damiankwasniak.ryanair.reducer.AppStateInitializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import tw.geothings.rekotlin.Store
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApplicationStore(appStateInitializer: AppStateInitializer): Store<AppState> {
        return Store(
                reducer = ::appReducer,
                state = appStateInitializer.initialAppState()
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideSchedulersProvider(): SchedulersProvider {
        return object : SchedulersProvider {
            override fun getIOScheduler(): Scheduler {
                return Schedulers.io()
            }

            override fun getMainThreadScheduler(): Scheduler {
                return AndroidSchedulers.mainThread()
            }
        }
    }
}