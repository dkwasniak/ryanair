package com.damiankwasniak.ryanair.ui.presenter

import android.support.annotation.CallSuper
import com.damiankwasniak.ryanair.ui.view.BaseView


abstract class BasePresenter<T : BaseView> {

    protected var view: T? = null

    protected var isGeneralErrorShown = false

    @CallSuper
    open fun attached(view: T) {
        this.view = view
    }

    @CallSuper
    open fun detached() {
        this.view = null
    }

    @CallSuper
    open fun resetState() {
        isGeneralErrorShown = false
    }
}