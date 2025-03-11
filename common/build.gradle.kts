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
}
