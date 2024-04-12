import dev.adamko.dokkatoo.dokka.parameters.KotlinPlatform
import dev.adamko.dokkatoo.dokka.parameters.VisibilityModifier

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dokkatoo)
    `maven-publish`
}

var compileSdkInt = (findProperty("android.compileSdk") as String).toInt()
var minSdkInt = (findProperty("android.minSdk") as String).toInt()
var namespaceString = findProperty("namespace") as String
var artifactIdString = findProperty("artifactId") as String
var userName = findProperty("userName") as String
var password = findProperty("authToken") as String
var versionString = findProperty("version") as String
var groupString = findProperty("group") as String


version = versionString
group = groupString

android {
    namespace = namespaceString
    compileSdk = compileSdkInt

    defaultConfig {
        minSdk = minSdkInt
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    val javaVersion = JavaVersion.VERSION_11
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    compileOnly(libs.okhttp)
    compileOnly(libs.okhttp.dnsoverhttps)
    compileOnly(libs.okhttp.brotli)
    compileOnly(libs.okhttp.logging)
    compileOnly(libs.jsandroid)
    compileOnly(libs.jsoup)
    compileOnly(libs.rxjava)
    compileOnly(libs.rxandroid)
    compileOnly(libs.injekt.core)
    compileOnly(libs.coroutines)
    compileOnly(libs.kotlin.json)
    compileOnly(libs.kotlin.json.okio)
}

tasks.register("androidSourcesJar", Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

dokkatoo {
    moduleName.set(artifactIdString.replace("-", "."))
    moduleVersion.set(versionString)
    dokkatooPublicationDirectory.set(layout.buildDirectory.dir("docs"))
    dokkatooSourceSets.main {
        includes.from("Module.md")

        analysisPlatform.set(KotlinPlatform.JVM)

        perPackageOption {
            matchingRegex.set("android.content")
            suppress.set(true)
        }

        documentedVisibilities(VisibilityModifier.PUBLIC, VisibilityModifier.PROTECTED)

        externalDocumentationLinks {
            create("okhttp5") {
                url("https://square.github.io/okhttp/5.x/")
            }

            create("jsoup") {
                url("https://jsoup.org/apidocs/")
                packageListUrl("https://jsoup.org/apidocs/element-list")
            }

            create("rxjava") {
                url("https://reactivex.io/RxJava/1.x/javadoc/")
            }
        }

        val packageRoot = projectDir.resolve("src/main/java/com/sokoobo/seekg/")
        sourceLink {
            localDirectory.set(packageRoot.resolve("util/JsonExtensions.kt"))
            remoteUrl("https://github.com/sokoobo/seekg-extensions-stubs/tree/main/library/src/main/java/com/sokoobo/seekg/util/JsonExtensions.kt")
            remoteLineSuffix.set("#L")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("util/CoroutinesExtensions.kt"))
            remoteUrl("https://github.com/sokoobo/seekg-extensions-stubs/tree/main/library/src/main/java/com/sokoobo/seekg/util/CoroutinesExtensions.kt")
            remoteLineSuffix.set("#L")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("source/"))
            remoteUrl("https://github.com/sokoobo/seekg/tree/master/source-api/src/commonMain/kotlin/com/sokoobo/seekg/source/")
            // The line number is wrong, so we're not going to highlight it.
            remoteLineSuffix.set("#")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("network/"))
            remoteUrl("https://github.com/sokoobo/seekg/tree/master/core/src/main/kotlin/com/sokoobo/seekg/network/")
            remoteLineSuffix.set("#") // Same as before.
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = artifactIdString
            afterEvaluate {
                from(components["release"])
            }
        }
    }

    repositories {
        maven {
            url = uri("https://jitpack.io")
            credentials {
                username = userName
                password = password
            }
        }
    }
}

