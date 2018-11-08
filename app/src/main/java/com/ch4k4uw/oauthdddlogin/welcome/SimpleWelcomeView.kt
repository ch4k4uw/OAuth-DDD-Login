package com.ch4k4uw.oauthdddlogin.welcome

import android.animation.Animator
import android.animation.AnimatorInflater
import android.graphics.drawable.Animatable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ch4k4uw.oauthdddlogin.R
import com.ch4k4uw.oauthdddlogin.abstraction.mvp.DroidView
import com.ch4k4uw.oauthdddlogin.abstraction.mvp.ViewFragment
import com.ch4k4uw.oauthdddlogin.abstraction.welcome.WelcomePresenter
import com.ch4k4uw.oauthdddlogin.abstraction.welcome.WelcomeView
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.login.SimpleLoginView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import java.util.*
import javax.inject.Inject

class SimpleWelcomeView: ViewFragment(), WelcomeView {
    private lateinit var mPicture: SimpleDraweeView
    private lateinit var mMessage: TextView
    private lateinit var mLogout: Button

    @Inject
    lateinit var mPresenter: WelcomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: DroidView, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPicture = view.findViewById(R.id.image)
        mMessage = view.findViewById(R.id.welcome_message)
        mLogout = view.findViewById(R.id.logout)

        mLogout.setOnClickListener {
            mPresenter.logout()
        }

    }

    override fun onResume() {
        super.onResume()
        mPresenter.view = this
        mPresenter.findLoggedUser()
    }

    override fun showWelcomeMessage(user: LoggedUser) {
        if(user.photo.isNotBlank()) {
            @Suppress("DEPRECATION")
            val frescoListener = object: BaseControllerListener<Any>() {
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onFinalImageSet(id: String?, imageInfo: Any?, animatable: Animatable?) {
                    super.onFinalImageSet(id, imageInfo, animatable)
                    val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                        resources.getColor(R.color.colorAccent, null)
                    else
                        resources.getColor(R.color.colorAccent)

                    val roundingParams = RoundingParams.fromCornersRadius(5f)
                    roundingParams.setBorder(color, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics))
                    roundingParams.roundAsCircle = true
                    mPicture.hierarchy.roundingParams = roundingParams

                }

                override fun onFailure(id: String?, throwable: Throwable?) {
                    super.onFailure(id, throwable)
                    mPicture.visibility = View.GONE
                }
            }

            val uri = Uri.parse(user.photo)
            val dc = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(frescoListener)
                .setOldController(mPicture.controller)
                .build()

            mPicture.controller = dc
        }

        mMessage.text = String.format(Locale.getDefault(), getString(R.string.welcome_template), user.name)

    }

    override fun showLoginView() {
        appFragmentManager
            .beginTransaction(fragmentManager!!)
            .animate()
            .replace(R.id.container, SimpleLoginView(), "login")
            .commit()
    }

    override fun showErrorMessageOnLogout() {
        Toast.makeText(activity!!, R.string.logout_error_message, Toast.LENGTH_LONG)
            .show()
    }

    override fun showErrorOnFindLoggedUser() {
        Toast.makeText(activity!!, R.string.find_logged_user_error_message, Toast.LENGTH_LONG)
            .show()
    }

    override fun enableLogoutControls(enable: Boolean) {
        mLogout.isEnabled = enable
    }
}