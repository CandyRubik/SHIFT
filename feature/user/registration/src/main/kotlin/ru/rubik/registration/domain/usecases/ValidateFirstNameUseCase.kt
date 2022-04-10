package ru.rubik.registration.domain.usecases

import javax.inject.Inject

class ValidateFirstNameUseCase @Inject constructor() {
	companion object {

		val FIRST_NAME_REGEX = Regex("[A-Z][a-z]*")
	}

	operator fun invoke(name: String): Boolean = name.matches(FIRST_NAME_REGEX)
}