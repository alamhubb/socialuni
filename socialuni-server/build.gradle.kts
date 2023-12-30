plugins {
    kotlin("jvm") version "1.9.21"
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.socialuni.social"
version = "0.3.0"

//子模块配置
subprojects {
    apply<JavaPlugin>()
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        constraints {
            implementation("com.socialuni.social:socialuni-common-api:${rootProject.version}")
            implementation("org.apache.commons:commons-lang3:3.9")
            implementation("io.jsonwebtoken:jjwt:0.9.1")
            implementation("cn.hutool:hutool-all:5.8.8")
        }
    }
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}