
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.currencyapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.currencyapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\" \"")
        buildConfigField("String", "API_KEY", "\"c52ddf77af3b4b5db036a8f7b8417b52\"")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://data.fixer.io/api/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://data.fixer.io/api/\"")
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
        dataBinding= true
        buildConfig = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    // Networking
    val retrofit_version = "2.9.0"
    val okhttp_version = "3.14.9"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttp_version")
    implementation ("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")

    // Logging
    val timber_version = "4.7.1"
    implementation ("com.jakewharton.timber:timber:$timber_version")

    //rxjava2
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1") // Use the latest version
    //viewmodel
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}