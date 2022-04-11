package ru.rubik.greeting.di

interface GreetingComponentProvider {

	fun provideGreetingComponent(): GreetingComponent
}