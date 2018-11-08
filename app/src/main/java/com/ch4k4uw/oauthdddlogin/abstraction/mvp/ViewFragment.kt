package com.ch4k4uw.oauthdddlogin.abstraction.mvp

import android.os.Bundle
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.ch4k4uw.oauthdddlogin.R
import com.ch4k4uw.oauthdddlogin.abstraction.util.AppFragmentManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

typealias DroidView = android.view.View

abstract class ViewFragment: Fragment(), View {
    @Inject
    lateinit var appFragmentManager: AppFragmentManager

    private var progressInstances = 0
    private var progress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        setHasOptionsMenu(true)

        AndroidSupportInjection.inject(this)

    }

    override fun onViewCreated(view: DroidView, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.progress)
    }

    override fun showIndeterminateProgress() {
        progress?.visibility = DroidView.VISIBLE
        ++progressInstances
    }

    override fun hideIndeterminateProgress(force: Boolean) {
        if(progressInstances - 1 == 0 || force) {
            progress?.visibility = DroidView.GONE
        }

        progressInstances = if (force) 0 else progressInstances - 1
        if(progressInstances < 0) {
            throw RuntimeException("Ops, something goes wrong!!!")
        }
    }
}