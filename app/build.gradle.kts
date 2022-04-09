plugins {
    id(Plugins.ANDROID_APPLICATION)
}

dependencies {

    implementation(Dependencies.Android.APPCOMPAT)
    implementation(Dependencies.Android.MATERIAL)
    implementation(Dependencies.Android.CONSTRAINT)
    implementation(project(Dependencies.Modules.DOMAIN))
    implementation(project(Dependencies.Modules.DATA))
}