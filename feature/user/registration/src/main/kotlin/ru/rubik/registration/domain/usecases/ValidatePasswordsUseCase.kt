package ru.rubik.registration.domain.usecases

import javax.inject.Inject

class ValidatePasswordsUseCase @Inject constructor() {

	operator fun invoke(first: String, second: String): Boolean = first == second
}