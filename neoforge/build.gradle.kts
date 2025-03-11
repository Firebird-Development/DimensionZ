plugins {
    id("net.neoforged.moddev") version("2.0.78")
}

neoForge {
    version = properties["neoforge_version"] as String

    parchment {
        mappingsVersion = properties["parchment_mappings_version"] as String
        minecraftVersion = properties["parchment_minecraft_version"] as String
    }

    runs {
        create("client") {
            client()
			gameDirectory = file("../.runs/client")
			programArguments = listOf("--username", "dev")
        }

        create("server") {
            server()
			gameDirectory = file("../.runs/server")
			programArgument("--nogui")
        }
    }
}

dependencies {
	common(project(":common", "namedElements")) { isTransitive = false }
	shadowBundle(project(":common"))
}