plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.socialuni.social"
version = "0.3.0"

//子模块配置
subprojects {
    apply<JavaPlugin>()

    dependencies{
        constraints {
            add("implementation", "com.socialuni.social:socialuni-common-api:${rootProject.version}")
        }
    }
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}