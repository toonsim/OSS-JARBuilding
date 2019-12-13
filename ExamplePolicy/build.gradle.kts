plugins {
    java
}

group = "Group 09"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testCompile("junit", "junit", "4.12") //compile files("lib/zap-2.8.0.jar")
    implementation(files("zap-2.9.0-SNAPSHOT.jar"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}