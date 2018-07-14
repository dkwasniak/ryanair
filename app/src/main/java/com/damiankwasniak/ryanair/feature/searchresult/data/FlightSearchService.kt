package com.damiankwasniak.ryanair.feature.searchresult.data

import com.damiankwasniak.applySchedulers
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.damiankwasniak.ryanair.feature.searchresult.action.AvailableFlightsFetched
import com.damiankwasniak.ryanair.feature.searchresult.action.AvailableFlightsFetchingError
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightsList
import com.damiankwasniak.ryanair.network.BaseService
import com.damiankwasniak.ryanair.network.FlightsApiService
import com.damiankwasniak.ryanair.network.mapper.NetworkExceptionMapper
import com.damiankwasniak.ryanair.store
import rx.lang.kotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightSearchService @Inject constructor(
        private val flightsApiService: FlightsApiService,
        private val schedulersProvider: SchedulersProvider,
        private val networkErrorExceptionMapper: NetworkExceptionMapper
) : BaseService() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("pl_PL"))
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pl_PL")) //this format changea

    init {
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        dateFormatter.timeZone = TimeZone.getDefault()
    }

    fun fetchedAvailableFlights(origin: String?, dest: String?, dateOut: String, adults: Int, teen: Int, children: Int) {
        flightsApiService.searchFlights(origin, dest, dateOut, adults, teen, children)
                .compose(applySchedulers(schedulersProvider))
                .subscribeBy(
                        onNext = {
                            store.dispatch(AvailableFlightsFetched(getFlights(it)))
                        },
                        onError = {
                            val err = networkErrorExceptionMapper.mapToApiError(it)
                            handleApiError(err) {
                                store.dispatch(AvailableFlightsFetchingError())
                            }
                        }
                )
    }

    private fun getFlights(it: FlightsList): List<FlightViewModel> {
        val currency = it.currency
        var flightDuration: String
        var flightNumber: String
        var flightDateOut: String
        return it.trips.flatMap {
            it.dates.flatMap {
                it.flights.flatMap {
                    flightNumber = it.flightNumber
                    flightDuration = it.duration
                    flightDateOut = getDate(it.timeUTC[0])
                    it.regularFare.fares.map {
                        // businnesFare & leusureFare is set to 0.0 because API is not providing this data
                        FlightViewModel(flightDateOut, flightNumber, flightDuration, it.amount, 0.0, 0.0, currency)
                    }
                }
            }
        }

    }

    private fun getDate(date: String): String {
        return dateFormatter.format(formatter.parse(date))
    }

}