package com.damiankwasniak.ryanair.ui

import android.support.v7.app.AppCompatActivity
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.showToast
import com.nomtek.utils.toolbar.ToolbarController

/**
 * Created by damiankwasniak on 10.04.2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    val toolbarController by lazy { ToolbarController() }

    fun showNoInternetError() {
        showToast(getString(R.string.general_network_not_available))
    }
}