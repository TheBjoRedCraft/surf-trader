buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://repo.slne.dev/repository/maven-public/") { name = "maven-public" }
    }
    dependencies {
        classpath("dev.slne.surf:surf-api-gradle-plugin:1.21.4+")
    }
}

allprojects {
    group = "dev.slne"
    version = "1.0.0-1.21.4-SNAPSHOT"
}