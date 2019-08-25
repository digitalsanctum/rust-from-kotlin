import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
  }
}

plugins {
  id("io.spring.dependency-management") version "1.0.8.RELEASE"
  id("org.jetbrains.kotlin.jvm") version "1.3.50"
  id("java-library")
  id("maven-publish")
}

group = "io.futz"
version = "1.0.0"

repositories {
  mavenCentral()
}

allprojects {
  apply {
    plugin("kotlin")
  }
}

subprojects {

  apply {
    plugin("kotlin")
    plugin("java-library")
    plugin("io.spring.dependency-management")
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }

  repositories {
    mavenCentral()
  }

  dependencyManagement {

    imports {
      mavenBom("org.slf4j:slf4j-parent:1.7.28")
    }

    dependencies {
      dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.50")
      dependency("org.junit.jupiter:junit-jupiter-api:5.5.0")
      dependency("org.junit.jupiter:junit-jupiter-params:5.5.0")
      dependency("org.junit.jupiter:junit-jupiter-engine:5.5.0")
      dependency("org.slf4j:slf4j-api:1.7.28")
      dependency("org.slf4j:slf4j-log4j12:1.7.28")
      dependency("com.github.jnr:jnr-ffi:2.1.10")
    }
  }

  dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.slf4j:slf4j-api")
    testImplementation("org.slf4j:slf4j-log4j12")
  }


  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}


