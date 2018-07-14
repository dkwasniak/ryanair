package com.damiankwasniak.ryanair.di.component

import com.damiankwasniak.ryanair.RyanairApplication
import com.damiankwasniak.ryanair.di.module.ApiModule
import dagger.Component
import com.damiankwasniak.ryanair.di.module.ApplicationModule
import com.damiankwasniak.ryanair.di.module.NetworkModule
import com.damiankwasniak.ryanair.di.module.OkHttpInterceptorsModule
import com.damiankwasniak.ryanair.feature.detail.ui.FlightDetailActivity
import com.damiankwasniak.ryanair.feature.mainscreen.ui.MainActivity
import com.damiankwasniak.ryanair.feature.searchresult.ui.SearchResultActivity
import com.damiankwasniak.ryanair.feature.stations.ui.StationsListActivity
import javax.inject.Singleton

/**
 * Created by damiankwasniak on 09.04.2018.
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (NetworkModule::class), (ApiModule::class), (OkHttpInterceptorsModule::class)])
interface ApplicationComponent {

    fun inject(ryanairApplication: RyanairApplication)

    fun inject(ryanairApplication: MainActivity)

    fun inject(searchResultActivity: SearchResultActivity)

    fun inject(stationsListActivity: StationsListActivity)

    fun inject(flightDetailActivity: FlightDetailActivity)

}