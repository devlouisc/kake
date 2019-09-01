# Overview

Kake is a dependency manager and task runner for the Kotlin programming language.


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
        "com.fasterxml.jackson.core:jackson-databind:2.9.9.3"
    ],
    "devDependencies": [
        "org.junit.jupiter:junit-jupiter-api:5.5.1"
    ]
}
```

# Task Running

kake will come with some standard tasks:

- clean
- compile
- package
- install

```sh
kake [flags] task1[args...] task2[args...] ...

    -h --help       Prints this help text

    -l --list       Lists all registered tasks

    -v --version    Prints the tool's version and other info
    
    These flags may only be used with tasks:
    
        -o --output     File to write the run output to
        -q --quiet      Supresses the run's console output
        
    Ex: kake clean \
             compile[src,target] \
             test \
             package[full=true] \
             deploy[env=production] \
             -q -o build.log 
```
