plugins {
    java
    kotlin("jvm") version "1.3.61"
}

group = "com.smartsoft"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
//    mavenCentral()
//    jcenter()
//    google()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    //for command line blackjack
    implementation("jline:jline:2.14.5")

    //for html blackjack
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.3")

    //for http server blackjack
    implementation("io.javalin:javalin:3.6.0")
    implementation("org.slf4j:slf4j-simple:1.8.0-beta4")
    implementation("org.codehaus.jackson:jackson-mapper-asl:1.9.11")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")

    implementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}