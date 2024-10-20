plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    kotlin("kapt")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
//    id ("org.mockito:mockito-junit-jupiter:5.0.0")

}

android {
    namespace = "com.demosample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demosample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.demosample.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
//    implementation("androidx.fragment:fragment-ktx:1.5.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit itself
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for JSON parsing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
//    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.4")

    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-android-compiler:2.49")

    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.49")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.49")

//    implementation ("androidx.room:room-runtime:2.5.0")
//    kapt ("androidx.room:room-compiler:2.5.0")

//    def room_version = "2.6.1"

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // To use Kotlin annotation processing tool (kapt)
    kapt ("androidx.room:room-compiler:2.6.1")
//    kapt("com.google.dagger:hilt-compiler:2.44")

    testImplementation ("org.mockito:mockito-junit-jupiter:5.0.0")
    testImplementation ("org.mockito:mockito-core:5.0.0")
    androidTestImplementation ("org.mockito:mockito-core:5.0.0")
    testImplementation ("org.mockito:mockito-inline:5.0.0")// for inline mocking
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
//    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
//    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    // JUnit and Hilt test helpers
//    testImplementation ("junit:junit:4.13.2")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.3")


    testImplementation ("junit:junit:4.13")
    testImplementation ("org.mockito:mockito-core:2.23.0")
//    testImplementation ("android.arch.core:core-testing:1.1.1")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.8")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
//    androidTestImplementation ("androidx.test.runner:runner:1.1.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")

    androidTestAnnotationProcessor ("com.google.dagger:hilt-android-compiler:2.51.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    androidTestImplementation("androidx.test:runner:1.5.2")
//    testImplementation("android.arch.core:core-testing:2.2.0")
//    androidTestImplementation("android.arch.core:core-testing:2.2.0")

//    androidTestImplementation(libs.androidx.test.runner)
//    androidTestImplementation(libs.androidx.test.rules)
//    androidTestUtil(libs.androidx.test.orchestrator)

}