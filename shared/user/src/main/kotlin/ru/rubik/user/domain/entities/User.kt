package ru.rubik.user.domain.entities

data class User(
	val firstName: String,
	val lastName: String,
	val bornDate: String,
	val password: String,
)