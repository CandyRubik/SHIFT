rootProject.name = "ShiftTest"
include(":app")
project(":app").projectDir = File("app")
include(":data")
project(":data").projectDir = File("data")
include(":domain")
project(":domain").projectDir = File("domain")