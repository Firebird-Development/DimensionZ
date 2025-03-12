import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
	idea
	id("com.gradleup.shadow") version "9.0.0-beta10" apply false
	id("io.github.pacifistmc.forgix") version "1.2.9"
}

allprojects {
	apply(plugin = "java")

	base {
		archivesName = properties["mod_id"] as String
	}
	group = properties["maven_group"] as String
	version = "mc${properties["minecraft_version"]}-${properties["mod_version"]}"
}

forgix {
	group = "${properties["maven_group"]}.${properties["mod_id"]}"
	mergedJarName = "${properties["mod_id"]}-${version}.jar"
	outputDir = "build/libs/merged"

	fabricContainer = FabricContainer().apply {
		jarLocation = "build/libs/${properties["mod_id"]}-fabric-${version}.jar"
	}

	forgeContainer = ForgeContainer().apply {
		jarLocation = "build/libs/${properties["mod_id"]}-forge-${version}.jar"
	}

	neoForgeContainer = NeoForgeContainer().apply {
		jarLocation = "build/libs/${properties["mod_id"]}-neoforge-${version}.jar"
	}

	removeDuplicate("dev.firebird.dimensionz")
}

subprojects {
	apply(plugin = "com.gradleup.shadow")

	base { archivesName = "${properties["mod_id"]}-${project.name}" }

	@Suppress("UnstableApiUsage")
	configurations {
		create("common") {
			isCanBeResolved = true
			isCanBeConsumed = false
		}
		compileClasspath.get().extendsFrom(configurations["common"])
		runtimeClasspath.get().extendsFrom(configurations["common"])

		// Files in this configuration will be bundled into your mod using the Shadow plugin.
		// Don't use the `shadow` configuration from the plugin itself as it's meant for excluding files.
		create("shadowBundle") {
			isCanBeResolved = true
			isCanBeConsumed = false
		}
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		compileOnly("org.jetbrains:annotations:24.1.0")
	}

	tasks.withType<ShadowJar> {
		configurations = listOf(project.configurations.getByName("shadowBundle"))
//		archiveClassifier.set("dev-shadow")
		archiveClassifier.set("")
	}

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
		options.release.set(JavaLanguageVersion.of(properties["java_version"] as String).asInt())
	}

	tasks.processResources {
		val props = mutableMapOf(
			"java_version" to properties["java_version"],

			"maven_group" to properties["maven_group"],
			"mod_id" to properties["mod_id"],
			"mod_version" to properties["mod_version"],

			"mod_name" to properties["mod_name"],
			"mod_description" to properties["mod_description"],
			"mod_author" to properties["mod_author"],
			"mod_license" to properties["mod_license"],

			"fabric_version_range" to properties["fabric_version_range"],
			"forge_version_range" to properties["forge_version_range"],
			"neoforge_version_range" to properties["neoforge_version_range"],
		)

		inputs.properties(props)
		filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/mods.toml", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
			expand(props)
		}
	}

	tasks.jar {
		manifest {
			attributes(mapOf(
				"Specification-Title" to properties["mod_name"],
				"Specification-Vendor" to properties["mod_author"],
				"Specification-Version" to properties["mod_version"],
				"Implementation-Title" to name,
				"Implementation-Vendor" to properties["mod_author"],
				"Implementation-Version" to archiveVersion
			))
		}
	}

	java {
		withSourcesJar()
	}

	tasks.build.get().finalizedBy(rootProject.tasks.getByName("mergeJars"))
	tasks.assemble.get().finalizedBy(rootProject.tasks.getByName("mergeJars"))
}