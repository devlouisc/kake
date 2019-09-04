# Preamble

This is the design document for the Kake tool and will detail how to implement the various features of the tool.


# Overview

Kake is a dependency manager and task runner for the Kotlin programming language.


# Dependency Management

This is the `project.json` file in the top level directory of the project. The JSON format is used because it is simple and easy to parse.

```json
{
    "group": "dev.louisc",
    "artifact": "kake",
    "version": "1.0.0-SNAPSHOT",
    "repositories": [
        "https://repo.maven.apache.org/maven2/",
        "https://jcenter.bintray.com/"
    ],
    "dependencies": [
        "com.google.code.gson:gson:2.8.5"
    ],
    "developmentDependencies": [
        "org.junit.jupiter:junit-jupiter-api:5.5.1"
    ],
    "overridingDependencies": []
}
```


## Dependency Resolution

The dependency resolution strategy is to take whichever dependency is higher in the dependency tree. The top level tasks (declared in the project.json file) are at level 0, their immediate transient tasks are at level 1, and so on. The `overrideingDependencies` field may be used to force the use of a particular dependency version if there is a conflict.


# Task Running

Installing Kake will create a `.kake` directory in the home directory and pull down the necessary dependencies. The package cache will already have the version of Kake installed so that the `Kakefile.kts` can import the standard tasks that Kake provides, i.e. `Kakefile.kts` will have the `dev.louisc.kake` package available to import.

```kotlin
// Kakefile.kts
import dev.louisc.kake.*

val task1 = Task("task1", "description1") { println("task1") }
val task2 = Task("task2", "") { println("task2") }
val task3 = Task("task3", "") { println("task3") }

val seriesTask = Task("seriesTask", "", {}, series(task1, task2, task3))
val parallelTask = Task("parallelTask", "", parallel(task1, task2, task3))
val complexTask = Task("complexTask", "", series(task1, parallel(task2, task3)))

val builderTask = TaskBuilder()
    .name("builderTask")
    .logic(series(task1, task2))
    .build()

TaskManager.add(task1)
TaskManager.add(task1, task2, task3)

TaskManager.run("task1")
```
