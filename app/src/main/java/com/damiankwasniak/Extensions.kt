package com.damiankwasniak

import android.view.View
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.nomtek.utils.gone
import com.nomtek.utils.visible
import rx.Observable

val emptyString: String = ""

fun <T> applySchedulers(schedulersProvider: SchedulersProvider): Observable.Transformer<T, T> {
    return Observable.Transformer {
        it.subscribeOn(schedulersProvider.ioScheduler)
                .observeOn(schedulersProvider.mainThreadScheduler)
    }
}

fun View.setViewVisibility(isVisible: Boolean?) {
    if (isVisible != null && isVisible) {
        this.visible()
    } else {
        this.gone()
    }
}