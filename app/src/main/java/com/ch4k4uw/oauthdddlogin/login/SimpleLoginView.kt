package com.ch4k4uw.oauthdddlogin.login

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.ch4k4uw.oauthdddlogin.R
import com.ch4k4uw.oauthdddlogin.abstraction.login.LoginPresenter
import com.ch4k4uw.oauthdddlogin.abstraction.login.LoginView
import com.ch4k4uw.oauthdddlogin.abstraction.mvp.DroidView
import com.ch4k4uw.oauthdddlogin.abstraction.mvp.ViewFragment
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.welcome.SimpleWelcomeView
import java.util.*
import javax.inject.Inject

class SimpleLoginView: ViewFragment(), LoginView {
    private var mLogin: EditText? = null
    private var mPassword: EditText? = null
    private var mRememberMe: CheckBox? = null
    private var mSubmit: Button? = null

    private var mCheckLoginStatus = true
    private var mLoadLoginPreferences = true
    private var mAnimate = true

    @Inject
    lateinit var mPresenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: DroidView, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enableLogin = mLogin?.isEnabled ?: true
        val enablePassword = mPassword?.isEnabled ?: true
        val enableRememberMe = mRememberMe?.isEnabled ?: true
        val enableSubmit = mSubmit?.isEnabled ?: true

        mLogin = view.findViewById(R.id.login)
        mPassword = view.findViewById(R.id.password)
        mRememberMe = view.findViewById(R.id.remember_credentials)
        mSubmit = view.findViewById(R.id.submit)

        mPassword!!.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEND) {
                mSubmit!!.performClick()
            }
            false
        }

        mSubmit!!.setOnClickListener {
            mPresenter.login(mLogin!!.text.toString(), mPassword!!.text.toString(), mRememberMe!!.isChecked)
        }

        val textChange = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mLogin!!.error = null
                mPassword!!.error = null
            }
        }

        mLogin!!.addTextChangedListener(textChange)
        mPassword!!.addTextChangedListener(textChange)

        if(mAnimate) {
            val translation = 300f
            val pxTranslation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, translation, resources.displayMetrics)

            mLogin!!.translationY = pxTranslation
            mPassword!!.translationY = pxTranslation
            mRememberMe!!.translationY = pxTranslation
            mSubmit!!.translationY = pxTranslation
            mLogin!!.alpha = 0f
            mPassword!!.alpha = 0f
            mRememberMe!!.alpha = 0f
            mSubmit!!.alpha = 0f

            val interpolator = OvershootInterpolator()
            val animListener = object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    mLogin?.requestLayout()
                    mPassword?.requestLayout()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            }

            mLogin!!.animate().alpha(1f).translationY(0f).setDuration(900L).setInterpolator(interpolator).setListener(animListener).start()
            mPassword!!.animate().alpha(1f).translationY(0f).setDuration(900L).setStartDelay(160L).setInterpolator(interpolator).setListener(animListener).start()
            mRememberMe!!.animate().alpha(1f).translationY(0f).setDuration(900L).setStartDelay(312L).setInterpolator(interpolator).setListener(animListener).start()
            mSubmit!!.animate().alpha(1f).translationY(0f).setDuration(900L).setStartDelay(472L).setInterpolator(interpolator).setListener(animListener).start()

            mAnimate = false
        }

        mLogin?.isEnabled = enableLogin
        mPassword?.isEnabled = enablePassword
        mRememberMe?.isEnabled = enableRememberMe
        mSubmit?.isEnabled = enableSubmit

    }

    override fun onResume() {
        super.onResume()
        mPresenter.view = this
        if(mLoadLoginPreferences) {
            mPresenter.loadLoginPreferences()
            mLoadLoginPreferences = false
        } else if(mCheckLoginStatus) {
            mPresenter.findLoggedUser()
            mCheckLoginStatus = false
        }

    }

    override fun onPause() {
        super.onPause()
        mPresenter.view = null
    }

    override fun showWelcomeView(loggedUser: LoggedUser) {
        Toast.makeText(activity!!, R.string.successful_login_message, Toast.LENGTH_LONG)
            .show()

        appFragmentManager
            .beginTransaction(fragmentManager!!)
            .replace(R.id.container, SimpleWelcomeView(), "welcome")
            .animate()
            .commit()

    }

    override fun showLoginObligationMessage() {
        mLogin?.error = String.format(Locale.getDefault(), getString(R.string.field_obligation_template), getString(R.string.login_edittext_label))
        mLogin?.requestFocus()
    }

    override fun showPasswordObligationMessage() {
        mPassword?.error = String.format(Locale.getDefault(), getString(R.string.field_obligation_template), getString(R.string.password_edittext_label))
        mPassword?.requestFocus()
    }

    override fun enableLoginControls(enable: Boolean) {
        mLogin?.isEnabled = enable
        mPassword?.isEnabled = enable
        mSubmit?.isEnabled = enable
        mRememberMe?.isEnabled = enable
    }

    override fun showInvalidLoginOrPasswordMessage() {
        Toast.makeText(activity, R.string.invalid_login_or_password_message, Toast.LENGTH_LONG)
            .show()
    }

    override fun showGenericLoginErrorMessage() {
        Toast.makeText(activity, R.string.generic_login_precess_error_message, Toast.LENGTH_LONG)
            .show()
    }

    override fun showGenericLoggedUserQueryErrorMessage() {
        Toast.makeText(activity, R.string.generic_user_query_error_message, Toast.LENGTH_LONG)
            .show()
    }

    override fun defineLoginPreferences(login: String, password: String, remember: Boolean) {
        mLogin?.setText(login)
        mPassword?.setText(password)
        mRememberMe?.isChecked = remember
        if(mCheckLoginStatus){
            mPresenter.findLoggedUser()
            mCheckLoginStatus = false
        }
    }
}