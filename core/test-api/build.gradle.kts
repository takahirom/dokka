import org.jetbrains.registerDokkaArtifactPublication

plugins {
    `maven-publish`
    id("com.jfrog.bintray")
}

dependencies {
    api(project(":core"))
    implementation(project(":plugins:kotlin-analysis"))
    implementation("junit:junit:4.13") // TODO: remove dependency to junit
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
}

registerDokkaArtifactPublication("dokkaTestApi") {
    artifactId = "dokka-test-api"
}
