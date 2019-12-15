plugins {
    java
    `java-library`
}
group = "Group 09"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    api("commons-httpclient:commons-httpclient:3.1")
    api("commons-lang:commons-lang:2.6")
    api("org.apache.commons:commons-lang3:3.9")
    implementation(files("zap-2.9.0-SNAPSHOT.jar"))
    testImplementation("junit:junit:4.11")
    testImplementation("org.slf4j:slf4j-log4j12:1.7.28")
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}