package ru.rubik.greeting.di

import dagger.Subcomponent
import ru.rubik.greeting.ui.GreetingFragment

@Subcomponent
interface GreetingComponent {

	@Subcomponent.Factory
	interface Factory {

		fun create(): GreetingComponent
	}

	fun inject(registrationFragment: GreetingFragment)
}