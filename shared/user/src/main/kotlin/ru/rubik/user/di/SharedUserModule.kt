package ru.rubik.user.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.rubik.user.data.UserRepositoryImpl
import ru.rubik.user.data.storage.UserStorage
import ru.rubik.user.data.storage.sharedprefs.SharedPrefUserStorage
import ru.rubik.user.domain.repository.UserRepository
import javax.inject.Singleton

@Module(includes = [UserDataModule::class, UserDomainModule::class])
class SharedUserModule

@Module
class UserDataModule {

	@Provides
	@Singleton
	fun provideUserStorage(context: Context): UserStorage = SharedPrefUserStorage(context = context)
}

@Module
class UserDomainModule {

	@Provides
	@Singleton
	fun provideUserRepository(userStorage: UserStorage): UserRepository = UserRepositoryImpl(userStorage = userStorage)
}