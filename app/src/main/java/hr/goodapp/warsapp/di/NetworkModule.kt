package hr.goodapp.warsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import hr.goodapp.warsapp.BuildConfig
import hr.goodapp.warsapp.data.people.PeopleDataSource
import hr.goodapp.warsapp.ui.common.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LengthValidator

    @Provides
    fun provideOtherInterceptorOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun providePeopleDataSource(okHttpClient: OkHttpClient ): PeopleDataSource {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PeopleDataSource::class.java)
    }
    /*@Provides
    fun provideInputValidators(): List<ICheckInput> {
        return listOf(
            InputLengthValidator(),
            RepeatOfCharacterValidator(),
            IsDigitValidator(), CheckIsExistValidator(), OneAfterOtherValidator()
        )
    }*/

}