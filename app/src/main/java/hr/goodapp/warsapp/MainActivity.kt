package hr.goodapp.warsapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import hr.goodapp.warsapp.data.prefs.PreferenceStorage
import hr.goodapp.warsapp.data.prefs.SharedPreferenceStorage
import hr.goodapp.warsapp.ui.common.LocalizationUtil
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // TODO Hilt can not injected SharedPreferences in this variable
    //@Inject lateinit var preferenceStorage: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
    }
    override fun attachBaseContext(newBase: Context) {
        // TODO Hilt can not injected SharedPreferences in this function
        if (SharedPreferenceStorage(WarsApplication.ctx).currentLanguage != "-"){
            super.attachBaseContext(LocalizationUtil.applyLanguageContext(newBase, SharedPreferenceStorage(WarsApplication.ctx).currentLanguage))
        }else{
            super.attachBaseContext(newBase)
        }
    }
}