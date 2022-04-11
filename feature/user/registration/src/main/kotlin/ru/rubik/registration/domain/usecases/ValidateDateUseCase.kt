package ru.rubik.registration.domain.usecases

import javax.inject.Inject

class ValidateDateUseCase @Inject constructor() {

	operator fun invoke(date: String): Boolean = date.isNotEmpty()
}