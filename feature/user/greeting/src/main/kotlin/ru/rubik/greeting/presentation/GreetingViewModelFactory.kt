package ru.rubik.greeting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.user.domain.repository.UserRepository
import javax.inject.Inject

class GreetingViewModelFactory @Inject constructor(
	private val repository: UserRepository
) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return GreetingViewModel(repository = repository) as T
	}
}