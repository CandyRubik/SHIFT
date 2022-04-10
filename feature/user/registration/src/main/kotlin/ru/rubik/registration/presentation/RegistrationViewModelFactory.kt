package ru.rubik.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.registration.domain.usecases.ValidateDateUseCase
import ru.rubik.registration.domain.usecases.ValidateFirstNameUseCase
import ru.rubik.registration.domain.usecases.ValidateLastNameUseCase
import ru.rubik.registration.domain.usecases.ValidatePasswordsUseCase
import ru.rubik.user.domain.repository.UserRepository
import javax.inject.Inject

class RegistrationViewModelFactory @Inject constructor(
	private val validateFirstNameUseCase: ValidateFirstNameUseCase,
	private val validateLastNameUseCase: ValidateLastNameUseCase,
	private val validateDateUseCase: ValidateDateUseCase,
	private val validatePasswordsUseCase: ValidatePasswordsUseCase,
	private val repository: UserRepository,
) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return RegistrationViewModel(
			validateFirstNameUseCase = validateFirstNameUseCase,
			validateLastNameUseCase = validateLastNameUseCase,
			validateDateUseCase = validateDateUseCase,
			validatePasswordsUseCase = validatePasswordsUseCase,
			repository = repository,
		) as T
	}
}