plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.sgmis_java"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sgmis_java"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation(libs.retrofit)
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    implementation(libs.converter.gson)
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation(libs.okhttp)
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxjava
    implementation(libs.rxjava)
    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxandroid
    implementation(libs.rxandroid)
    implementation(libs.adapter.rxjava3)
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor
    implementation(libs.logging.interceptor)
//    // https://mvnrepository.com/artifact/androidx.navigation/navigation-ui
//    runtimeOnly(libs.navigation.ui)
//    // https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment
//    runtimeOnly(libs.navigation.fragment)
    // https://mvnrepository.com/artifact/com.github.abel533/ECharts
    implementation(libs.echarts)
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation(libs.gson)
    // https://mvnrepository.com/artifact/io.github.youth5201314/banner
    implementation(libs.banner)
    // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
    implementation(libs.glide)
    // https://mvnrepository.com/artifact/cn.hutool/hutool-core
    implementation(libs.hutool.core)
}