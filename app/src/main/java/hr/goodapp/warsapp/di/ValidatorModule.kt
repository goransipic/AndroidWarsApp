package hr.goodapp.warsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import hr.goodapp.warsapp.ui.common.*
import javax.inject.Qualifier

@Module
@InstallIn(ApplicationComponent::class)
object ValidatorsModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LengthValidator


    /*@Provides
    fun provideInputValidators(): List<ICheckInput> {
        return listOf(
            InputLengthValidator(),
            RepeatOfCharacterValidator(),
            IsDigitValidator(), CheckIsExistValidator(), OneAfterOtherValidator()
        )
    }*/

}