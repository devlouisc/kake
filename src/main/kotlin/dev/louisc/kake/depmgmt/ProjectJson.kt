package dev.louisc.kake.depmgmt

data class PackageJson(
        val group: String,
        val artifact: String,
        val version: String,
        val dependencies: List<String>,
        val developmentDependencies: List<String>,
        val overridingDependencies: List<String>
)
