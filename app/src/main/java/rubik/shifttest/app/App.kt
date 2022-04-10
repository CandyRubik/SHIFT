package rubik.shifttest.app

import android.app.Application
import ru.rubik.greeting.di.GreetingComponent
import ru.rubik.greeting.di.GreetingComponentProvider
import ru.rubik.registration.di.RegistrationComponent
import ru.rubik.registration.di.RegistrationComponentProvider
import rubik.shifttest.di.AppComponent
import rubik.shifttest.di.AppModule
import rubik.shifttest.di.DaggerAppComponent

class App : Application(), RegistrationComponentProvider, GreetingComponentProvider {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent
			.builder()
			.appModule(AppModule(context = this))
			.build()
	}

	override fun provideRegistrationComponent(): RegistrationComponent {
		return appComponent.registrationComponent().create()
	}

	override fun provideGreetingComponent(): GreetingComponent {
		return appComponent.greetingComponent().create()
	}
}