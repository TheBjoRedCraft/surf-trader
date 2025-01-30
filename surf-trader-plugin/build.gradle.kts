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

    maven("https://repo.slne.dev/repository/maven-external-developers/") {
        name = "maven-external-developers"
    }

    maven {
        name = "pyrSnapshots"
        url = uri("https://repo.pyr.lol/snapshots")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly ("dev.jorel:commandapi-bukkit-core:9.7.0")

    /*compileOnly("dev.slne:surf-transaction-api:1.21-1.0.0-SNAPSHOT") {
        exclude("*", "*")
    }*/

    compileOnly("lol.pyr:znpcsplus-api:2.0.0-SNAPSHOT")

    implementation ("com.github.stefvanschie.inventoryframework:IF:0.10.19")
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
    implementation("com.github.Heliosares:AuxProtect:1.3.1") {
        exclude("*", "*")
    }
}

tasks {
    runServer {
        minecraftVersion("1.21.4")

        downloadPlugins {
            modrinth("CommandAPI", "9.7.0")
            url("https://ci.citizensnpcs.co/job/Citizens2/lastBuild/artifact/dist/target/Citizens-2.0.37-b3693.jar")
        }
    }

    shadowJar {
        archiveClassifier = ""

        relocate("com.github.Heliosares", "dev.slne.surf.trader.lib")
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
        register("AuxProtect") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = true
        }

        register("CommandAPI") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = true
        }

        register("ZNPCsPlus") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = true
        }
    }
}