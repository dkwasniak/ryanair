package com.damiankwasniak.ryanair.di.component

import com.damiankwasniak.ryanair.RyanairApplication
import com.damiankwasniak.ryanair.di.module.ApiModule
import dagger.Component
import com.damiankwasniak.ryanair.di.module.ApplicationModule
import com.damiankwasniak.ryanair.di.module.NetworkModule
import com.damiankwasniak.ryanair.di.module.OkHttpInterceptorsModule
import com.damiankwasniak.ryanair.feature.mainscreen.MainActivity
import javax.inject.Singleton

/**
 * Created by damiankwasniak on 09.04.2018.
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (NetworkModule::class), (ApiModule::class), (OkHttpInterceptorsModule::class)])
interface ApplicationComponent {

    fun inject(ryanairApplication: RyanairApplication)

    fun inject(ryanairApplication: MainActivity)


}