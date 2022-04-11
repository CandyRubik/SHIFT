object Dependencies {
	object Versions {

		const val KOTLIN =
			"1.6.20"//https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-gradle-plugin
		const val GRADLE =
			"7.1.2"//https://mvnrepository.com/artifact/com.android.tools.build/gradle?repo=google
		const val APPCOMPAT = "1.4.1"
		const val MATERIAL = "1.5.0"
		const val CONSTRAINT = "2.1.3"
		const val GSON = "2.8.8"
		const val CORE = "1.7.0"//https://mvnrepository.com/artifact/androidx.core/core-ktx
		const val COROUTINE =
			"1.6.0"//https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
		const val LIFECYCLE = "2.4.1"
		const val DAGGER = "2.40.1"
		const val NAVIGATION = "2.4.0"
	}

	const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"

	object Kotlin {

		const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
		const val KOTLIN_STANDARD_LIBRARY =
			"org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
	}

	const val CORE = "androidx.core:core-ktx:${Versions.CORE}"

	object Android {

		const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
		const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
		const val CONSTRAINT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
		const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
		const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
	}

	object Network {

		const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
	}

	object Coroutine {

		const val COROUTINE_CORE =
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
		const val COROUTINE_ANDROID =
			"org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"
	}

	object Modules {

		const val FEATURE_REGISTRATION = ":feature:user:registration"
		const val FEATURE_GREETING = ":feature:user:greeting"
		const val SHARED_USER = ":shared:user"
		const val NAVIGATION = ":navigation"
	}

	object Dagger {

		const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
		const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
	}

	object Navigation {

		const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
		const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
	}
}