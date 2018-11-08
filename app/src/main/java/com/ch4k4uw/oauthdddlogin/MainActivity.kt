package com.ch4k4uw.oauthdddlogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ch4k4uw.oauthdddlogin.login.SimpleLoginView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SimpleLoginView(), "login")
                .commit()
        }

    }
}
