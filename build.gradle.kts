plugins {
  maven
  `java-library`
  kotlin("jvm") version "1.2.60"
}
tasks.withType<Wrapper> {
  gradleVersion = "4.9"
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_7
  targetCompatibility = JavaVersion.VERSION_1_7
}
tasks.withType<Test> {
  useJUnitPlatform()
}

repositories {
  jcenter()
}
dependencies {
  implementation(create(kotlin("stdlib"), closureOf<ExternalModuleDependency> {
    exclude("org.jetbrains", "annotations")
  }))

  testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.8")
}
