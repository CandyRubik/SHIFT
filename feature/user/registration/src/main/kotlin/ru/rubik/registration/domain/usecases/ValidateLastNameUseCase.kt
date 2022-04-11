package ru.rubik.registration.domain.usecases

import javax.inject.Inject

class ValidateLastNameUseCase @Inject constructor() {
	companion object {

		val LAST_NAME_REGEX = Regex("[a-zA-z]+([ '-][a-zA-Z]+)*")
	}

	operator fun invoke(lastName: String): Boolean = lastName.matches(LAST_NAME_REGEX)
}