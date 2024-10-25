plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    id("io.github.tabilzad.ktor-docs-plugin-gradle") version "0.6.4-alpha"
    application
}

group = "com.doctest"
version = "1.0.0"
application {
    mainClass.set("com.doctest.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation("io.ktor:ktor-server-swagger")

//    testImplementation(libs.ktor.server.tests)
//    testImplementation(libs.kotlin.test.junit)
}

swagger {
    documentation {
        docsTitle = "Doc Test API"
        docsDescription = "CRUD operations for Doc Test API"
        docsVersion = "1.0"
        generateRequestSchemas = true
        hideTransientFields = true
        hidePrivateAndInternalFields = true
        deriveFieldRequirementFromTypeNullability = true
    }

    pluginOptions {
        format = "yaml"
    }
}

//tasks.register("findTaskCreatingSpecificOutput") {
//    doLast {
//        subprojects { subproject ->
//            subproject.tasks.findAll { task ->
//                task.outputs.getFiles().getFiles().findAll { output ->
//                    output == 'thisIsTheOne'
//                }
//            }.flatten().each { println it }
//        }
//    }
//}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    doLast {
        val outputFiles = outputs.files.files
        println("Output files: $outputFiles")
    }
}