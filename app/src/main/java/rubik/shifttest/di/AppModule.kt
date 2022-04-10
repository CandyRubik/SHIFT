package rubik.shifttest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.rubik.greeting.di.GreetingComponent
import ru.rubik.registration.di.RegistrationComponent

@Module
class AppModule(private val context: Context) {

	@Provides
	fun providesContext(): Context = context
}

@Module(subcomponents = [RegistrationComponent::class, GreetingComponent::class])
class SubcomponentsModule