plugins {
	id(Plugins.ANDROID_LIBRARY)
	id(Plugins.KOTLIN_ANDROID)
	id(Plugins.KOTLIN_ANNOTATION)

}

dependencies {
	implementation(Dependencies.CORE)
	implementation(Dependencies.Dagger.DAGGER)
	kapt(Dependencies.Dagger.DAGGER_COMPILER)
	implementation(Dependencies.Navigation.NAVIGATION_FRAGMENT)
	implementation(Dependencies.Navigation.NAVIGATION_UI)
	implementation(Dependencies.Coroutine.COROUTINE_CORE)
	implementation(Dependencies.Android.APPCOMPAT)
	implementation(Dependencies.Android.MATERIAL)
	implementation(Dependencies.Android.LIFECYCLE_VIEWMODEL)
	implementation(Dependencies.Android.LIFECYCLE)
	implementation(project(Dependencies.Modules.SHARED_USER))
}