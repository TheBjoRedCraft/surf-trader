import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id ("io.freefair.lombok") version "8.10"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.slne"
version = "1.21.4-1.0.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://jitpack.io")
    }

    maven ("https://repo.codemc.org/repository/maven-public/")

    maven("https://repo.fancyplugins.de/releases")

    maven("https://repo.slne.dev/repository/maven-external-developers/") {
        name = "maven-external-developers"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly ("dev.jorel:commandapi-bukkit-core:9.7.0")
    compileOnly("de.oliver:FancyNpcs:2.4.1")

    /*compileOnly("dev.slne:surf-transaction-api:1.21-1.0.0-SNAPSHOT") {
        exclude("*", "*")
    }*/

    implementation ("com.github.stefvanschie.inventoryframework:IF:0.10.17")
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
}

tasks {
    runServer {
        minecraftVersion("1.21.4")

        downloadPlugins {
            modrinth("CommandAPI", "9.7.0")
            modrinth("FancyNpcs", "2.4.1")
        }
    }

    shadowJar {
        archiveClassifier = ""
    }
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