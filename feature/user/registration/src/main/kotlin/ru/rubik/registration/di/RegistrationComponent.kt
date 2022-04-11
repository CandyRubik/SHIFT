package ru.rubik.registration.di

import dagger.Subcomponent
import ru.rubik.registration.ui.RegistrationFragment

@Subcomponent
interface RegistrationComponent {

	@Subcomponent.Factory
	interface Factory {

		fun create(): RegistrationComponent
	}

	fun inject(registrationFragment: RegistrationFragment)
}