package hr.goodapp.warsapp

import android.app.Application
import android.content.Context
import hr.goodapp.warsapp.ui.common.LocalizationUtil

class WarsApplication : Application() {

    companion object {
        lateinit var ctx: Context
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

    }
}