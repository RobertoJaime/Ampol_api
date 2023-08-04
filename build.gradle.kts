plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.rest-assured:rest-assured:4.4.0")
    implementation ("javax.xml.bind:jaxb-api:2.3.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.5")
    implementation ("com.google.code.gson:gson:2.8.8")
    testImplementation("junit:junit:4.13.1")
}

tasks.test {
    useJUnitPlatform()
}