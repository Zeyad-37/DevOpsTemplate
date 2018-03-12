/**
 * @author ZIaDo on 2/20/18.
 */
object Versions {
    // Gradle
    val android_gradle_plugin = "3.0.1"

    // Kotlin
    val kotlin = "1.2.21"

    // SDK
    val min_sdk = 21
    val target_sdk = 27
    val compile_sdk = 27
    val buildTool = "27.0.1"

    // App Version
    val version_code = 1
    val version_name = "1.0"

    // Libs
    // - Support
    val support_lib = "27.0.2"
    val contraint_layout = "1.0.2"
    val archComp = "1.0.0"

    // - Network
    val retrofit = "2.3.0"
    val glide = "4.0.0"
    val okhttpVersion = "3.9.1"

    // - Reactive
    val rxjava = "2.1.9"
    val rxAndroid = "2.0.1"
    val rxbinding = "2.1.1"
    val rxredux = "2.0.0"

    // - Injection
    val koin_version = "0.8.2"
    val butterKnife = "8.8.1"

    // - Tools
    val lottie = "2.2.0"
    val rxlint = "1.6"
    val leakCanary = "1.5.4"
    val parceler = "1.1.9"

    // - Testing
    val androidSupportTest = "1.0.1"
    val espressoCore = "3.0.1"
    val powerMock = "1.7.3"
    val robolectric = "3.5.1"
    val okhttpIdelingResource = "1.0.0"
    val mockito = "2.10.0"
    val mockitoKotlin = "1.5.0"
    val restMock = "0.2.2"
    val junit = "4.12"

    val genericRecyclerViewAdapter = "1.8.0"
    val appId = "com.dd.template"
}

object Deps {
    // Gradle
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    val infer = "com.uber:infer-plugin:0.7.4"
    val error_prone = "net.ltgt.gradle:gradle-errorprone-plugin:0.0.13"
    val pitest = "pl.droidsonroids.gradle:gradle-pitest-plugin:0.1.4"
    val jacoco = "com.dicedmelon.gradle:jacoco-android:0.1.2"
    val kotlinter = "gradle.plugin.org.jmailen.gradle:kotlinter-gradle:1.5.1"
    val detekt = "gradle.plugin.io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.0.0.RC6-3"
    val dokka = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.15"

    // Kotlin
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.kotlin}"

    // Libs
    // - Support
    val design_support = "com.android.support:design:${Versions.support_lib}"
    val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val support_v4 = "com.android.support:support-v4:${Versions.support_lib}"
    val recycler_view = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    val card_view = "com.android.support:cardview-v7:${Versions.support_lib}"
    val palette = "com.android.support:palette-v7:${Versions.support_lib}"
    val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions
            .contraint_layout}"
    val arch_life_cycle = "android.arch.lifecycle:extensions:${Versions.archComp}"
    val arch_reactive_stream = "android.arch.lifecycle:reactivestreams:${Versions.archComp}"

    // - Network
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val okhttp = "3.9.1"
    val reactive_netork = "com.github.pwittchen:reactivenetwork-rx2:0.12.2"

    // - Reactive
    val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rx_binding_core = "com.jakewharton.rxbinding2:rxbinding:${Versions.rxbinding}"
    val rx_binding_app_compat = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:${Versions
            .rxbinding}"
    val rx_binding_design = "com.jakewharton.rxbinding2:rxbinding-design:${Versions.rxbinding}"
    val rx_binding_recycler_view = "com.jakewharton.rxbinding2:rxbinding-recyclerview-v7:${Versions
            .rxbinding}"
    val rx_redux = "com.github.Zeyad-37:RxRedux:${Versions.rxredux}"

    // - Injection
    val koin_core = "org.koin:koin-core:${Versions.koin_version}"
    val koin_android = "org.koin:koin-android:${Versions.koin_version}"
    val koin_arch = "org.koin:koin-android-architecture:${Versions.koin_version}"

    // - Tools
    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    val rxlint = "nl.littlerobots.rxlint:rxlint:${Versions.rxlint}"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    val leakCanary_no_op = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
    val generic_recycler_review = "com.github.Zeyad-37:GenericRecyclerViewAdapter:${Versions.genericRecyclerViewAdapter}"

    // - Testing
    val junit = "junit:junit:${Versions.junit}"
    val support_annotation = "com.android.support:support-annotations:${Versions.support_lib}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    val robolectric_shadow_support_v4 = "org.robolectric:shadows-support-v4:3.3.2"

    val power_mock_junit = "org.powermock:powermock-module-junit4:${Versions.powerMock}"
    val power_mock_junit_rule = "org.powermock:powermock-module-junit4-rule:${Versions.powerMock}"
    val power_mock_mockito = "org.powermock:powermock-api-mockito:${Versions.powerMock}"
    val power_mock_class_loading_xstream = "org.powermock:powermock-classloading-xstream:${Versions
            .powerMock}"

    val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.okhttpVersion}"
    val rest_mock = "com.github.andrzejchm.RESTMock:android:${Versions.restMock}"

    val okHttp_ideling_resource = "com.jakewharton.espresso:okhttp3-idling-resource:${Versions
            .okhttpIdelingResource}"

    val esspresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espressoCore}"
    val support_test_runner = "com.android.support.test:runner:${Versions.androidSupportTest}"
    val support_test_rules = "com.android.support.test:rules:${Versions.androidSupportTest}"
}