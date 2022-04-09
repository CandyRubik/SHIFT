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
    }

    const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"

    object Kotlin {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    }

    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
    }

    object Network {
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    }

    object Modules {
        const val DOMAIN = ":domain"
        const val DATA = ":data"
    }
}