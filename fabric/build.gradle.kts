plugins {
	id("fabric-loom") version "1.10-SNAPSHOT"
}

repositories {
	maven {
		name = "ParchmentMC"
		url = uri("https://maven.parchmentmc.org")
	}
}

@Suppress("UnstableApiUsage")
dependencies {
	minecraft("net.minecraft:minecraft:${properties["minecraft_version"]}")
//	mappings(loom.officialMojangMappings())
	mappings(loom.layered {
		officialMojangMappings()
		parchment("org.parchmentmc.data:parchment-${properties["parchment_minecraft_version"]}:${properties["parchment_mappings_version"]}@zip")
	})
	modImplementation("net.fabricmc:fabric-loader:${properties["fabric_version"]}")

	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_api_version"]}")

	common(project(":common", "namedElements")) { isTransitive = false }
	shadowBundle(project(":common"))
}

loom {
	runs {
		named("client") {
			client()
			configName = "Client"
			ideConfigGenerated(true)
			runDir("../.runs/client")
			source(sourceSets["main"])
			programArgs("--username=Dev")
		}
		named("server") {
			server()
			configName = "Server"
			ideConfigGenerated(true)
			runDir("../.runs/server")
			source(sourceSets["main"])
		}
	}
}