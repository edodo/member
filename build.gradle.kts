
plugins {
    id("java")
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val sourceCompatibility = 11
val targetCompatibility = 11
val querydslVersion = "4.1.4"
val querydslSourcesDir  = "gradle/querydsl.gradle"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDirs (")src/main/java", "src/main/generated")
        }
    }
}

dependencies {
    implementation("org.testng:testng:7.1.0")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.slf4j:jcl-over-slf4j")
    implementation("com.querydsl:querydsl-jpa")
    implementation("com.querydsl:querydsl-apt")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.webjars:webjars-locator-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    annotationProcessor("org.projectlombok:lombok:1.18.22")
    runtimeOnly("com.h2database:h2")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}