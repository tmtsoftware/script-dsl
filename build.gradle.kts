import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.50"
    `maven-publish`
}

group = "com.github.tmtsoftware.esw"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.tmtsoftware.esw:esw-ocs-impl_2.13:0.1-SNAPSHOT")
    implementation("com.github.tmtsoftware.csw:csw-params_2.13:0.1-SNAPSHOT")
    compile("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", "1.3.0")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

task<Jar>("sourcesJar") {
    from(project.the<SourceSetContainer>()["main"].java)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.tmtsoftware.esw"
            artifactId = "script-dsl"
            version = "1.0"

            from(components["java"])
        }
    }
}