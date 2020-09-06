package hr.goodapp.warsapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hr.goodapp.warsapp.data.prefs.IPreferenceStorage
import hr.goodapp.warsapp.data.prefs.SharedPreferenceStorage
import hr.goodapp.warsapp.data.randomnumbers.IRandomNumbersDataSource
import hr.goodapp.warsapp.data.randomnumbers.LocalRandomNumbersDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindPreferenceStorage(
        sharedPreferenceStorage: SharedPreferenceStorage
    ): IPreferenceStorage

    @Binds
    abstract fun bindRandomDataSource(
        localRandomNumbersDataSource: LocalRandomNumbersDataSource): IRandomNumbersDataSource
}