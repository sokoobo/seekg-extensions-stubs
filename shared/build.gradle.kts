plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                api(libs.coroutines)
                api(libs.injekt.core)
                api(libs.kotlin.json)
                api(libs.kotlin.json.okio)
                api(libs.okhttp)
                api(libs.rxandroid)
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.jsoup)
                implementation(libs.rxjava)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

version = "0.1.0"
group = "com.github.sokoobo"

android {
    namespace = "com.sokoobo.seekg.extensions.stubs"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(JavaVersion.VERSION_11.toString().toInt())
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "seekg-extensions-stubs"
            afterEvaluate {
                from(components.getByName("release"))
            }
        }
    }

    repositories {
        maven {
            url = uri("https://jitpack.io")
            credentials {
                username = findProperty("userName") as String
                password = findProperty("authToken") as String
            }
        }
    }
}