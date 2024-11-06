plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "in.tweets"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "in.tweets"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint{
        abortOnError = true // Fails the build on serious lint errors
        checkReleaseBuilds = true // Runs lint checks on release builds

        // Optionally enable or disable specific checks
        disable += listOf("TypographyFractions", "TypographyQuotes") // Disable specific checks
        enable += listOf("NewApi", "InlinedApi") // Enable specific checks

        // Configure report formats
        textOutput = file("stdout") // Print lint results to the console
        xmlReport = true // Enable XML report
        htmlReport = true // Enable HTML report

        // Specify report locations using layout.buildDirectory
        htmlOutput = layout.buildDirectory.file("reports/lint/lint-report.html").get().asFile
        xmlOutput = layout.buildDirectory.file("reports/lint/lint-report.xml").get().asFile

        checkDependencies = true
    }
}

dependencies {

    //core
    implementation(libs.androidx.core.ktx)

    //compose lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation (libs.androidx.material)

    //Navigation
    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.hilt.navigation.compose)

    //HILT
    implementation (libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.compose)


    // Hilt for Android Testing
    androidTestImplementation (libs.google.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    //splashScreen
    implementation (libs.androidx.core.splashscreen)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}