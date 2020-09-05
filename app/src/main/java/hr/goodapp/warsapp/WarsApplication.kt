package hr.goodapp.warsapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WarsApplication : Application() {

    companion object {
        lateinit var ctx: Context
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

    }
}