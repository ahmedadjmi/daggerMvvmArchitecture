plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.daggerproject"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.daggerproject"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    val dagger_version = "2.35.1"
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")
    implementation("com.google.dagger:dagger-android:$dagger_version")
    implementation("com.google.dagger:dagger-android-support:$dagger_version")
    kapt("com.google.dagger:dagger-android-processor:$dagger_version")
    val glide_version = "4.14.2"
    implementation("com.github.bumptech.glide:glide:$glide_version")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.4.2")
    // ViewModel and LiveData
    val lifecycle_version = "2.6.0-alpha02"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    //retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    val rxJava_version = "3.1.5"
    implementation("io.reactivex.rxjava3:rxjava:$rxJava_version")
    // Rx-Retrofit Call Adapter
    val rxJava_adaptor_version = "2.9.0"
    implementation("com.squareup.retrofit2:adapter-rxjava3:$rxJava_adaptor_version")
    // Reactive Streams (convert Observable to LiveData)
    val reactivestreams_version = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$reactivestreams_version")
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
}