import dev.slne.surf.surfapi.gradle.util.registerRequired

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

repositories {
    mavenCentral()

    maven("https://repo.slne.dev/repository/maven-public/") {
        name = "maven-public"
    }
}

dependencies {
    compileOnly(libs.surf.gui)

    api(project(":surf-trader-api"))
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.trader.example.SurfTraderExample")
    authors.add("SLNE Development")

    generateLibraryLoader(false)
    foliaSupported(true)

    serverDependencies {
        registerRequired("surf-trader-bukkit")
        registerRequired("surf-gui-bukkit")
    }
}