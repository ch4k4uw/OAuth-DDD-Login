package com.ch4k4uw.oauthdddlogin.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ch4k4uw.oauthdddlogin.abstraction.util.AppFragmentManager
import javax.inject.Inject
import javax.inject.Named

class SimpleAppFragmentManager @Inject constructor(@Named("app_animations") private val enableAnimations: Boolean): AppFragmentManager {
    override fun beginTransaction(fragmentManager: FragmentManager): AppFragmentManager.AppFragmentTransaction
            = SimpleAppFragmentTransaction(fragmentManager, enableAnimations)

    override fun popBackStack(fragmentManager: FragmentManager)
            = fragmentManager.popBackStack()

    class SimpleAppFragmentTransaction(private val manager: FragmentManager, private val enableAnimations: Boolean): AppFragmentManager.AppFragmentTransaction {
        private var mAnimate: Boolean = false
        private var mReplace: Boolean = false
        private var mContainerViewId: Int? = null
        private var mFragment: Fragment? = null
        private var mTag: String? = null
        private var mAddToBackStack: Boolean = false

        override fun animate(): AppFragmentManager.AppFragmentTransaction {
            mAnimate = true && enableAnimations
            return this
        }

        override fun replace(containerViewId: Int, fragment: Fragment, tag: String): AppFragmentManager.AppFragmentTransaction {
            mReplace = true
            mContainerViewId = containerViewId
            mFragment = fragment
            mTag = tag
            return this
        }

        override fun addToBackStack(): AppFragmentManager.AppFragmentTransaction {
            mAddToBackStack = true
            return this
        }

        override fun commit(): Int {
            val transaction = manager.beginTransaction()
            if(mAnimate) {
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                /*transaction.setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )*/
            }

            if(mReplace) {
                transaction.replace(mContainerViewId!!, mFragment!!, mTag!!)
            }

            if(mAddToBackStack) {
                transaction.addToBackStack(null)
            }

            return transaction.commit()

        }

    }
}