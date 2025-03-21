plugins {
    id("dev.slne.surf.surfapi.gradle.core")
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    api(project(":surf-trader-api"))

    compileOnly(libs.surf.transaction)
    compileOnly(libs.surf.gui)

    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}