package ru.rubik.greeting.presentation

sealed class GreetingState {
	object Button : GreetingState()
	data class Dialog(val firstName: String) : GreetingState()
}