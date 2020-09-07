package hr.goodapp.warsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hr.goodapp.warsapp.BuildConfig
import hr.goodapp.warsapp.data.people.PeopleDataSource
import hr.goodapp.warsapp.data.people.remote.PeopleDao
import hr.goodapp.warsapp.data.people.remote.PeopleRemoteDataSource
import hr.goodapp.warsapp.data.peopledetail.MovieDetailDataSource
import hr.goodapp.warsapp.data.peopledetail.remote.MovieDetailDao
import hr.goodapp.warsapp.data.peopledetail.remote.MovieDetailRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LengthValidator

    @Provides
    @Singleton
    fun provideOtherInterceptorOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePeopleDataSource(okHttpClient: OkHttpClient ): PeopleDao {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PeopleDao::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleDetailDataSource(okHttpClient: OkHttpClient ): MovieDetailDao {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDetailDao::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleRemoteDataSource(peopleDao: PeopleDao): PeopleDataSource {
        return PeopleRemoteDataSource(peopleDao)
    }

    @Provides
    @Singleton
    fun providePeopleDetailRemoteDataSource(movieDetailDao: MovieDetailDao): MovieDetailDataSource {
        return MovieDetailRemoteDataSource(movieDetailDao)
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