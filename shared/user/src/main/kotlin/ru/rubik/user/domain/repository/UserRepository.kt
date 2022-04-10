package ru.rubik.user.domain.repository

import ru.rubik.user.domain.entities.User

interface UserRepository {

	fun saveUser(user: User)
	fun getUser(): User
}