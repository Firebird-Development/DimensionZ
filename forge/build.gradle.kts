plugins {
	id("net.minecraftforge.gradle") version("6.0.26")
	id("org.spongepowered.mixin") version("0.7.+")
}

minecraft {
	mappings("official", properties["minecraft_version"] as String)
	reobf = false
	copyIdeResources.set(true)

	runs {
		create("client") {
			taskName("Client")
			workingDirectory(file("../.runs/client"))
			ideaModule("${rootProject.name}.${project.name}.main")
			args("--username", "Dev")
		}

		create("server") {
			taskName("Server")
			workingDirectory(file("../.runs/server"))
			ideaModule("${rootProject.name}.${project.name}.main")
			args("--nogui")
		}
	}
}

repositories {
	maven("https://maven.minecraftforge.net/")
}

dependencies {
    minecraft("net.minecraftforge:forge:${properties["minecraft_version"]}-${properties["forge_version"]}")

	// Mixin for forge
	annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
	// Mixin Extras for forge
	annotationProcessor("io.github.llamalad7:mixinextras-common:0.4.1")
	compileOnly("io.github.llamalad7:mixinextras-common:0.4.1")
	implementation(jarJar("io.github.llamalad7:mixinextras-forge:0.4.1")) {
		jarJar.ranged(this, "[0.4.1,)")
	}

	common(project(":common", "namedElements")) { isTransitive = false }
	shadowBundle(project(":common"))
}

mixin {
	add(sourceSets.main.get(), "mixins.${project.name}.refmap.json")
	config("${project.name}.mixins.json")

	add(sourceSets.main.get(), "mixins.${project.name}-common.refmap.json")
	config("${project.name}-common.mixins.json")
}

sourceSets.forEach {
	val outputDir = layout.buildDirectory.file("sourcesSets/${it.name}").get().asFile
	it.output.setResourcesDir(outputDir)
	it.java.destinationDirectory.set(outputDir)
}
