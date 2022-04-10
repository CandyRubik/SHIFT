package rubik.shifttest.di

import dagger.Component
import ru.rubik.greeting.di.GreetingComponent
import ru.rubik.registration.di.RegistrationComponent
import ru.rubik.user.di.SharedUserModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SharedUserModule::class, SubcomponentsModule::class])
interface AppComponent {

	fun registrationComponent(): RegistrationComponent.Factory
	fun greetingComponent(): GreetingComponent.Factory
}