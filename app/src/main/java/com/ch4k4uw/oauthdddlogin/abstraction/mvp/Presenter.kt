package com.ch4k4uw.oauthdddlogin.abstraction.mvp

interface Presenter<T> where T: View {
    var view: T?
}