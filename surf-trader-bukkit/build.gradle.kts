import dev.slne.surf.surfapi.gradle.util.registerRequired

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

repositories {
    mavenCentral()

    maven("https://repo.slne.dev/repository/maven-public/") {
        name = "maven-public"
    }

    maven("https://repo.pyr.lol/snapshots") {
        name = "pyrSnapshots"
    }
}

dependencies {
    compileOnly(libs.surf.transaction)
    compileOnly(libs.znpcs.plus)

    api(project(":surf-trader-core"))
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.trader.bukkit.SurfTraderBukkit")
    authors.add("SLNE Development")

    generateLibraryLoader(false)
    foliaSupported(true)

    serverDependencies {
        registerRequired("surf-transaction-bukkit")
        registerRequired("ZNPCsPlus")
    }
}