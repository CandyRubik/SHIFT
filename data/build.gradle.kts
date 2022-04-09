plugins {
    id(Plugins.ANDROID_LIBRARY)
}

dependencies {
    implementation(project(Dependencies.Modules.DOMAIN))
    implementation(Dependencies.Network.GSON)
}