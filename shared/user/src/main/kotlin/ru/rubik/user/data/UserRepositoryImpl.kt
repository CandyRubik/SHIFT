package ru.rubik.user.data

import ru.rubik.user.data.storage.UserStorage
import ru.rubik.user.data.storage.models.UserDTO
import ru.rubik.user.domain.entities.User
import ru.rubik.user.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

	override fun saveUser(user: User) = userStorage.saveUser(mapToData(user))

	override fun getUser(): User = mapToDomain(userStorage.getUser())

	private fun mapToDomain(user: UserDTO): User =
		User(
			firstName = user.firstName,
			lastName = user.lastName,
			bornDate = user.bornDate,
			password = user.password,
		)

	private fun mapToData(user: User): UserDTO =
		UserDTO(
			firstName = user.firstName,
			lastName = user.lastName,
			bornDate = user.bornDate,
			password = user.password,
		)
}