// プラグインの追加（参照などのサポート）
plugins {
    kotlin("jvm") version "1.3.60"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.yotu"
version = "1.0-SNAPSHOT"

// 依存関係の追加
repositories {
    jcenter()
    maven {
        url = uri("https://repo.nukkitx.com/main/")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/exposed")
    }
}

// プロジェクトコンパイル時の設定
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.xerial:sqlite-jdbc:3.30.1")
    implementation("org.jetbrains.exposed:exposed:0.9.1")
    implementation("org.jetbrains.exposed:exposed-core:0.35.1")
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    testCompileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
}

// タスクの定義
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

}