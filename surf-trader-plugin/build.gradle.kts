import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id ("io.freefair.lombok") version "8.10"
}

group = "dev.slne"
version = "1.21.4-1.0.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven ("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly ("dev.jorel:commandapi-bukkit-core:9.5.2")

    implementation ("com.github.stefvanschie.inventoryframework:IF:0.10.17")
}

paper {
    name = "SurfTrader"
    main = "dev.slne.surf.trader.SurfTrader"
    apiVersion = "1.21"
    authors = listOf("SLNE Development", "TheBjoRedCraft")
    prefix = "SurfTrader"
    version = "${project.version}"
    foliaSupported = false


    serverDependencies {
        register("CommandAPI") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = true
        }
    }
}