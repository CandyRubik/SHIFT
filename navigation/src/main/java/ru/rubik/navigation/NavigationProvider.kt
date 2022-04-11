package ru.rubik.navigation

interface NavigationProvider {

	fun launch(navCommand: NavCommand)
}