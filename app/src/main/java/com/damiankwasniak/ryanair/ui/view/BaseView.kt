package com.damiankwasniak.ryanair.ui.view

import com.damiankwasniak.emptyString

interface BaseView {

    fun showError(error: String = emptyString){
        // no op
    }

}