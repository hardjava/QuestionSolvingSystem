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
    implementation("com.mysql:mysql-connector-j:8.0.33")
    implementation("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    implementation("org.apache.commons", "commons-dbcp2", "2.8.0")
    implementation("org.mybatis:mybatis:3.5.6")

}

tasks.test {
    useJUnitPlatform()
}