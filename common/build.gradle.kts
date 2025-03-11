plugins {
	id("fabric-loom") version "1.10-SNAPSHOT"
}

dependencies {
	minecraft("net.minecraft:minecraft:${properties["minecraft_version"]}")
	mappings(loom.officialMojangMappings())
}
