package ru.rubik.user.data.storage

import ru.rubik.user.data.storage.models.UserDTO

interface UserStorage {

	fun saveUser(user: UserDTO)
	fun getUser(): UserDTO
}