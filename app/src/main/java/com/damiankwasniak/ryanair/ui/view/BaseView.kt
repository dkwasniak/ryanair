package com.damiankwasniak.ryanair.ui.view

import android.support.annotation.StringRes

interface BaseView {

    fun showError(@StringRes msg: Int)

}