plugins {
    id(Plugins.ANDROID_APPLICATION)
	id(Plugins.KOTLIN_ANDROID)
	id(Plugins.KOTLIN_ANNOTATION)
}

dependencies {

	implementation(Dependencies.Android.APPCOMPAT)
	implementation(Dependencies.Dagger.DAGGER)
	kapt(Dependencies.Dagger.DAGGER_COMPILER)
	implementation(Dependencies.Navigation.NAVIGATION_FRAGMENT)
	implementation(Dependencies.Navigation.NAVIGATION_UI)
	implementation(Dependencies.Android.MATERIAL)
	implementation(Dependencies.Android.CONSTRAINT)
	implementation(project(Dependencies.Modules.FEATURE_GREETING))
	implementation(project(Dependencies.Modules.FEATURE_REGISTRATION))
	implementation(project(Dependencies.Modules.SHARED_USER))
	implementation(project(Dependencies.Modules.NAVIGATION))
}