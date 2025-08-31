plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "RoosterSql"

include(":RoosterCommon")
project(":RoosterCommon").projectDir = file("../../repos/RoosterUI/RoosterCommon")