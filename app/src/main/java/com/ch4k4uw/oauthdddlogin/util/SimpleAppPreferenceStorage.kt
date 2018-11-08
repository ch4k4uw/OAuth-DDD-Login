package com.ch4k4uw.oauthdddlogin.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.ch4k4uw.oauthdddlogin.abstraction.util.AppPreferenceStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SimpleAppPreferenceStorage @Inject constructor(private val context: Context): AppPreferenceStorage {
    override var login: Observable<String>
        get() = getValue { storedLogin }
        set(value) = setValue(value) { storedLogin = it }

    override var password: Observable<String>
        get() = getValue { storedPassword }
        set(value) = setValue(value) { storedPassword = it }

    override var rememberCredentials: Observable<Boolean>
        get() = getValue { storedRememberCredentials }
        set(value) = setValue(value) { storedRememberCredentials = it }

    private fun <T> getValue(value: () -> T): Observable<T>
            = Observable.defer {
            Observable.just(value())
        }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    @SuppressLint("CheckResult")
    private fun <T> setValue(observable: Observable<T>, consumer: (T) -> Unit) {
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                consumer(it)
            }
    }

    private val sharedPreference: SharedPreferences
        get()
        = context.getSharedPreferences("app_preference_storage", Context.MODE_PRIVATE)

    private var storedLogin: String
        get() {
            synchronized(context) {
                return sharedPreference.getString("login", "")!!
            }
        }
        @SuppressLint("ApplySharedPref")
        set(value) {
            synchronized(context) {
                sharedPreference
                    .edit()
                    .putString("login", value)
                    .commit()
            }
        }

    private var storedPassword: String
        get() {
            synchronized(context) {
                return sharedPreference.getString("password", "") ?: ""
            }
        }
        @SuppressLint("ApplySharedPref")
        set(value) {
            synchronized(context) {
                sharedPreference
                    .edit()
                    .putString("password", value)
                    .commit()
            }
        }

    private var storedRememberCredentials: Boolean
        get() {
            synchronized(context) {
                return sharedPreference.getBoolean("remember_credentials", false)
            }
        }
        @SuppressLint("ApplySharedPref")
        set(value) {
            synchronized(context) {
                sharedPreference
                    .edit()
                    .putBoolean("remember_credentials", value)
                    .commit()
            }
        }
}