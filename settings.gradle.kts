rootProject.name = "ShiftTest"

include(":app")
project(":app").projectDir = File("app")

include(":feature:user:registration")
project(":feature:user:registration").projectDir = File("feature/user/registration")

include(":feature:user:greeting")
project(":feature:user:greeting").projectDir = File("feature/user/greeting")

include(":shared:user")
project(":shared:user").projectDir = File("shared/user")

include(":navigation")
project(":navigation").projectDir = File("navigation")
