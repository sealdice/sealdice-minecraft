//val java_websocket: Configuration by configurations.creating


//val smokeTest by configurations.creating {
//    extendsFrom(configurations.testImplementation.get())
//}
//
//dependencies {
//    testImplementation("junit:junit:4.13")
//    smokeTest("org.apache.httpcomponents:httpclient:4.5.5")
//}
repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://github.com/TooTallNate/Java-WebSocket")
    maven("https://github.com/alibaba/fastjson2")
}

plugins {
    java
}
dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    implementation("org.java-websocket:Java-WebSocket:1.5.3")
    implementation("com.alibaba.fastjson2:fastjson2:2.0.23")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

