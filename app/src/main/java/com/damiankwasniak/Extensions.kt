package com.damiankwasniak

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.jakewharton.rxbinding2.view.RxView
import com.nomtek.utils.gone
import com.nomtek.utils.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import rx.Observable
import java.util.concurrent.TimeUnit

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

fun <K : Activity> Context.goToActivity(clazz: Class<K>, bundle: Bundle? = null) {
    val intent = Intent(this, clazz)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    (this as Activity).startActivity(intent)
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun View.onClickWithDebounce(consumer: (Any) -> Unit) {
    RxView.clicks(this)
            .throttleFirst(300L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe(consumer)
}