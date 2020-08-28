import org.jetbrains.registerDokkaArtifactPublication

plugins {
    id("com.jfrog.bintray")
}

dependencies {
    val coroutines_version: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

    api(project(":plugins:kotlin-analysis"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.1")
    testImplementation(project(":plugins:base:base-test-utils"))
    testImplementation(project(":core:content-matcher-test-utils"))

    val kotlinx_html_version: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_html_version")
}

task("copy_frontend", Copy::class) {
    from(File(project(":plugins:base:frontend").projectDir, "dist/"))
    destinationDir = File(sourceSets.main.get().resources.sourceDirectories.singleFile, "dokka/scripts")
}.dependsOn(":plugins:base:frontend:generateFrontendFiles")

tasks {
    processResources {
        dependsOn("copy_frontend")
    }

    test {
        maxHeapSize = "4G"
    }
}

registerDokkaArtifactPublication("dokkaBase") {
    artifactId = "dokka-base"
}
