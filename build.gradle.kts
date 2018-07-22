plugins {
  maven
  `java-library`
  kotlin("jvm") version "1.2.51"
}
tasks.getByName<Wrapper>("wrapper") {
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
  implementation(kotlin("stdlib"))

  testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.8")
}
