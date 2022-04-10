package ru.rubik.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.registration.domain.usecases.ValidateDateUseCase
import ru.rubik.registration.domain.usecases.ValidateFirstNameUseCase
import ru.rubik.registration.domain.usecases.ValidateLastNameUseCase
import ru.rubik.registration.domain.usecases.ValidatePasswordsUseCase
import ru.rubik.user.domain.repository.UserRepository

class RegistrationViewModel(
	private val validateFirstNameUseCase: ValidateFirstNameUseCase,
	private val validateLastNameUseCase: ValidateLastNameUseCase,
	private val validateDateUseCase: ValidateDateUseCase,
	private val validatePasswordsUseCase: ValidatePasswordsUseCase,
	private val repository: UserRepository,
) : ViewModel() {

	private val _isNameCorrect = MutableStateFlow(true)
	val isNameCorrect: StateFlow<Boolean> = _isNameCorrect

	private val _isLastNameCorrect = MutableStateFlow(true)
	val isLastNameCorrect: StateFlow<Boolean> = _isLastNameCorrect

	private val _isDateCorrect = MutableStateFlow(true)
	val isDateCorrect: StateFlow<Boolean> = _isDateCorrect

	private val _isPasswordsEquals = MutableStateFlow(true)
	val isPasswordsEquals: StateFlow<Boolean> = _isPasswordsEquals

	private val _isButtonAvailable = MutableStateFlow(
		_isNameCorrect.value
			&& _isDateCorrect.value
			&& _isLastNameCorrect.value
			&& _isPasswordsEquals.value
	)
	val isButtonAvailable: StateFlow<Boolean> = _isButtonAvailable

	fun validateFirstName(name: String) =
		viewModelScope.launch {

			_isNameCorrect.value = validateFirstNameUseCase(name)
		}

	fun validateLastName(lastName: String) =
		viewModelScope.launch {

			_isDateCorrect.value = validateLastNameUseCase(lastName)
		}

	fun validateDate(date: String) =
		viewModelScope.launch {

			_isDateCorrect.value = validateDateUseCase(date)
		}

	fun validateEqualsPasswords(first: String, second: String) =
		viewModelScope.launch {
			_isPasswordsEquals.value = validatePasswordsUseCase(first, second)
		}

	fun saveUser(firstname: String, lastName: String, bornDate: String, password: String) =
		viewModelScope.launch {
			repository.saveUser(
				ru.rubik.user.domain.entities.User(
					firstName = firstname,
					lastName = lastName,
					bornDate = bornDate,
					password = password,
				)
			)
		}
}