package ru.rubik.greeting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.user.domain.repository.UserRepository

class GreetingViewModel(
	private val repository: UserRepository,
) : ViewModel() {

	private val _uiState = MutableStateFlow<GreetingState>(GreetingState.Button)
	val uiState: StateFlow<GreetingState> = _uiState

	fun getUserInfo() {
		viewModelScope.launch {
			_uiState.value = GreetingState.Dialog(repository.getUser().firstName)
		}
	}
}