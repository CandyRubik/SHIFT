plugins {
	id(Plugins.ANDROID_LIBRARY)
	id(Plugins.KOTLIN_ANDROID)
	id(Plugins.KOTLIN_ANNOTATION)
}

dependencies {
	implementation(Dependencies.CORE)
	implementation(Dependencies.Network.GSON)
	implementation(Dependencies.Dagger.DAGGER)
	kapt(Dependencies.Dagger.DAGGER_COMPILER)
}
