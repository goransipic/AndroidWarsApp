package hr.goodapp.warsapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.goodapp.warsapp.R
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

const val ENGLISH_LANGUAGE = "en"
const val CROATIAN_LANGUAGE = "hr"

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
@Singleton
class SharedPreferenceStorage @Inject constructor(@ApplicationContext context: Context) : PreferenceStorage {

    private val prefs: Lazy<SharedPreferences> = lazy { // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        ).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            PREF_LANGUAGE -> Toast.makeText(context,context.getString(R.string.language_updated), Toast.LENGTH_SHORT).show()
        }
    }

    override var currentLanguage by StringPreference(prefs, PREF_LANGUAGE, context.getString(R.string.language_placeholder))

    companion object {
        const val PREFS_NAME = "warsapp"
        const val PREF_LANGUAGE = "pref_language"
    }

    fun registerOnPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.value.registerOnSharedPreferenceChangeListener(listener)
    }
}

class BooleanPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }
}

class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String
) : ReadWriteProperty<Any, String> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.value.getString(name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preferences.value.edit(true) { putString(name, value) }
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun bindPreferenceStorage(
        sharedPreferenceStorage: SharedPreferenceStorage
    ): PreferenceStorage
}