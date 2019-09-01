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
        "com.fasterxml.jackson.core:jackson-databind:2.9.9.3"
    ],
    "developmentDependencies": [
        "org.junit.jupiter:junit-jupiter-api:5.5.1"
    ],
    "overridingDependencies": []
}
```


## Dependency Resolution

The dependency resolution strategy is to take whichever dependency is higher in the dependency tree. The top level dependencies (declared in the project.json file) are at level 0, their immediate transient dependencies are at level 1, and so on. The `overrideingDependencies` field may be used to force the use of a particular dependency version if there is a conflict.
