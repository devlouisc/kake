# Overview

Kake is a dependency manager and task runner for the Kotlin programming language.

This project is in the proof of concept phase so things will change quickly and the code is only intended to work on my machine, for now.


# Dependency Management

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

The dependency resolution strategy is to take whichever dependency is higher in the dependency tree. The top level tasks (declared in the project.json file) are at level 0, their immediate transient tasks are at level 1, and so on. The `overrideingDependencies` field may be used to force the use of a particular dependency version if there is a conflict.


# Task Running

kake will come with some standard tasks:

- clean
- compile
- test
- run

```sh
kake [flags] task1 task2 task3 ...

    -h --help       Prints this help text

    -l --list       Lists all registered tasks

    -v --version    Prints the tool's version and other info
    
    These flags may only be used with tasks:

        -q --quiet      Supresses the run's console output
        
    Ex: kake --quiet clean compile test package deploy
```
